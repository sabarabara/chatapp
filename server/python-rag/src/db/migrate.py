import psycopg2
import os

def get_db_connection():
    return psycopg2.connect(
        host=os.getenv("PG_HOST"),
        dbname=os.getenv("PG_DB"),
        user=os.getenv("PG_USER"),
        password=os.getenv("PG_PASSWORD"),
        sslmode=os.getenv("PG_SSLMODE", "require"),
        options=f"-c {os.getenv('PG_OPTIONS')}" if os.getenv("PG_OPTIONS") else None
    )


def run_migration():
    conn = get_db_connection()
    cur = conn.cursor()
    with open("config/init.sql", "r") as f:
        sql = f.read()
    cur.execute(sql)
    conn.commit()
    cur.close()
    conn.close()
