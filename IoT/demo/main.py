from PySide2.QtWidgets import *
from PySide2.QtGui import *
from PySide2.QtCore import *

#UI
from main_ui import Ui_MainWindow
from camera_ui import Ui_CameraWidget
from member_ui import Ui_MemberWidget
#rpi 카메라, qr
from rpicamera import qr_recog
import pyzbar.pyzbar as pyzbar
import cv2
from time import *

# 초음파센서
import RPi.GPIO as gpio
import time


#초음파 센서 전역변수
TRIGER = 24
ECHO = 23
gpio.setmode(gpio.BCM)
gpio.setup(TRIGER, gpio.OUT)
gpio.setup(ECHO, gpio.IN)

#UltraSocnic
class UltraThread(QThread):
    #시그널 이름 = Signal(타입)
    ultraSignal = Signal(int) # openMember
    #-1 > 없음
    #0 > 좌
    #1 > 우
    def __init__(self):
        super().__init__()

    def run(self):
        startTime = time.time()
# 초음파 run 코드
        try:
            while True:
                gpio.output(TRIGER, gpio.LOW)
                time.sleep(0.1)
                gpio.output(TRIGER, gpio.HIGH)
                time.sleep(0.00002)
                gpio.output(TRIGER, gpio.LOW)

                while gpio.input(ECHO) == gpio.LOW:
                    startTime = time.time()  # 1sec unit

                while gpio.input(ECHO) == gpio.HIGH:
                    endTime = time.time()

                period = endTime - startTime
                # 거리 계산 (단위: cm, 음속 340m/s 기준)
                dist = round(period * 17241, 2)

                # 간격 : 20cm
                # 40 - 20 | 20 - 0
                # 0       | 1
                if dist < 40:
                    if dist > 20:
                        self.ultraSignal.emit(1)
                        sleep(1)
                    else:
                        self.ultraSignal.emit(2)
                        sleep(1)
        except:
            gpio.cleanup()


class CameraThread(QThread):
    mySignal = Signal(QPixmap)
    cameraExitSignal = Signal(int)
    def __init__(self):
        super().__init__()
        self.cam = cv2.VideoCapture(0)
        self.cam.set(3, 640)
        self.cam.set(4, 360)

    def run(self):
        while True:
            ret, self.img = self.cam.read()
            if ret:
                self.printImage(self.img)
            sleep(0.1)

    def printImage(self, imgBGR):
        imgRGB = cv2.cvtColor(imgBGR, cv2.COLOR_BGR2RGB)
        h, w, byte = imgRGB.shape
        img = QImage(imgRGB, w, h, byte * w, QImage.Format_RGB888)
        pix_img = QPixmap(img)#QPixmap -> GUI에 이미지를 표시하는데 사용

        #QR 코드 스캔 파트
        for code in pyzbar.decode(imgBGR):
            my_code = code.data.decode('utf-8')
            user_code = -2
            user_code = qr_recog.confirm_code(my_code)
            #if user_code != "error":
            if user_code > 0 :
                #print("email : " + user_code + " : auth success , modal terminate start")
                print("your account : " + str(user_code) + ": auth success , modal terminate start")
                self.cameraExitSignal.emit(user_code)
            else :
                print("plz try again : qr not initialized")

        self.mySignal.emit(pix_img)



class MemberWidget(QDialog, Ui_MemberWidget):
    _instance = None

    def __init__(self):
        if MemberWidget._instance is not None:
            raise RuntimeError("Only one instance of MemberWidget is allowed.")
        super().__init__()
        self.setupUi(self)
        MemberWidget._instance = self

    @staticmethod
    def get_instance():
        if MemberWidget._instance is None:
            MemberWidget._instance = MemberWidget()
        return MemberWidget._instance

    # 회원, 카메라 촬영 페이지로
    def clickMember(self):
        # 카메라 촬영 버튼 연결 함수
        camera_view = CameraWidget()
        camera_view.startCameraThread()
        self.close()
        camera_view.exec_()
    # 비회원, 초기 페이지로 이동
    def clickAnonymous(self):

        self.close()

    def memberClose(self):
        self.close()
class CameraWidget(QDialog, Ui_CameraWidget):
    def __init__(self):
        super().__init__()
        self.setupUi(self)
        self.th = None

    def startCameraThread(self):
        if self.th is None:
            self.th = CameraThread()
            self.th.mySignal.connect(self.setImage)
            self.th.cameraExitSignal.connect(self.cameraClose)
            self.th.start()
    def stopCameraThread(self):
        if self.th:
            self.th.cam.release()
            self.th.terminate()
            self.th = None
    def setImage(self, img):
        self.video_display.setPixmap(img)#QT에 촬영된 화면 표시

    def cameraClose(self,usrCode = 0): # 카메라 촬영 모달 종료 cameraexitSignal
        print(str(usrCode)+"th quit")
        self.stopCameraThread()
        self.close()


class Myapp(QMainWindow, Ui_MainWindow):
    def __init__(self):
        super().__init__()
        self.setupUi(self)
        #초음파센서 꽁초 감지 시 시그널 생성해서 MemberWidget
        self.th = UltraThread()
        self.th.ultraSignal.connect(self.openMember)
        self.th.start()



    def openMember(self,voteNum):
        #cellName : int (0 > 좌(40-20) , 1 > 우(20-0) / 오른쪽에서 측정한 거리)
        #회원, 카메라 촬영 페이지로
        print(str(voteNum)+"선택")
        member_view = MemberWidget.get_instance()
        member_view.exec_()


app = QApplication()
win = Myapp()

win.show()
app.exec_()
