FROM gcc:12

WORKDIR /app
COPY . .

RUN apt-get update && apt-get install -y wget
RUN g++ -std=c++17 main.cpp src/algorithm.cpp src/algologic/birhtday/birthday.cpp src/algologic/bloodtype/bloodtype.cpp src/algologic/charactertypedecide/decidechara.cpp src/algologic/dominanthand/dominanthand.cpp src/algologic/favoritecolor/favoritecolor.cpp src/algologic/favoriteweather/favoriteweather.cpp src/algologic/height/height.cpp -pthread -o server

CMD ["./server"]
