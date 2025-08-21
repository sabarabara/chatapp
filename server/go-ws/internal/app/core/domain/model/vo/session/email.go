package session

type Email struct {
	email string
}

func NewEmail(email string) *Email {
	return &Email{
		email: email,
	}
}

func (e *Email) GetEmail() string {
	return e.email
}