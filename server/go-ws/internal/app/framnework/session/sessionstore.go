package session

import (
	"context"
	"encoding/json"
	dto "go-ws/internal/app/core/dto/session"

	"github.com/redis/go-redis/v9"
)

type SessionStore struct {
	rdb *redis.Client
}

func NewSessionStore(rdb *redis.Client) *SessionStore {
	return &SessionStore{rdb: rdb}
}

func (s *SessionStore) GetSession(ctx context.Context, sessionID string) (*dto.SessionDTO, error) {
	val, err := s.rdb.Get(ctx, sessionID).Result()
	if err == redis.Nil {
		return nil, nil 
	}
	if err != nil {
		return nil, err
	}


	var session dto.SessionDTO
	if err := json.Unmarshal([]byte(val), &session); err != nil {
		return nil, err
	}

	return &session, nil
}
