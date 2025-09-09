package chat

import "github.com/google/uuid"
import "encoding/json"

type MessageOutNonidDTO struct {
	roomid    uuid.UUID
	message   string
	userid    string
}

func NewMessageOutNonidDTO(roomid uuid.UUID, message string, userid string) *MessageOutNonidDTO {
	return &MessageOutNonidDTO{
		roomid: roomid,
		message: message,
		userid: userid,
	}
}

func (dto *MessageOutNonidDTO) GetRoomID() uuid.UUID {
	return dto.roomid
}
func (dto *MessageOutNonidDTO) GetMessage() string {
	return dto.message
}

func (dto *MessageOutNonidDTO) GetUserID() string {
	return dto.userid
}

func (m MessageOutNonidDTO) MarshalJSON() ([]byte, error) {

	return json.Marshal(map[string]interface{}{
		"message":   m.message,
		"userid":    m.userid,
		"roomid":    m.roomid.String(),
	})
}

func (m *MessageOutNonidDTO) UnmarshalJSON(data []byte) error {

    var tmp struct {
        Message string `json:"message"`
        UserID  string `json:"userid"`
        RoomID  string `json:"roomid"`
    }

    if err := json.Unmarshal(data, &tmp); err != nil {
        return err
    }

    m.message = tmp.Message
    m.userid = tmp.UserID
    m.roomid, _ = uuid.Parse(tmp.RoomID)

    return nil
}