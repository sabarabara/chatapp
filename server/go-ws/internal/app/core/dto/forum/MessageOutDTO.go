package forum

import (
	"github.com/google/uuid"
)


type MessageOutDTO struct {
	roomid  uuid.UUID `json:"roomId"`
	message string    `json:"message"`
}

func NewMessageOutDTO(roomid uuid.UUID, message string) *MessageOutDTO {
	return &MessageOutDTO{
		roomid:  roomid,
		message: message,
	}
}

func (dto *MessageOutDTO) GetRoomID() uuid.UUID {
	return dto.roomid
}
func (dto *MessageOutDTO) GetMessage() string {
	return dto.message
}