package chat

import "github.com/google/uuid"

type RoomId struct {
	id uuid.UUID `json:"id"`
}

func NewRoomId(id uuid.UUID) *RoomId {
	return &RoomId{
		id: id,
	}
}

func (rid *RoomId) GetId() uuid.UUID {
	return rid.id
}
