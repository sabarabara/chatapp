package chat

type UserId struct {
	id string
}

func NewUserId(id string) *UserId {
	return &UserId{
		id: id,
	}
}

func (uid *UserId) GetId() string {
	return uid.id
}
