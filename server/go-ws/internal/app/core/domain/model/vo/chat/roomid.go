package chat

type RoomId struct {
	id string
}

func NewRoomId(id string) *RoomId {
	return &RoomId{
		id: id,
	}
}

func (rid *RoomId) GetId() string {
	return rid.id
}
