package chat

type MessageInDTO struct {
	messageid string `json:"messageId"`
	message   string `json:"message"`
	userid    string `json:"userId"`
	isPersonal bool   `json:"isPersonal"`
	username  string `json:"username"`
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