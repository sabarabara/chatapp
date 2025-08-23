package main

import (
	"context"
	"log"
	"net/http"
	"time"

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
	// Redis クライアント作成
	rdb := redispkg.NewRedisClient("localhost:6379")

	// Redis 接続確認
	ctx := context.Background()
	if _, err := rdb.Ping(ctx); err != nil {
		log.Fatalf("Failed to connect to Redis: %v", err)
	} else {
		log.Println("Connected to Redis successfully")
	}


	//httpclient初期化
	httpClient := &http.Client{
		Timeout: time.Second * 10,
	}

	const port = "8080"

	// Hub 初期化
	h := hub.NewHubManager(rdb)

	// APIサーバー初期化
	chatAPI := api.NewChatAPIserver(httpClient, "http://localhost:"+port)
	forumAPI := api.NewForumAPIServer(httpClient, "http://localhost:"+port)

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
	r.Run(":8080")
}
