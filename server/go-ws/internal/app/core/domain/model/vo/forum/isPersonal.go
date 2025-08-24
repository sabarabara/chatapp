package forum

type IsPersonal struct {
	isperson bool
}

func NewIsPersonal(isperson bool) *IsPersonal {
	return &IsPersonal{
		isperson: isperson,
	}
}

func (ip *IsPersonal) IsPersonal() bool {
	return ip.isperson
}