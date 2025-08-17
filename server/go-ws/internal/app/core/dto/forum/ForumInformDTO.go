package forum

import "github.com/google/uuid"

type ForumInformDTO struct {
	roomid uuid.UUID `json:"roomId"`
	username string    `json:"username"`
	title string    `json:"title"`
	image []byte     `json:"image,omitempty"`
}

func NewForumInformDTO(roomid uuid.UUID, username string, title string, image []byte) *ForumInformDTO {
	return &ForumInformDTO{
		roomid:   roomid,
		username: username,
		title:    title,	
		image:    image,
	}
}	

func (dto *ForumInformDTO) GetRoomID() uuid.UUID {
	return dto.roomid
}
func (dto *ForumInformDTO) GetUsername() string {
	return dto.username
}
func (dto *ForumInformDTO) GetTitle() string {
	return dto.title
}
func (dto *ForumInformDTO) GetImage() []byte {
	return dto.image
}
func (dto *ForumInformDTO) GetImageBase64() string {
	if dto.image == nil {
		return ""
	}
	return string(dto.image)
}