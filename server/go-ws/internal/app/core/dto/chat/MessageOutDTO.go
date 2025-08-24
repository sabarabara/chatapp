package chat

import "github.com/google/uuid"
import "encoding/json"
import "log"

type MessageOutDTO struct {
	roomid    uuid.UUID
	messageid string
	message   string
	userid    string
}

func NewMessageOutDTO(roomid uuid.UUID, messageid string, message string, userid string) *MessageOutDTO {
	return &MessageOutDTO{
		roomid:    roomid,
		messageid: messageid,
		message:   message,
		userid:    userid,
	}
}

func (dto *MessageOutDTO) GetRoomID() uuid.UUID {
	return dto.roomid
}
func (dto *MessageOutDTO) GetMessage() string {
	return dto.message
}

func (dto *MessageOutDTO) GetUserID() string {
	return dto.userid
}

func (dto *MessageOutDTO) GetMessageID() string {
	return dto.messageid
}

func (m MessageOutDTO) MarshalJSON() ([]byte, error) {


    return json.Marshal(map[string]interface{}{
        "messageId": m.messageid,
        "message":   m.message,
        "userId":    m.userid,
		"roomId":    m.roomid.String(),
    })
}

func (m *MessageOutDTO) UnmarshalJSON(data []byte) error {

    var tmp struct {
        MessageID string `json:"messageId"`
        Message   string `json:"message"`
        UserID    string `json:"userId"`
		RoomID    string `json:"roomId"`
    }

	log.Println("Raw JSON:", string(data))

    if err := json.Unmarshal(data, &tmp); err != nil {
        return err
    }


    m.message = tmp.Message
    m.userid = tmp.UserID
	m.roomid, _ = uuid.Parse(tmp.RoomID)

    return nil
}