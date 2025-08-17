package forum

import (
	"github.com/google/uuid"
)


type MessageOutDTO struct {
	roomid  uuid.UUID `json:"roomId"`
	userid  string    `json:"userId"`
	message string    `json:"message"`
}

func NewMessageOutDTO(roomid uuid.UUID, userid string, message string) *MessageOutDTO {
	return &MessageOutDTO{
		roomid:  roomid,
		userid:  userid,
		message: message,
	}
}

func (dto *MessageOutDTO) GetRoomID() uuid.UUID {
	return dto.roomid
}
func (dto *MessageOutDTO) GetMessage() string {
	return dto.message
}

func (dto *MessageOutDTO) GetUserID() string {
	return dto.userid
}