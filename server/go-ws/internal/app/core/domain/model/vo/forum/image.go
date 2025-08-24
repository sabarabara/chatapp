package forum

import (
	"encoding/base64"
)

type Image struct {
	data []byte
}

func NewImage(data []byte) *Image {
	return &Image{
		data: data,
	}
}

func (img *Image) GetData() []byte {
	return img.data
}

func (img *Image) GetBase64() string {
	return base64.StdEncoding.EncodeToString(img.data)
}