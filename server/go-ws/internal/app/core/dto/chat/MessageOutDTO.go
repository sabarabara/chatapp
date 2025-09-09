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
        "messageid": m.messageid,
        "message":   m.message,
        "userid":    m.userid,
		"roomid":    m.roomid.String(),
    })
}

func (m *MessageOutDTO) UnmarshalJSON(data []byte) error {

    var tmp struct {
        MessageID string `json:"messageid"`
        Message   string `json:"message"`
        UserID    string `json:"userid"`
		RoomID    string `json:"roomid"`
    }

	log.Println("Raw JSON:", string(data))

    if err := json.Unmarshal(data, &tmp); err != nil {
        return err
    }


	m.messageid = tmp.MessageID
    m.message = tmp.Message
    m.userid = tmp.UserID
	m.roomid, _ = uuid.Parse(tmp.RoomID)

    return nil
}