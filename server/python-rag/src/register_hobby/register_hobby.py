from sentence_transformers import SentenceTransformer
from src.register_hobby.dao import insert_hobby

# アプリ起動時にモデルを一度だけ読み込む
model = SentenceTransformer('sentence-transformers/all-MiniLM-L6-v2')

def register_hobby(data):
    user_id = data.get("user_id")
    hobby_text = data.get("hobby_text")

    if not user_id or not hobby_text:
        return {"error": "user_id and hobby_text required"}

    # Hugging Face ローカルモデルで埋め込み生成
    embedding = model.encode(hobby_text).tolist()

    # DBに保存
    insert_hobby(user_id, hobby_text, embedding)

    return {"status": "hobby registered"}
