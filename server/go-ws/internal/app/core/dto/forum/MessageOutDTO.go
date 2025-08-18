package forum

import (
	"github.com/google/uuid"
	"encoding/json"
)


type MessageOutDTO struct {
	roomid  uuid.UUID
	userid  string
	message string
}

func NewMessageOutDTO(roomid uuid.UUID, userid string, message string) *MessageOutDTO {
	return &MessageOutDTO{
		roomid:  roomid,
		userid:  userid,
		message: message,
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

func (m MessageOutDTO) MarshalJSON() ([]byte, error) {


    return json.Marshal(map[string]interface{}{
        "message": m.message,
        "userId":  m.userid,
		"roomId":  m.roomid.String(),
    })
}

func (m *MessageOutDTO) UnmarshalJSON(data []byte) error {
	
    var tmp struct {
        Message string `json:"message"`
        UserID  string `json:"userId"`
		RoomID  string `json:"roomId"`
    }

    if err := json.Unmarshal(data, &tmp); err != nil {
        return err
    }


    m.message = tmp.Message
    m.userid = tmp.UserID
	m.roomid, _ = uuid.Parse(tmp.RoomID)

    return nil
}