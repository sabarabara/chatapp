package hub

import (
	"go-ws/internal/app/core/domain/service/interacter/ICachehub"
)


type Client struct {
	Send chan []byte
}

type Hub struct {
	Clients   map[*Client]bool
	Broadcast chan []byte
	Redis     ICachehub.PubSubClient
}

func NewHub(redis ICachehub.PubSubClient) *Hub {
	h := &Hub{
		Clients:   make(map[*Client]bool),
		Broadcast: make(chan []byte),
		Redis:     redis,
	}
	go h.run()
	go h.listenRedis()
	return h
}

func (h *Hub) run() {
	for msg := range h.Broadcast {
		// ローカルクライアントに送信
		for client := range h.Clients {
			client.Send <- msg
		}
		// Redis にも送信
		h.Redis.Publish("chat-room-1", msg)
	}
}

func (h *Hub) listenRedis() {
	ch := h.Redis.Subscribe("chat-room-1")
	for msg := range ch {
		for client := range h.Clients {
			client.Send <- msg
		}
	}
}