package session

import (
	"context"
	dto "go-ws/internal/app/core/dto/session"
	"go-ws/internal/app/framnework/session"
)

type SessionUsecase struct {
	sessionStore *session.SessionStore
	sessionFactory *SessionFactory
}

func NewSessionUsecase(sessionStore *session.SessionStore) *SessionUsecase {
	return &SessionUsecase{sessionStore: sessionStore, sessionFactory: NewSessionFactory()}
}

func (u *SessionUsecase) GetSession(ctx context.Context, sessionID string) (dto.SessionDTO, error) {
	session, err := u.sessionStore.GetSession(ctx, sessionID)
	if err != nil {
		return dto.SessionDTO{}, err
	}

	sessiondto := u.sessionFactory.CreateSession(session)
	return *sessiondto, nil

}