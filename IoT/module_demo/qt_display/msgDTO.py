
class MqttMessageDTO:
    _instance = None
    def __new__(cls):
        if cls._instance is None:
            cls._instance = super(MqttMessageDTO, cls).__new__(cls)
        return cls._instance
    def __init__(self):
        self.user_no = 1 #int
        self.machine_no = 2 #int
        self.question_ID = 1 #int
        self.vote_date = "" #datetime
        self.answer = 1 #tinyint
        self.question_offset = 0 # setdata, 질문풀이 바뀌지 않는 한 고정
    def set_vote(self, u_no,m_no,q_id,v_date,ans):
        self.user_no = u_no  # int
        self.machine_no = m_no  # int
        self.question_ID = q_id  # int
        self.vote_date = v_date  # datetime
        self.answer = ans  # tinyint

    def get_dist(self):
        ret_dist = {

                "user_no": int(self.user_no),
                "machine_no" : int(self.machine_no),
                "question_ID" : int(self.question_ID+self.question_offset),
                "vote_date" : str(self.vote_date),
                "answer" : int(self.answer),

        }
        return ret_dist

    def get_real_question_id(self):
        return self.question_offset+self.question_ID

msg_instance = MqttMessageDTO()