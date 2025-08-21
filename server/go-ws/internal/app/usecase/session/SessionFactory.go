package session

import (
	"go-ws/internal/app/core/domain/model/vo/session"
	dto "go-ws/internal/app/core/dto/session"
)

type SessionFactory struct{}

func NewSessionFactory() *SessionFactory {
	return &SessionFactory{}
}

func (sf *SessionFactory) CreateSession(sessiondto *dto.SessionDTO) *dto.SessionDTO {

	userId := sessiondto.GetUserID()
	username := sessiondto.GetUsername()
	email := sessiondto.GetEmail()

	validUserId := session.NewUserID(userId)
	validUsername := session.NewUsername(username)
	validEmail := session.NewEmail(email)

	sessionDTO := dto.NewSessionDTO(
		validUserId.GetUserID(),
		validUsername.GetUsername(),
		validEmail.GetEmail(),
	)

	return sessionDTO
}
