package forum

import (
	"encoding/json"
	"go-ws/internal/app/core/dto/forum"
	"go-ws/internal/app/framnework/api"
	hub "go-ws/internal/app/framnework/redis"
	"log"
	"go-ws/internal/app/core/dto/session"
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
func (uc *ForumUsecase) SendMessage(sessiondto *session.SessionDTO, dto *forum.MessageOutNonidDTO, roomHub *hub.Hub) (string, error) {

	//uc.api.SendMessage(validatedDTO)
	messageid := "karioki"

	validatedDTO, err := uc.factory.ValidatedOutForumUser(dto,messageid)
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

func (uc *ForumUsecase) ValidateInUser(sessiondto *session.SessionDTO, client *hub.Client) (*forum.MessageInDTO, error) {

	for msg := range client.Send {
		dto := &forum.MessageOutDTO{}
		if err := json.Unmarshal(msg, dto); err != nil {
			log.Println("unmarshal error:", err)
			return nil, err
		}


		// session内の情報をdtoに追加
		userid := sessiondto.UserID
		username := sessiondto.Username
		isPersonal := true

		if userid != dto.GetUserID() {
			isPersonal = false
		}

		setInDTO := forum.NewMessageInDTO(dto.GetMessageID(), dto.GetMessage(), userid, isPersonal, username)

		validatedDTO, err := uc.factory.ValidatedInForumUser(setInDTO)
		if err != nil {
			return nil, err
		}
		return validatedDTO, nil
	}

	return nil, nil
}
