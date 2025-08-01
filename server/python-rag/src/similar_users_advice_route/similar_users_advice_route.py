from openai import OpenAI
from src.similar_users_advice_route.dao import get_user_embedding, get_similar_users

client = OpenAI()

def get_similar_users_with_advice(user_id, top_k=3):
    user_embedding = get_user_embedding(user_id)
    if not user_embedding:
        return {"error": "User not found"}

    similar_users = get_similar_users(user_id, user_embedding, top_k)

    results = []
    for uid, hobby in similar_users:
        prompt = f"このユーザーの趣味は「{hobby}」です。このユーザーと話すときに使える一言アドバイスを教えてください。"
        chat = client.chat.completions.create(
            model="gpt-4o-mini",
            messages=[{"role": "user", "content": prompt}]
        )
        advice = chat.choices[0].message.content
        results.append({"user_id": uid, "hobby": hobby, "advice": advice})

    return {"similar_users": results}
