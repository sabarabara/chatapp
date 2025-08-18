package controllers

import (
	"encoding/json"
	"go-ws/internal/app/controller/ws"
	dto "go-ws/internal/app/core/dto/forum"
	hub "go-ws/internal/app/framnework/redis"
	"go-ws/internal/app/usecase/forum"
	"log"

	"github.com/gin-gonic/gin"
	"github.com/gorilla/websocket"
)

type ForumController struct {
	ForumUsecase *forum.ForumUsecase
	HubManager   *hub.HubManager
}

func NewForumController(cu *forum.ForumUsecase, hm *hub.HubManager) *ForumController {
	return &ForumController{ForumUsecase: cu, HubManager: hm}
}

func (f *ForumController) HandleWebSocket(ctx *gin.Context) {
	conn, err := ws.Upgrader.Upgrade(ctx.Writer, ctx.Request, nil)
	if err != nil {
		log.Println("upgrade error:", err)
		return
	}
	client := &hub.Client{Send: make(chan []byte, 256)}

	// client->hub
	go func() {
		defer func() {
			conn.Close()
			f.HubManager.RemoveClientFromAllHubs(client)
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

			// roomID に紐づく Hub を取得（存在しなければ作る）
			roomHub := f.HubManager.GetOrCreateHub(msgDTO.GetRoomID().String())

			// クライアントを Hub に登録（初回のみ）
			if _, exists := roomHub.Clients[client]; !exists {
				roomHub.Clients[client] = true
			}

			// Usecaseに渡す
			_, err = f.ForumUsecase.SendMessage(&msgDTO, roomHub)
			if err != nil {
				log.Println("send message error:", err)
			}
		}
	}()

	// hub->client
	go func() {
		defer conn.Close()
		msgdto, err := f.ForumUsecase.ValidateInUser(client)
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
