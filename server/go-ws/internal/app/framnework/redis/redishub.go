package hub

import (
	"go-ws/internal/app/core/domain/service/interacter/ICachehub"
)


type Client struct {
	Send chan []byte
}

type Hub struct {
    RoomID    string
    Clients   map[*Client]bool
    Broadcast chan []byte
    Redis     ICachehub.PubSubClient
}

func NewHub(roomID string, redis ICachehub.PubSubClient) *Hub {
    h := &Hub{
        RoomID:    roomID,
        Clients:   make(map[*Client]bool),
        Broadcast: make(chan []byte),
        Redis:     redis,
    }
    go h.run(roomID)
    go h.listenRedis(roomID)
    return h
}


func (h *Hub) run(roomID string) {
	for msg := range h.Broadcast {
		h.Redis.Publish(roomID, msg)
	}
}

func (h *Hub) listenRedis(roomID string) {
	ch := h.Redis.Subscribe(roomID)
	for msg := range ch {
		for client := range h.Clients {
			client.Send <- msg
		}
	}
}