from config.db import get_db_connection

def get_user_embedding(user_id):
    conn = get_db_connection()
    cur = conn.cursor()
    cur.execute("SELECT embedding FROM user_hobbies WHERE user_id = %s", (user_id,))
    row = cur.fetchone()
    cur.close()
    conn.close()
    return row[0] if row else None

def get_similar_users(user_id, embedding, top_k):
    conn = get_db_connection()
    cur = conn.cursor()
    cur.execute("""
        SELECT user_id, hobby_text
        FROM user_hobbies
        WHERE user_id != %s
        ORDER BY embedding <-> %s
        LIMIT %s
    """, (user_id, embedding, top_k))
    rows = cur.fetchall()
    cur.close()
    conn.close()
    return rows
