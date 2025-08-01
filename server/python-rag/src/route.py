from flask import request, jsonify
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
        user_id = request.args.get("user_id", type=int)
        top_k = request.args.get("top_k", default=3, type=int)
        result = get_similar_users_with_advice(user_id, top_k)
        return jsonify(result)
