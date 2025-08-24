package forum

type Username struct {
	username string
}

func NewUsername(username string) *Username {
	return &Username{
		username: username,
	}
}

func (u *Username) GetUsername() string {
	return u.username
}
