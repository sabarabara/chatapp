package chat

import "github.com/google/uuid"
import "encoding/json"

type MessageParseDTO struct {
	roomid    uuid.UUID
	message   string
}

func NewMessageParseDTO(roomid uuid.UUID, message string, userid string) *MessageParseDTO {
	return &MessageParseDTO{
		roomid: roomid,
		message: message,
	}
}

func (dto *MessageParseDTO) GetRoomID() uuid.UUID {
	return dto.roomid
}
func (dto *MessageParseDTO) GetMessage() string {
	return dto.message
}


func (m MessageParseDTO) MarshalJSON() ([]byte, error) {

	return json.Marshal(map[string]interface{}{
		"_message":   m.message,
		"_roomID":    m.roomid.String(),
	})
}

func (m *MessageParseDTO) UnmarshalJSON(data []byte) error {

    var tmp struct {
        Message string `json:"_message"`
        RoomID  string `json:"_roomID"`
    }

    if err := json.Unmarshal(data, &tmp); err != nil {
        return err
    }

    m.message = tmp.Message
    m.roomid, _ = uuid.Parse(tmp.RoomID)

    return nil
}