package forum

import (
	"github.com/google/uuid"
	"encoding/json"
)


type MessageOutNonidDTO struct {
	roomid  uuid.UUID
	userid  string
	message string
}

func NewMessageOutNonidDTO(roomid uuid.UUID, userid string, messageid string) *MessageOutNonidDTO {
	return &MessageOutNonidDTO{
		roomid:   roomid,
		userid:   userid,
		message: messageid,
	}
}

func (dto *MessageOutNonidDTO) GetRoomID() uuid.UUID {
	return dto.roomid
}

func (dto *MessageOutNonidDTO) GetUserID() string {
	return dto.userid
}

func (dto *MessageOutNonidDTO) GetMessage() string {
	return dto.message
}

func (m MessageOutNonidDTO) MarshalJSON() ([]byte, error) {


    return json.Marshal(map[string]interface{}{
        "message": m.message,
        "userId":  m.userid,
        "roomId":  m.roomid.String(),
    })
}

func (m *MessageOutNonidDTO) UnmarshalJSON(data []byte) error {
	
    var tmp struct {
        MessageID string `json:"messageId"`
        UserID    string `json:"userId"`
        RoomID    string `json:"roomId"`
    }

    if err := json.Unmarshal(data, &tmp); err != nil {
        return err
    }

    m.message = tmp.MessageID
    m.userid = tmp.UserID
    m.roomid, _ = uuid.Parse(tmp.RoomID)

    return nil
}