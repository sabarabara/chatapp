package forum


type MessaheInDTO struct {
	messageid string `json:"messageId"`
	message string `json:"message"`
	userid     string `json:"userId"`
	isPersonal bool   `json:"isPersonal"`
	username   string `json:"username"`
}

func NewMessageInDTO(messageid, message, userid string, isPersonal bool, username string) *MessaheInDTO {
	return &MessaheInDTO{
		messageid: messageid,
		message:   message,
		userid:    userid,
		isPersonal: isPersonal,
		username:  username,
	}
}

func (dto *MessaheInDTO) GetMessageID() string {
	return dto.messageid
}
func (dto *MessaheInDTO) GetMessage() string {
	return dto.message
}
func (dto *MessaheInDTO) GetUserID() string {
	return dto.userid
}
func (dto *MessaheInDTO) IsPersonal() bool {
	return dto.isPersonal
}
func (dto *MessaheInDTO) GetUsername() string {
	return dto.username
}