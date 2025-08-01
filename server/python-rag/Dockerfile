# path: ./python-rag/Dockerfile

FROM python:3.11-slim

# 必要パッケージのインストール（PostgreSQLに必要）
RUN apt-get update && apt-get install -y gcc libpq-dev && rm -rf /var/lib/apt/lists/*

# 作業ディレクトリの設定
WORKDIR /app
ENV PYTHONPATH=/app

# 依存関係のインストール
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# アプリケーションのコピー
COPY . .

# ポート指定（必要に応じて変更）
EXPOSE 5000

# アプリ起動
CMD ["python", "app.py"]
