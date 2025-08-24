package session

type SessionInfo struct {
	userID   *UserID
	username *Username
	email    *Email
}

func NewSessionInfo(userID *UserID, username *Username, email *Email) *SessionInfo {
	return &SessionInfo{
		userID:   userID,
		username: username,
		email:    email,
	}
}

func (s *Session) GetUserID() *UserID {
	return s.userID
}

func (s *Session) GetUsername() *Username {
	return s.username
}

func (s *Session) GetEmail() *Email {
	return s.email
}
