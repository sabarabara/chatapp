package session

import (
	"context"
	dto "go-ws/internal/app/core/dto/session"
	"go-ws/pkg/config/redis"
	"log"
)

type SessionStore struct {
	rdb *redis.RedisClient
}

func NewSessionStore(rdb *redis.RedisClient) *SessionStore {
	return &SessionStore{rdb: rdb}
}

func (s *SessionStore) GetSession(ctx context.Context, sessionid string) (*dto.SessionDTO, error) {
    key := "user:session:" + sessionid
    log.Println("Fetching session with key:", key)

    val, err := s.rdb.HGetAllAsMap(ctx, key)
    if err != nil {
        return nil, err
    }
    if len(val) == 0 {
        return nil, nil
    }

    session := &dto.SessionDTO{
        UserID:   val["userId"],
        Username: val["username"],
        Email:    val["email"],
    }

    log.Println("Retrieved session:", session)
    return session, nil
}

