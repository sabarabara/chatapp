package chat

import (
	"encoding/json"
	"log"
	"strings"
	"go-ws/internal/app/core/dto/chat"
	"go-ws/internal/app/framnework/api"
	"go-ws/internal/app/framnework/redis"
	"go-ws/internal/app/core/dto/session"
)

type ChatUsecase struct {
	factory *ChatFactory
	api     *api.ChatAPIserver
}

func NewChatUsecase(factory *ChatFactory, api *api.ChatAPIserver) *ChatUsecase {
	return &ChatUsecase{
		factory: factory,
		api:     api,
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

func (uc *ChatUsecase) SendMessage(sessiondto *session.SessionDTO, dto *chat.MessageOutNonidDTO, roomHub *hub.Hub) (string, error) {

	//ここでmessageidをjavaapiから受け取る
	messageid, err := uc.api.SendMessage(dto)
	if err != nil {
		return "", err
	}

	validatedDTO, err := uc.factory.ValidatedOutUser(dto,messageid)
	if err != nil {
		return "", err
	}
	//dbに接続,入れ込みOKならbroadcast これどっちの方がいいのかな？早い方がいいのか？
	msgBytes, err := json.Marshal(validatedDTO)
	if err != nil {
		log.Println("marshal error:", err)
		return "", err
	}

	
	roomHub.Broadcast <- msgBytes

	return "OK", nil
}



func (uc *ChatUsecase) ValidateInUser(sessiondto *session.SessionDTO, client *hub.Client) (*chat.MessageInDTO, error) {
	for msg := range client.Send {
		dto := &chat.MessageOutDTO{}
		if err := json.Unmarshal(msg, dto); err != nil {
			log.Println("unmarshal error:", err)
			return nil, err
		}

		// session内の情報をdtoに追加
		userid := sessiondto.UserID
		username := sessiondto.Username
		isPersonal := true

		log.Println("Session UserID:", userid)
		log.Println("DTO UserID:", dto.GetUserID())

		if strings.TrimSpace(userid) != strings.TrimSpace(dto.GetUserID()) {
    	isPersonal = false
		}


		setInDTO := chat.NewMessageInDTO(dto.GetMessageID(), dto.GetMessage(), userid, isPersonal, username)


		validatedDTO, err := uc.factory.ValidatedInUser(setInDTO)
		if err != nil {
			return nil, err
		}
		return validatedDTO, nil
	}

	return nil, nil
}