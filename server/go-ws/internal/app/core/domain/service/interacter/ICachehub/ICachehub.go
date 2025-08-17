package ICachehub

type PubSubClient interface {
    Publish(channel string, message []byte) error
    Subscribe(channel string) <-chan []byte
}
