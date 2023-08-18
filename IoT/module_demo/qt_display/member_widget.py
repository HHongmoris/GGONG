from PySide2.QtWidgets import QDialog
from qt_display.camera_widget import CameraWidget
from qt_display.ggong2_ui.member_ui import Ui_MemberWidget
from qt_display.ggong2_signals.anonymous_signals import anonymous_signal_instance

class MemberWidget(QDialog, Ui_MemberWidget):
    _instance = None

    def __init__(self):
        if MemberWidget._instance is not None:
            raise RuntimeError("Member_widget : Do not trying create multiple MemberWidget instance.")
        super().__init__()
        self.setupUi(self)
        MemberWidget._instance = self


    @staticmethod
    def get_instance():
        if MemberWidget._instance is None:
            print("member_widget is none")
            MemberWidget._instance = MemberWidget()
        else:
            print("member_widget is not none")
        return MemberWidget._instance

    def clickMember(self):
        camera_view = CameraWidget()
        camera_view.startCameraThread()
        self.close()
        camera_view.exec_()

    def clickAnonymous(self):
        #ggong2_telecom 데이터 전송 시그널 ->
        anonymous_signal_instance.anonymous_signal.emit()# auto incre -> 1 익명으로
        self.close()

    def memberClose(self):
        self.close()
