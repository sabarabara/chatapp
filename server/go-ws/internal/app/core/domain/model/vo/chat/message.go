package chat

type Message struct {
	content string
}

func NewMessage(content string) *Message {
	return &Message{
		content: content,
	}
}

func (msg *Message) GetContent() string {
	return msg.content
}
