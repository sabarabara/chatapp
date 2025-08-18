package controllers

import (
	"encoding/json"
	"go-ws/internal/app/controller/ws"
	dto "go-ws/internal/app/core/dto/chat"
	hub "go-ws/internal/app/framnework/redis"
	"go-ws/internal/app/usecase/chat"
	"log"

	"github.com/gin-gonic/gin"
	"github.com/gorilla/websocket"
)

type ChatController struct {
	Hub         *hub.Hub
	ChatUsecase *chat.ChatUsecase
	HubManager  *hub.HubManager
}

func NewChatController(h *hub.Hub, cu *chat.ChatUsecase, hm *hub.HubManager) *ChatController {
	return &ChatController{Hub: h, ChatUsecase: cu, HubManager: hm}
}

func (c *ChatController) HandleWebSocket(ctx *gin.Context) {
	conn, err := ws.Upgrader.Upgrade(ctx.Writer, ctx.Request, nil)
	if err != nil {
		log.Println("upgrade error:", err)
		return
	}
	client := &hub.Client{Send: make(chan []byte, 256)}
	c.Hub.Clients[client] = true

	// client->hub
	go func() {
		defer func() {
			conn.Close()
			delete(c.Hub.Clients, client)
		}()

		for {
			_, msgBytes, err := conn.ReadMessage()
			if err != nil {
				break
			}

			var msgDTO dto.MessageOutDTO
			if err := json.Unmarshal(msgBytes, &msgDTO); err != nil {
				log.Println("invalid message format:", err)
				continue
			}

			// roomID に紐づく Hub を取得
			hub := c.HubManager.GetOrCreateHub(msgDTO.GetRoomID().String())

			// クライアントを Hub に登録（初回のみ）
			if _, exists := hub.Clients[client]; !exists {
				hub.Clients[client] = true
			}

			// Usecaseに渡す
			_, err = c.ChatUsecase.SendMessage(&msgDTO)
			if err != nil {
				log.Println("send message error:", err)
			}
		}
	}()

	// hub->client
	go func() {
		defer conn.Close()
		msgdto, err := c.ChatUsecase.ValidateInUser(client)
		if err != nil {
			log.Println("invalid message format:", err)
			return
		}

		msgBytes, err := json.Marshal(msgdto)
		if err != nil {
			log.Println("marshal error:", err)
			return
		}

		conn.WriteMessage(websocket.TextMessage, msgBytes)
	}()
}
