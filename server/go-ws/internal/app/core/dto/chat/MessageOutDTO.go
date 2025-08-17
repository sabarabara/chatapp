package chat

import "github.com/google/uuid"

type MessageOutDTO struct {
	roomid  uuid.UUID `json:"roomId"`
	message string    `json:"message"`
	userid  string    `json:"userId"`
}

func NewMessageOutDTO(roomid uuid.UUID, message string, userid string) *MessageOutDTO {
	return &MessageOutDTO{
		roomid:  roomid,
		message: message,
		userid:  userid,
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