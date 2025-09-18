package chat

import (
	vo "go-ws/internal/app/core/domain/model/vo/chat"
	"go-ws/internal/app/core/dto/chat"
)

type ChatFactory struct{}

func NewChatFactory() *ChatFactory {
	return &ChatFactory{}
}

func (f *ChatFactory) fetchChatInformation(dto *chat.ChatInformDTO) (*chat.ChatInformDTO, error) {

	roomid := dto.GetRoomID()
	username := dto.GetUsername()
	image := dto.GetImage()

	validroomid := vo.NewRoomId(roomid)
	validusername := vo.NewUsername(username)
	validimage := vo.NewImage(image)

	validchatdto := chat.NewChatInformDTO(validroomid.GetId(), validusername.GetUsername(), []byte(validimage.GetBase64()))
	return validchatdto, nil
}

func (f *ChatFactory) ValidatedInUser(dto *chat.MessageInDTO) (*chat.MessageInDTO, error) {

	messageid := dto.GetMessageID()
	messageinfo := dto.GetMessage()
	userid := dto.GetUserID()
	isPersonal := dto.IsPersonal()
	username := dto.GetUsername()

	validmsgid := vo.NewMessageId(messageid)
	validmsginfo := vo.NewMessage(messageinfo)
	validuserid := vo.NewUserId(userid)
	validusername := vo.NewUsername(username)

	validmegdto := chat.NewMessageInDTO(validmsgid.GetId(), validmsginfo.GetContent(), validuserid.GetId(), isPersonal, validusername.GetUsername())

	return validmegdto, nil
}

func (f *ChatFactory) ValidatedOutUser(dto *chat.MessageOutNonidDTO, messageId string) (*chat.MessageOutDTO, error) {

	roomid := dto.GetRoomID()
	message := dto.GetMessage()
	userid := ""

	validroomid := vo.NewRoomId(roomid)
	validmessage := vo.NewMessage(message)
	validuserid := vo.NewUserId(userid)

	validmegdto := chat.NewMessageOutDTO(validroomid.GetId(), messageId, validmessage.GetContent(), validuserid.GetId())
	return validmegdto, nil
}
