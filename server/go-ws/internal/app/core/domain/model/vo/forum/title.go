package forum

type Title struct {
	title string
}

func NewTitle(title string) *Title {
	return &Title{
		title: title,
	}
}

func (t *Title) GetTitle() string {
	return t.title
}
