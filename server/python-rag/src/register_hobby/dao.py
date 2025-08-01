from config.db import get_db_connection

def insert_hobby(user_id, hobby_text, embedding):
    conn = get_db_connection()
    cur = conn.cursor()
    cur.execute(
        "INSERT INTO user_hobbies (user_id, hobby_text, embedding) VALUES (%s, %s, %s)",
        (user_id, hobby_text, embedding)
    )
    conn.commit()
    cur.close()
    conn.close()
