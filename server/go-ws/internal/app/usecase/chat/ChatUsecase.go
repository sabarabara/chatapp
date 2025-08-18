package chat

import (
	"encoding/json"
	"log"
	"go-ws/internal/app/core/dto/chat"
	"go-ws/internal/app/framnework/api"
	"go-ws/internal/app/framnework/redis"
)

type ChatUsecase struct {
	factory *ChatFactory
	api     *api.ChatAPIserver
	h       *hub.Hub
}

func NewChatUsecase(factory *ChatFactory, api *api.ChatAPIserver, h *hub.Hub) *ChatUsecase {
	return &ChatUsecase{
		factory: factory,
		api:     api,
		h:       h,
	}
}

// いらないかも
func (uc *ChatUsecase) FetchChatInformation(dto *chat.ChatInformDTO) (string, error) {
	validatedDTO, err := uc.factory.fetchChatInformation(dto)
	if err != nil {
		return "", err
	}

	return uc.api.FetchChatInformation(validatedDTO)
}

/////////////////////////////////////////////////////////////////////////////////

func (uc *ChatUsecase) SendMessage(dto *chat.MessageOutDTO) (string, error) {
	validatedDTO, err := uc.factory.ValidatedOutUser(dto)
	if err != nil {
		return "", err
	}

	uc.api.SendMessage(validatedDTO)

	//dbに接続,入れ込みOKならbroadcast これどっちの方がいいのかな？早い方がいいのか？
	msgBytes, err := json.Marshal(dto)
	if err != nil {
		log.Println("marshal error:", err)
		return "", err
	}
	uc.h.Broadcast <- msgBytes

	return "OK", nil
}



func (uc *ChatUsecase) ValidateInUser(client *hub.Client) (*chat.MessageInDTO, error) {
	for msg := range client.Send {
		dto := &chat.MessageInDTO{}
		if err := json.Unmarshal(msg, dto); err != nil {
			log.Println("unmarshal error:", err)
			return nil, err
		}

		validatedDTO, err := uc.factory.ValidatedInUser(dto)
		if err != nil {
			return nil, err
		}
		return validatedDTO, nil
	}

	return nil, nil
}