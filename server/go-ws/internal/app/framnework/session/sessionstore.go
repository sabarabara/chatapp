package session

import (
	"context"
	"encoding/json"
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

	session := &dto.SessionDTO{}
	for k, v := range val {
		switch k {
		case "userId":
			json.Unmarshal([]byte(v), &session.UserID)
		case "username":
			json.Unmarshal([]byte(v), &session.Username)
		case "email":
			json.Unmarshal([]byte(v), &session.Email)
		}
	}

	return session, nil
}
