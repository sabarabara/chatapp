package controllers

import (
    "log"
    "net/http"

    "github.com/gin-gonic/gin"
    "github.com/gorilla/websocket"

	"go-ws/internal/app/framnework/redis"
)

var upgrader = websocket.Upgrader{
	CheckOrigin: func(r *http.Request) bool { return true },
}

type ChatController struct {
	Hub *hub.Hub
}

func NewChatController(h *hub.Hub) *ChatController {
	return &ChatController{Hub: h}
}

func (c *ChatController) HandleWebSocket(ctx *gin.Context) {
	conn, err := upgrader.Upgrade(ctx.Writer, ctx.Request, nil)
	if err != nil {
		log.Println("upgrade error:", err)
		return
	}
	client := &hub.Client{Send: make(chan []byte, 256)}
	c.Hub.Clients[client] = true

	// 受信
	go func() {
		defer func() {
			conn.Close()
			delete(c.Hub.Clients, client)
		}()
		for {
			_, msg, err := conn.ReadMessage()
			if err != nil {
				break
			}
			c.Hub.Broadcast <- msg
		}
	}()

	// 送信
	go func() {
		defer conn.Close()
		for msg := range client.Send {
			if err := conn.WriteMessage(websocket.TextMessage, msg); err != nil {
				break
			}
		}
	}()
}