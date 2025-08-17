package main

import (
	"context"
	"log"

	controllers "go-ws/internal/app/controller"
	hub "go-ws/internal/app/framnework/redis"
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

	// Hub 初期化
	h := hub.NewHub(rdb)

	// Controller 初期化
	ctrl := controllers.NewChatController(h)

	// Gin サーバー
	r := gin.Default()
	r.GET("/ws", ctrl.HandleWebSocket)
	r.Run(":8080")
}
