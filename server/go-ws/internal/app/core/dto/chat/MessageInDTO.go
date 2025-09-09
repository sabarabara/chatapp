package chat

import (
	"encoding/json"
)

type MessageInDTO struct {
	messageid string
	message   string
	userid    string
	isPersonal bool
	username  string
}

func NewMessageInDTO(messageid, message, userid string, isPersonal bool, username string) *MessageInDTO {
	return &MessageInDTO{
		messageid: messageid,
		message:   message,
		userid:    userid,
		isPersonal: isPersonal,
		username:  username,
	}
}

func (dto *MessageInDTO) GetMessageID() string {
	return dto.messageid
}
func (dto *MessageInDTO) GetMessage() string {
	return dto.message
}
func (dto *MessageInDTO) GetUserID() string {
	return dto.userid
}
func (dto *MessageInDTO) IsPersonal() bool {
	return dto.isPersonal
}
func (dto *MessageInDTO) GetUsername() string {
	return dto.username
}

func (m MessageInDTO) MarshalJSON() ([]byte, error) {
	return json.Marshal(map[string]interface{}{
		"messageid": m.messageid,
		"message":   m.message,
		"userid":    m.userid,
		"isPersonal": m.isPersonal,
		"username":  m.username,
	})
}

func (m *MessageInDTO) UnmarshalJSON(data []byte) error {
	var tmp struct {
		MessageID  string `json:"messageid"`
		Message    string `json:"message"`
		UserID     string `json:"userid"`
		IsPersonal bool   `json:"isPersonal"`
		Username   string `json:"username"`
	}

	if err := json.Unmarshal(data, &tmp); err != nil {
		return err
	}

	m.messageid = tmp.MessageID
	m.message = tmp.Message
	m.userid = tmp.UserID
	m.isPersonal = tmp.IsPersonal
	m.username = tmp.Username

	return nil
}