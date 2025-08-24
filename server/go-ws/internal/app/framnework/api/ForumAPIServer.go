package api

import (
	"bytes"
	"encoding/json"
	"fmt"
	"net/http"

	"go-ws/internal/app/core/dto/forum"
)

type ForumAPIServer struct {
	client  *http.Client
	baseURL string
}

func NewForumAPIServer(client *http.Client, baseURL string) *ForumAPIServer {
	return &ForumAPIServer{
		client:  client,
		baseURL: baseURL,
	}
}

func (s *ForumAPIServer) FetchForumInformation(dto *forum.ForumInformDTO) (string, error) {
	url := s.baseURL + "/forum/information"

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

func (s *ForumAPIServer) SendMessage(dto *forum.MessageOutNonidDTO) (string, error) {
	url := s.baseURL + "/forum/create"

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

	var uuidStr string
	if err := json.NewDecoder(resp.Body).Decode(&uuidStr); err != nil {
		return "", err
	}
	return uuidStr, nil
}

func (s *ForumAPIServer) ReceiveMessage(dto *forum.MessageInDTO) (string, error) {
	url := s.baseURL + "/forum/message/receive"

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
