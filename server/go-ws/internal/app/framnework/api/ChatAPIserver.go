package api

import (
	"bytes"
	"encoding/json"
	"fmt"
	"net/http"
	"go-ws/internal/app/core/dto/chat"
)

type ChatAPIserver struct {
	client  *http.Client
	baseURL string
}

func NewChatAPIserver(client *http.Client, baseURL string) *ChatAPIserver {
	return &ChatAPIserver{
		client:  client,
		baseURL: baseURL,
	}
}

func (s *ChatAPIserver) FetchChatInformation(dto *chat.ChatInformDTO) (string, error) {
	url := s.baseURL + "/chat/info"

	body, err := json.Marshal(dto)
	if err != nil {
		return "", err
	}

	resp, err := s.client.Post(url, "application/json", bytes.NewBuffer(body))
	if err != nil {
		return "", err
	}
	defer resp.Body.Close()

	if resp.StatusCode != http.StatusOK {
		return "", fmt.Errorf("failed: %s", resp.Status)
	}

	return "OK", nil
}

func (s *ChatAPIserver) SendMessage(dto *chat.MessageOutDTO) (string, error) {
	url := s.baseURL + "/chat/message/send"

	body, err := json.Marshal(dto)
	if err != nil {
		return "", err
	}

	resp, err := s.client.Post(url, "application/json", bytes.NewBuffer(body))
	if err != nil {
		return "", err
	}
	defer resp.Body.Close()

	if resp.StatusCode != http.StatusOK {
		return "", fmt.Errorf("failed: %s", resp.Status)
	}

	return "OK", nil
}

func (s *ChatAPIserver) ReceiveMessage(dto *chat.MessageInDTO) (string, error) {
	url := s.baseURL + "/chat/message/receive"

	body, err := json.Marshal(dto)
	if err != nil {
		return "", err
	}

	resp, err := s.client.Post(url, "application/json", bytes.NewBuffer(body))
	if err != nil {
		return "", err
	}
	defer resp.Body.Close()

	if resp.StatusCode != http.StatusOK {
		return "", fmt.Errorf("failed: %s", resp.Status)
	}

	return "OK", nil
}
