from flask import request, jsonify
import uuid
from src.register_hobby.register_hobby import register_hobby
from src.similar_users_advice_route.similar_users_advice_route import get_similar_users_with_advice

def setup_routes(app):

    @app.route("/register_hobby", methods=["POST"])
    def register():
        data = request.json
        result = register_hobby(data)
        return jsonify(result)

    @app.route("/similar_users_advice", methods=["GET"])
    def similar_users():
        user_id_str = request.args.get("user_id")
        try:
            user_id = uuid.UUID(user_id_str) if user_id_str else None
        except ValueError:
            return {"error": "無効な user_id です"}, 400
        
        top_k = request.args.get("top_k", default=3, type=int)
        result = get_similar_users_with_advice(user_id, top_k)
        return jsonify(result)
