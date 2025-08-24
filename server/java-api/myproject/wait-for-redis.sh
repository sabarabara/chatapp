#!/bin/sh

until redis-cli -h "$REDIS_HOST" -p "$REDIS_PORT" ping 2>/dev/null | grep -q PONG; do
  echo "Waiting for Redis..."
  sleep 1
done

echo "Redis is up, starting the app..."
