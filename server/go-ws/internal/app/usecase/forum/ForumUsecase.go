package forum

import (
	"encoding/json"
	"go-ws/internal/app/core/dto/forum"
	"go-ws/internal/app/framnework/api"
	hub "go-ws/internal/app/framnework/redis"
	"log"
)

type ForumUsecase struct {
	factory *ForumFactory
	api     *api.ForumAPIServer
}

func NewForumUsecase(factory *ForumFactory, api *api.ForumAPIServer) *ForumUsecase {
	return &ForumUsecase{
		factory: factory,
		api:     api,
	}
}

func (uc *ForumUsecase) FetchForumInformation(dto *forum.ForumInformDTO) (string, error) {
	validatedDTO, err := uc.factory.fetchForumInformation(dto)
	if err != nil {
		return "", err
	}

	return uc.api.FetchForumInformation(validatedDTO)
}

// //////////////////////////////////////////////////////////////////////////////////
func (uc *ForumUsecase) SendMessage(dto *forum.MessageOutDTO, roomHub *hub.Hub) (string, error) {
	validatedDTO, err := uc.factory.ValidatedOutForumUser(dto)
	if err != nil {
		return "", err
	}

	uc.api.SendMessage(validatedDTO)

	//dbに接続,入れ込みOKならbroadcast これどっちの方がいいのかな？早い方がいいのか？
	msgBytes, err := json.Marshal(validatedDTO)
	if err != nil {
		log.Println("marshal error:", err)
		return "", err
	}
	roomHub.Broadcast <- msgBytes

	return "OK", nil
}

func (uc *ForumUsecase) ValidateInUser(client *hub.Client) (*forum.MessageInDTO, error) {

	for msg := range client.Send {
		dto := &forum.MessageInDTO{}
		if err := json.Unmarshal(msg, dto); err != nil {
			log.Println("unmarshal error:", err)
			return nil, err
		}

		validatedDTO, err := uc.factory.ValidatedInForumUser(dto)
		if err != nil {
			return nil, err
		}
		return validatedDTO, nil
	}

	return nil, nil
}
