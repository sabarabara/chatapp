package session

type UserID struct {
	userid string
}

func NewUserID(userid string) *UserID {
	return &UserID{
		userid: userid,
	}
}

func (u *UserID) GetUserID() string {
	return u.userid
}
