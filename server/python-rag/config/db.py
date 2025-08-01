# config/db.py

import psycopg2
import os

def get_db_connection():
    pg_options = os.getenv("PG_OPTIONS")
    return psycopg2.connect(
        host=os.getenv("PG_HOST"),
        dbname=os.getenv("PG_DB"),
        user=os.getenv("PG_USER"),
        password=os.getenv("PG_PASSWORD"),
        sslmode=os.getenv("PG_SSLMODE", "require"),
        options=f"-c {pg_options}" if pg_options else None
    )