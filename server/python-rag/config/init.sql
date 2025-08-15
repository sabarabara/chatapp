-- config/.sql/init.sql

-- 必要な拡張を有効化
CREATE EXTENSION IF NOT EXISTS vector;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- user_hobbies テーブルを作成
CREATE TABLE IF NOT EXISTS user_hobbies (
    user_id UUID NOT NULL,
    hobby_text TEXT NOT NULL,
    embedding vector(384),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id)
);
