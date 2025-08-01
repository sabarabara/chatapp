from openai import OpenAI
from src.register_hobby.dao import insert_hobby

client = OpenAI()

def register_hobby(data):
    user_id = data.get("user_id")
    hobby_text = data.get("hobby_text")

    if not user_id or not hobby_text:
        return {"error": "user_id and hobby_text required"}

    embedding = client.embeddings.create(
        model="text-embedding-3-small",
        input=hobby_text
    ).data[0].embedding

    insert_hobby(user_id, hobby_text, embedding)

    return {"status": "hobby registered"}
