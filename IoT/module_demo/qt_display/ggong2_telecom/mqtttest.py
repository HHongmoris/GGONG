from qt_display.msgDTO import msg_instance
from datetime import datetime
import mysql.connector

my_dist = msg_instance.get_dist()
my_dist["vote_date"]=datetime.now()
# 변수 설정
# user_no = 3
# machine_no = 1
# question_ID = 1
# answer = 3
# point = 10
# buy_no = 1
# current_time = datetime.now()
user_no = my_dist["user_no"]
#machine_no = my_dist["machine_no"]
machine_no = 2
question_ID = my_dist["question_ID"]
answer = my_dist["answer"]
point = 10
buy_no = 1
current_time = my_dist["vote_date"]

try:
    connection = mysql.connector.connect(user='ggong', password='1234', host='i9A304.p.ssafy.io',
                                         database='ggong')
    # connection = mysql.connector.connect(user='root', password='1', host='127.0.0.1', database='ggong')
    if connection.is_connected():
        db_info = connection.get_server_info()
        print('mysql Version : ', db_info)

        cursor = connection.cursor()

        connection.start_transaction()

        query_vote = """
          INSERT INTO vote (user_no, machine_no, question_ID, vote_date, answer)
          VALUES (%s, %s, %s, %s, %s);
          """
        cursor.execute(query_vote, (user_no, machine_no, question_ID, current_time, answer))

        vote_no = cursor.lastrowid

        cursor.execute("SELECT balance_point FROM point WHERE user_no = %s ORDER BY point_no DESC LIMIT 1",
                       (user_no,))
        last_balance_point = cursor.fetchone()
        if last_balance_point:
            last_balance_point = last_balance_point[0]
        else:
            last_balance_point = 0

        new_balance_point = last_balance_point + point

        query_point = """
          INSERT INTO point (event_time, point, balance_point, vote_no, user_no, buy_no)
          VALUES (%s, %s, %s, %s, %s, %s);
          """
        cursor.execute(query_point, (current_time, point, new_balance_point, vote_no, user_no, buy_no))

        # 등급 갱신
        # "(회원 등급) - 6단계 (씨앗(0point), 새싹(100point), 잎새(500point), 가지(1000point), 나무(10000point), 숲(100000point))
        # 누적포인트에 따라 해당 회원 등급 프론트로 전송
        # (누적 포인트 바뀔 때(포인트 적립 발생 시) 자동적으로 등급도 바뀌게 쿼리 짜기)"
        # User _ user_rating vchar
        # seed, sprout, leaf, branch, tree, forest
        tem_rate = "seed"
        if new_balance_point > 100000:
            tem_rate = "forest"
        elif new_balance_point > 10000:
            tem_rate = "tree"
        elif new_balance_point > 1000:
            tem_rate = "branch"
        elif new_balance_point > 500:
            tem_rate = "leaf"
        elif new_balance_point > 100:
            tem_rate = "sprout"
        else:
            tem_rate = "seed"


        connection.commit()
except mysql.connector.Error as e:
    connection.rollback()
    print('Database Error: ', e)

finally:
    if connection.is_connected():
        cursor.close()
        connection.close()
        print('MySQL Connection Close')