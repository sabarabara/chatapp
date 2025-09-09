package main

import (
	"context"
	"log"
	"net/http"
	"time"
	"os"
	"fmt"

	controllers "go-ws/internal/app/controller"

	"go-ws/internal/app/framnework/api"
	hub "go-ws/internal/app/framnework/redis"
	sessionstore "go-ws/internal/app/framnework/session"
	chat "go-ws/internal/app/usecase/chat"
	forum "go-ws/internal/app/usecase/forum"
	session "go-ws/internal/app/usecase/session"
	redispkg "go-ws/pkg/config/redis"

	"github.com/gin-gonic/gin"
)

func main() {

	ctx := context.Background()

    // Redis
    redisHost := os.Getenv("REDIS_HOST")
    if redisHost == "" {
        redisHost = "redis"
    }
    rdb := redispkg.NewRedisClient(redisHost + ":6379")
    if _, err := rdb.Ping(ctx); err != nil {
        log.Fatalf("Failed to connect to Redis: %v", err)
    } else {
		log.Println("Connected to Redis successfully")
	}

    // HTTP クライアント
    httpClient := &http.Client{Timeout: 10 * time.Second}

    // Java API ホスト
    host := os.Getenv("JAVA_API_HOST")
    port := os.Getenv("JAVA_API_PORT")
    url := fmt.Sprintf("http://%s:%s", host, port)

    // Hub 初期化
    h := hub.NewHubManager(rdb)

    // APIサーバー初期化
    chatAPI := api.NewChatAPIserver(httpClient, url)
    forumAPI := api.NewForumAPIServer(httpClient, url)

	//usecase初期化
	//chat
	chatFactory := chat.NewChatFactory()
	chatUsecase := chat.NewChatUsecase(chatFactory, chatAPI)

	//forum
	forumFactory := forum.NewForumFactory()
	forumUsecase := forum.NewForumUsecase(forumFactory, forumAPI)

	//session
	sessionStore := sessionstore.NewSessionStore(rdb)
	sessionUsecase := session.NewSessionUsecase(sessionStore)

	// Controller 初期化
	Chatctrl := controllers.NewChatController(chatUsecase, h, sessionUsecase)
	Forumctrl := controllers.NewForumController(forumUsecase, h, sessionUsecase)

	// Gin サーバー
	r := gin.Default()
	r.GET("/chat/ws", Chatctrl.HandleWebSocket)
	r.GET("/forum/ws", Forumctrl.HandleWebSocket)
	// ヘルスチェックエンドポイント
	r.GET("/health", func(c *gin.Context) {
		c.JSON(http.StatusOK, gin.H{"status": "ok"})
	})
	r.Run(":8080")
}
