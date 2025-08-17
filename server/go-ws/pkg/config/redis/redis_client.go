package redis

import (
	"context"

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
