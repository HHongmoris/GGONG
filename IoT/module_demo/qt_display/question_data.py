
class QuestionDTO:
    _instance = None
    def __new__(cls):
        if cls._instance is None:
            cls._instance = super(QuestionDTO, cls).__new__(cls)
            cls._instance._init_data()
        return cls._instance

    def _init_data(self):
        self.temp_question = ""
        self.next_question = ""
        # 32보다 커지면 1로
        self.temp_question_id = 1
        self.next_question_id = 2  # temp_question_id + 1,

    #int, int
    def plus_hour(self):
        self.temp_question_id += 1
        self.next_question_id = self.temp_question_id + 1

        if (self.next_question_id > 32):
            self.temp_question_id = 32
            self.next_question_id = 1

        if (self.temp_question_id > 32):
            self.temp_question_id = 1
            self.next_question_id = 2

        return (self.temp_question_id, self.next_question_id)

    def get_questions(self):
        return (self.temp_question_id, self.next_question_id)

question_instance = QuestionDTO()