FROM openjdk:17-slim

WORKDIR /app

# 必要なツールをインストール
RUN apt-get update && apt-get install -y findutils redis-tools
RUN apt-get update && apt-get install -y curl

# プロジェクトをコピー
COPY . .

# 環境変数設定
RUN chmod +x ./wait-for-redis.sh

# ビルド
RUN ./gradlew build --no-daemon

# ポート公開
EXPOSE 8080

CMD ["sh", "-c", "./wait-for-redis.sh && java -Djava.net.preferIPv4Stack=true -Dspring.redis.host=${REDIS_HOST} -Dspring.redis.port=${REDIS_PORT} -jar build/libs/myproject-0.0.1-SNAPSHOT.jar"]


    
