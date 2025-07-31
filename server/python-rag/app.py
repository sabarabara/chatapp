from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route("/recommend", methods=["POST"])
def recommend():
    data = request.json
    print("Received:", data)
    return jsonify({"recommendations": ["user123", "user456"]})

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)
