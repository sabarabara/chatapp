package controllers

import (
	"encoding/json"
	"net/http"
	"go-ws/internal/app/controller/ws"
	dto "go-ws/internal/app/core/dto/chat"
	hub "go-ws/internal/app/framnework/redis"
	"go-ws/internal/app/usecase/chat"
	"log"
	"go-ws/internal/app/usecase/session"

	"github.com/gin-gonic/gin"
	"github.com/gorilla/websocket"
)

type ChatController struct {
	ChatUsecase *chat.ChatUsecase
	HubManager  *hub.HubManager
	SessionUsecase *session.SessionUsecase
}

func NewChatController(cu *chat.ChatUsecase, hm *hub.HubManager, su *session.SessionUsecase) *ChatController {
	return &ChatController{ChatUsecase: cu, HubManager: hm, SessionUsecase: su}
}

func (c *ChatController) HandleWebSocket(ctx *gin.Context) {

	//sessionの取得
	context := ctx.Request.Context()
	cookie, err := ctx.Request.Cookie("session_id")
	if err != nil {
		ctx.String(http.StatusUnauthorized, "no session")
		return
	}

	sessionDTO, err := c.SessionUsecase.GetSession(context, cookie.Value)
	if err != nil {
		ctx.String(http.StatusUnauthorized, "invalid session")
		return
	}

	

	// upgrade
	conn, err := ws.Upgrader.Upgrade(ctx.Writer, ctx.Request, nil)
	if err != nil {
		log.Println("upgrade error:", err)
		return
	}


	// Client を作成（Send channel は必須）
	client := &hub.Client{Send: make(chan []byte, 256)}

	// client->hub
	go func() {
		defer func() {
			conn.Close()


			c.HubManager.RemoveClientFromAllHubs(client)
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
			roomHub := c.HubManager.GetOrCreateHub(msgDTO.GetRoomID().String())

			// クライアントを Hub に登録（初回のみ）
			if _, exists := roomHub.Clients[client]; !exists {
				roomHub.Clients[client] = true
			}

			// Usecase に渡す
			_, err = c.ChatUsecase.SendMessage(&msgDTO, roomHub)
			if err != nil {
				log.Println("send message error:", err)
			}
		}
	}()

	// hub->client
	go func() {
		defer conn.Close()
		for {
			// ここは client に送られる Hub メッセージを待つ
			msgDTO, err := c.ChatUsecase.ValidateInUser(client)
			if err != nil {
				log.Println("invalid message format:", err)
				break
			}

			msgBytes, err := json.Marshal(msgDTO)
			if err != nil {
				log.Println("marshal error:", err)
				break
			}

			if err := conn.WriteMessage(websocket.TextMessage, msgBytes); err != nil {
				break
			}
		}
	}()
}
