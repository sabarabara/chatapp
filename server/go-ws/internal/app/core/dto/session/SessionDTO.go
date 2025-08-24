package session

type SessionDTO struct {
	UserID   string 
	Username string 
	Email    string 
}

func NewSessionDTO(userID, username, email string) *SessionDTO {
	return &SessionDTO{
		UserID:   userID,
		Username: username,
		Email:    email,
	}
}

func (dto *SessionDTO) GetUserID() string {
	return dto.UserID
}	

func (dto *SessionDTO) GetUsername() string {
	return dto.Username
}

func (dto *SessionDTO) GetEmail() string {
	return dto.Email
}	