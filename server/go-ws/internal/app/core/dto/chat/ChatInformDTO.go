package chat

import(
	"github.com/google/uuid"
)

type ChatInformDTO struct {
	roomid   uuid.UUID `json:"roomid"`
	username string    `json:"username"`
	image    []byte     `json:"image,omitempty"`
}

func NewChatInformDTO(roomid uuid.UUID, username string, image []byte) *ChatInformDTO {
	return &ChatInformDTO{
		roomid:   roomid,
		username: username,
		image:    image,
	}
}

func (dto *ChatInformDTO) GetRoomID() uuid.UUID {
	return dto.roomid
}

func (dto *ChatInformDTO) GetUsername() string {
	return dto.username
}

func (dto *ChatInformDTO) GetImage() []byte {
	return dto.image
}
func (dto *ChatInformDTO) GetImageBase64() string {
	if dto.image == nil {
		return ""
	}
	return string(dto.image)
}
