package forum

import (
	vo "go-ws/internal/app/core/domain/model/vo/forum"
	"go-ws/internal/app/core/dto/forum"
)

type ForumFactory struct{}

func NewForumFactory() *ForumFactory {
	return &ForumFactory{}
}

func (f *ForumFactory) fetchForumInformation(dto *forum.ForumInformDTO) (*forum.ForumInformDTO, error) {
	roomid := dto.GetRoomID()
	username := dto.GetUsername()
	image := dto.GetImage()
	title := dto.GetTitle()

	validroomid := vo.NewRoomId(roomid)
	validusername := vo.NewUsername(username)
	validimage := vo.NewImage(image)
	validtitle := vo.NewTitle(title)

	validforumdto := forum.NewForumInformDTO(validroomid.GetId(), validusername.GetUsername(), validtitle.GetTitle(), []byte(validimage.GetBase64()))
	return validforumdto, nil
}

func (f *ForumFactory) ValidatedInForumUser(dto *forum.MessageInDTO) (*forum.MessageInDTO, error) {

	messageid := dto.GetMessageID()
	messageinfo := dto.GetMessage()
	userid := dto.GetUserID()
	isPersonal := dto.IsPersonal()
	username := dto.GetUsername()

	validmsgid := vo.NewMessageId(messageid)
	validmsginfo := vo.NewMessage(messageinfo)
	validuserid := vo.NewUserId(userid)
	validusername := vo.NewUsername(username)

	validmegdto := forum.NewMessageInDTO(validmsgid.GetId(), validmsginfo.GetContent(), validuserid.GetId(), isPersonal, validusername.GetUsername())

	return validmegdto, nil
}

func (f *ForumFactory) ValidatedOutForumUser(dto *forum.MessageOutNonidDTO,messageId string) (*forum.MessageOutDTO, error) {
	roomid := dto.GetRoomID()
	message := dto.GetMessage()
	userid := dto.GetUserID()

	validroomid := vo.NewRoomId(roomid)
	validmessage := vo.NewMessage(message)
	validuserid := vo.NewUserId(userid)

	validmegdto := forum.NewMessageOutDTO(validroomid.GetId(), validuserid.GetId(), validmessage.GetContent(), messageId)
	return validmegdto, nil
}
