package session

import (
	"context"
	"encoding/base64"
	"encoding/json"
	dto "go-ws/internal/app/core/dto/session"
	"go-ws/pkg/config/redis"
)

type SessionStore struct {
	rdb *redis.RedisClient
}

func NewSessionStore(rdb *redis.RedisClient) *SessionStore {
	return &SessionStore{rdb: rdb}
}

func (s *SessionStore) GetSession(ctx context.Context, encodedSessionID string) (*dto.SessionDTO, error) {
	
	data, err := base64.URLEncoding.DecodeString(encodedSessionID)
	if err != nil {
		return nil, err
	}
	sessionID := string(data)

	key := "spring:session:sessions:" + sessionID

	// Redis ハッシュ取得
	val, err := s.rdb.HGetAll(ctx, key).Result()
	if err != nil {
		return nil, err
	}
	if len(val) == 0 {
		return nil, nil
	}

	// sessionAttr:* だけ取り出して DTO に変換
	session := &dto.SessionDTO{}
	for k, v := range val {
		switch k {
		case "sessionAttr:userId":
			json.Unmarshal([]byte(v), &session.UserID)
		case "sessionAttr:username":
			json.Unmarshal([]byte(v), &session.Username)
		case "sessionAttr:email":
			json.Unmarshal([]byte(v), &session.Email)
		}
	}

	return session, nil
}
