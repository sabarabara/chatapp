package hub

import (
	"go-ws/internal/app/core/domain/service/interacter/ICachehub"
)

type HubManager struct {
    Hubs  map[string]*Hub
    Redis ICachehub.PubSubClient
}

func NewHubManager(redis ICachehub.PubSubClient) *HubManager {
    return &HubManager{
        Hubs:  make(map[string]*Hub),
        Redis: redis,
    }
}


func (m *HubManager) GetOrCreateHub(roomID string) *Hub {
    if hub, exists := m.Hubs[roomID]; exists {
        return hub // 既存の Hub を返す
    }

    hub := NewHub(roomID, m.Redis)
    m.Hubs[roomID] = hub
    return hub
}
