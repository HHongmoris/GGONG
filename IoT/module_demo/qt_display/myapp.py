from PySide2.QtWidgets import QMainWindow, QApplication
from PySide2.QtCore import QTimer, QCoreApplication

from datetime import datetime

from qt_display.member_widget import MemberWidget
from qt_display.ggong2_hw.vote_distance import UltraThread
from qt_display.ggong2_telecom.ggong_mqtt import MqttThread
from qt_display.ggong2_ui.main_ui import Ui_MainWindow

from qt_display.setdata import question_df_instance

from qt_display.ggong2_signals.ultrasonic_signals import ultrasonic_signal_instance
from qt_display.ggong2_signals.received_signals import received_signal_instance
from qt_display.msgDTO import msg_instance
from qt_display.question_data import question_instance

from qt_display.question_follow_web import QuestionManager

class Myapp(QMainWindow, Ui_MainWindow):
    def __init__(self):
        super().__init__()

        self.setupUi(self)
        self.question_id = 1  # my app init 하면 1 (fake(offset적용안된상태))
        #시연상편의
        # self.title_acount = 0
        # self.title_bcount = 0
        self.title_acount = 15
        self.title_bcount = 27


        self.main_acount =0
        self.main_bcount =0
        # QuestionDataFrame 인스턴스 mysql에서 데이터 > 데이터프레임
        question_df_instance.load_data_from_mysql()

        # 1시간마다 질문 변환

        self.my_timer = QTimer()
        self.my_timer.start(3600000)
        self.qm = QuestionManager()

            #1분마다 밑에
        self.one_min_timer = QTimer()
        self.one_min_timer.start(60000)
        self.one_min_timer.timeout.connect(self.one_min_timer_task)
        self.my_timer.timeout.connect(self.timer_task)
        # 질문 id 초기화 필요
        received_signal_instance.received_signal.connect(self.receive_msg)


        # 초음파센서 시작, 꽁초 감지 시 시그널 생성해서 MemberWidget
        print("ultrathread start(myapp)")
        self.th = UltraThread()
        self.th.ultraSignal.connect(self.openMember)
        self.th.ultraSignal.connect(self.vote_task)
        #ultrasonic_signal_instance.ultra_signal.connect(self.openMember)
        self.th.start()

        # ggong2_telecom
        print("mqttthread start(myapp)")
        self.mqtt_thread = MqttThread()
        self.mqtt_thread.start()

        self.timer_task()
        self.one_min_timer_task()
        self.paint_main_count()
    def timer_task(self):
        # 1시간마다 질문 바꾸고 왼쪽 오른쪽 text 변환
        # 현재 시간 가져오기
        now = datetime.now()

        # 이번 주 그룹 번호 계산
        group_value = self.qm.get_this_week_group_num()

        # MySQL에서 해당 그룹의 데이터 로드
        self.qm.load_data_from_mysql(group_value)

        # 현재 및 다음 질문 데이터 가져오기
        question_data = self.qm.get_present_and_next_question(now)

        # 현재 질문 데이터
        current_question = question_data['current_question']

        # UI 갱신
        self.title_question_var.setText(current_question['content'])  # 질문 내용 업데이트
        self.main_atitle_var.setText(current_question['optionA'])  # 선택지 A 업데이트
        self.main_btitle_var.setText(current_question['optionB'])  # 선택지 B 업데이트
        self.foot_nxt_var.setText(current_question['content'])  # 질문 내용 업데이트


        # self.title_acount = 15
        # self.title_bcount = 27

        self.title_acount_var.setText(str(self.title_acount))
        self.title_bcount_var.setText(str(self.title_bcount))


    def one_min_timer_task(self):
        current_time = datetime.now()
        r_minutes = 60 - current_time.minute
        self.foot_timer_var.setText(str(r_minutes)+"분")

        pass

    def paint_main_count(self):
        self.main_acount_var.setText(str(self.main_acount))
        self.main_bcount_var.setText(str(self.main_bcount))
        self.title_acount_var.setText(str(self.title_acount))
        self.title_bcount_var.setText(str(self.title_bcount))
    def vote_task(self,a):
        #초음파센서에서 받아올때마다 화면 갱신하도록
        #main_acount_var : main_acount_var
        if a == 0:
            #좌
            self.main_acount+=1
            self.title_acount += 1
        else:
            #우
            self.main_bcount+=1
            self.title_bcount += 1
        self.paint_main_count()

    def receive_msg(self, ab):
        ab_ans=ab["answer"]
        #vote는 1 2
        if ab_ans>1:
            self.title_acount+=1
            self.title_acount_var.setText(str(self.title_acount))
        else:
            self.title_bcount+=1
            self.title_bcount_var.setText(str(self.title_bcount))

    def openMember(self,a):
        # cellName : int (0 > 좌(40-20) , 1 > 우(20-0) / 오른쪽에서 측정한 거리)
        # 회원, 카메라 촬영 페이지로
        #date초기화
        #msg_instance.vote_date = datetime.now()
        print("openMember called")
        member_view = MemberWidget.get_instance()
        member_view.exec_()
