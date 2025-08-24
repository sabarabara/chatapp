import os
import google.generativeai as genai
from src.similar_users_advice_route.dao import get_user_embedding, get_similar_users
from src.similar_users_advice_route.dao import get_user_hobby



api_key = os.getenv("GEMINI_API_KEY")

if not api_key:
    raise ValueError("環境変数 GEMINI_API_KEY が設定されていません")

genai.configure(api_key=api_key)





def get_similar_users_with_advice(user_id, top_k=3):
    user_embedding = get_user_embedding(user_id)
    if not user_embedding:
        return {"error": "User not found"}
    
    user_hobby = get_user_hobby(user_id)
    similar_users = get_similar_users(user_id, user_embedding, top_k)

    results = []
    for uid, hobby in similar_users:
        prompt = (
            f"自分自身の趣味は「{user_hobby}」です。"
            f"マッチングした相手の趣味は「{hobby}」です。"
            f"相手と話すと良い近い趣味について25文字程度でアドバイス文を生成してください"
            f"また以下に準拠してください"
            f"分かりました。などの返答を生成しないでください"
            f"指定したことのみを生成してください"
        )

        model = genai.GenerativeModel("gemini-1.5-flash")
        response = model.generate_content(prompt)

        # Geminiの返り値は response.text に格納される
        advice = response.text

        return {"user_id": uid, "hobby": hobby, "advice": advice}

