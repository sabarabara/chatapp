import sys
sys.path.append("/app")

from dotenv import load_dotenv
load_dotenv()

from flask import Flask
from src.db.migrate import run_migration
from src.route import setup_routes

app = Flask(__name__)

run_migration()
setup_routes(app)

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)
