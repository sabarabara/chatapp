FROM golang:1.24
WORKDIR /app
RUN apt-get update && apt-get install -y curl
COPY go.mod go.sum ./
RUN go mod download

COPY . .
RUN go build -o ws-server ./cmd

EXPOSE 8080

CMD ["./ws-server"]
