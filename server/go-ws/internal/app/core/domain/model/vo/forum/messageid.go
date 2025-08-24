package forum

type MessageId struct {
	id string
}

func NewMessageId(id string) *MessageId {
	return &MessageId{
		id: id,
	}
}

func (mid *MessageId) GetId() string {
	return mid.id
}
