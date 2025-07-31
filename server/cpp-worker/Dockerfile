FROM gcc:12

WORKDIR /app
COPY . .

RUN apt-get update && apt-get install -y wget
RUN g++ main.cpp -o server -pthread

CMD ["./server"]
