package session

type Session struct {
	userID   *UserID
	username *Username
	email    *Email
}


func NewSession(userID *UserID, username *Username, email *Email) *Session {
	return &Session{
		userID:   userID,
		username: username,
		email:    email,
	}
}

func (s *Session) GetSessionInfo() *SessionInfo {
	return NewSessionInfo(s.userID, s.username, s.email)
}