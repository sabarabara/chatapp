package chat

import(
	"github.com/google/uuid"
)

type ChatInformDTO struct {
	roomID   uuid.UUID `json:"room_id"`
	username string    `json:"username"`
	image    []byte     `json:"image,omitempty"`
}

func NewChatInformDTO(roomID uuid.UUID, username string, image []byte) *ChatInformDTO {
	return &ChatInformDTO{
		roomID:   roomID,
		username: username,
		image:    image,
	}
}

func (dto *ChatInformDTO) GetRoomID() uuid.UUID {
	return dto.roomID
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

