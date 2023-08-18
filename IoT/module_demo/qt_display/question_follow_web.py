import pandas as pd
from datetime import datetime
from sqlalchemy import create_engine


class QuestionManager:
    _instance = None

    def __init__(self):
        self._data = pd.DataFrame(columns=['question_ID', 'content', 'group', 'category', 'optionA', 'optionB', 'type'])

    def __new__(cls):
        if cls._instance is None:
            cls._instance = super(QuestionManager, cls).__new__(cls)
            cls._instance._init_data()
        return cls._instance

    def _init_data(self):
        self._data = pd.DataFrame(columns=['question_ID', 'content', 'group', 'category', 'optionA', 'optionB', 'type'])

    def get_this_week_group_num(self):
        this_week = datetime.now().isocalendar()[1]
        this_week = this_week % 10
        if this_week == 0:
            return 10
        return this_week

    def load_data_from_mysql(self, group_value):
        try:
            connection_str = 'mysql+mysqlconnector://ggong:1234@i9A304.p.ssafy.io/ggong'
            engine = create_engine(connection_str)

            query = "SELECT * FROM question WHERE `group` = %s"
            self._data = pd.read_sql(query, engine, params=(group_value,))

        except Exception as err:
            print("Error: {}".format(err))

    def get_present_and_next_question(self, now: datetime):
        day_to_int = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3, 'Friday': 4, 'Saturday': 5, 'Sunday': 6}

        int_day = now.hour + (24 * day_to_int[now.strftime('%A')])
        idx_day = int_day % 21

        current_group_num = self.get_this_week_group_num()

        current_question_data = self._data[self._data['group'] == current_group_num].iloc[idx_day]

        current_question = {
            'content': current_question_data['content'],
            'optionA': current_question_data['optionA'],
            'optionB': current_question_data['optionB']
        }

        next_idx_day = (idx_day + 1) % 21
        next_question_data = self._data[self._data['group'] == current_group_num].iloc[next_idx_day]

        next_question_content = next_question_data['content']

        res = {
            'current_question': current_question,
            'next_question_content': next_question_content
        }

        return res


# 테스트
qm = QuestionManager()
group_value = qm.get_this_week_group_num()
qm.load_data_from_mysql(group_value)
now = datetime.now()
print(qm.get_present_and_next_question(now))
