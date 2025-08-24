package forum

import (
	"github.com/google/uuid"
)

type ChatID struct {
	value uuid.UUID
}

func NewChatID(value uuid.UUID) *ChatID {
	return &ChatID{
		value: value,
	}
}

func (id *ChatID) GetValue() uuid.UUID {
	return id.value
}