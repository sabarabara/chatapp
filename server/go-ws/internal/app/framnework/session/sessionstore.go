package session

import (
	"context"
	"encoding/json"
	dto "go-ws/internal/app/core/dto/session"
	"go-ws/pkg/config/redis"
	redisv9 "github.com/redis/go-redis/v9"
)

type SessionStore struct {
	rdb *redis.RedisClient
}

func NewSessionStore(rdb *redis.RedisClient) *SessionStore {
	return &SessionStore{rdb: rdb}
}

func (s *SessionStore) GetSession(ctx context.Context, sessionID string) (*dto.SessionDTO, error) {
	val, err := s.rdb.Get(ctx, sessionID).Result()
	if err == redisv9.Nil {
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
