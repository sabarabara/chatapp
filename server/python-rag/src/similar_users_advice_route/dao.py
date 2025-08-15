import uuid
from config.db import get_db_connection

def get_user_embedding(user_id):
    """指定ユーザーの埋め込みベクトルを取得"""
    conn = get_db_connection()
    cur = conn.cursor()
    # UUIDを文字列として渡す
    cur.execute("SELECT embedding FROM user_hobbies WHERE user_id = %s", (str(user_id),))
    row = cur.fetchone()
    cur.close()
    conn.close()
    return row[0] if row else None

def get_similar_users(user_id, embedding, top_k):
    """指定ユーザーと類似したユーザーを取得"""
    conn = get_db_connection()
    cur = conn.cursor()
    cur.execute("""
        SELECT user_id, hobby_text
        FROM user_hobbies
        WHERE user_id != %s
        ORDER BY embedding <-> %s
        LIMIT %s
    """, (str(user_id), embedding, top_k))
    rows = cur.fetchall()
    cur.close()
    conn.close()
    return rows

def get_user_hobby(user_id):
    """指定ユーザーの趣味を取得"""
    conn = get_db_connection()
    cur = conn.cursor()
    cur.execute("SELECT hobby_text FROM user_hobbies WHERE user_id = %s", (str(user_id),))
    row = cur.fetchone()
    cur.close()
    conn.close()
    return row[0] if row else None
