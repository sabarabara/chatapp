FROM openjdk:17-slim

WORKDIR /app
COPY . .

RUN apt-get update && apt-get install -y findutils
RUN ./gradlew build

CMD ["java", "-jar", "build/libs/myproject-0.0.1-SNAPSHOT.jar"]

