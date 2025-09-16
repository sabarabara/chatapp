package redis

import (
	"context"
	"encoding/json"
	"github.com/redis/go-redis/v9"
)

type RedisClient struct {
	client *redis.Client
}

func NewRedisClient(addr string) *RedisClient {
	rdb := redis.NewClient(&redis.Options{
		Addr: addr,
	})
	return &RedisClient{client: rdb}
}

func (r *RedisClient) HGetAllAsMap(ctx context.Context, key string) (map[string]string, error) {
	// STRING 型を取得
	valStr, err := r.client.Get(ctx, key).Result()
	if err != nil {
		if err == redis.Nil {
			return nil, nil
		}
		return nil, err
	}

	// JSON を map[string]string に変換
	var m map[string]string
	if err := json.Unmarshal([]byte(valStr), &m); err != nil {
		return nil, err
	}

	return m, nil
}


func (r *RedisClient) Ping(ctx context.Context) (string, error) {
	return r.client.Ping(ctx).Result()
}

func (r *RedisClient) Publish(channel string, message []byte) error {
	return r.client.Publish(context.Background(), channel, message).Err()
}

func (r *RedisClient) Subscribe(channel string) <-chan []byte {
	pubsub := r.client.Subscribe(context.Background(), channel)
	ch := make(chan []byte)
	go func() {
		defer close(ch)
		for msg := range pubsub.Channel() {
			ch <- []byte(msg.Payload)
		}
	}()
	return ch
}
