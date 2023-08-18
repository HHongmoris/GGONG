from PySide2.QtCore import QThread, Signal
from PySide2.QtGui import QPixmap,QImage
import cv2
import pyzbar.pyzbar as pyzbar
import re

from qt_display.ggong2_signals.qr_signals import qr_signal_instance
from qt_display.msgDTO import msg_instance
class CameraThread(QThread):
    mySignal = Signal(QPixmap)
    cameraExitSignal = Signal()

    def __init__(self):
        super().__init__()
        print("cameraThreead init")
        self.cam = cv2.VideoCapture(0)
        self.cam.set(3, 640)
        self.cam.set(4, 360)

    def run(self):
        print("camera thread run")
        while True:
            ret, self.img = self.cam.read()
            if ret:
                self.printImage(self.img)
        print("camera thread run end")
    def confirm_code(self, plain_code):
        pattern = r'^\d+$'
        user_account = -1

        if re.match(pattern, plain_code):
            user_account = int(plain_code)
            print("your account : ", str(user_account))
        else:
            print("not match for user_account format")
        return user_account

    def printImage(self, imgBGR):
        imgRGB = cv2.cvtColor(imgBGR, cv2.COLOR_BGR2RGB)
        h, w, byte = imgRGB.shape
        img = QImage(imgRGB, w, h, byte * w, QImage.Format_RGB888)
        pix_img = QPixmap(img)

        for code in pyzbar.decode(imgBGR):
            my_code = code.data.decode('utf-8')
            user_code = self.confirm_code(my_code)
            if user_code > 0:
                print("your account : " + str(user_code) + ": auth success , modal terminate start")
                msg_instance.user_no = user_code
                qr_signal_instance.qr_signal.emit()#->publish message
                self.cameraExitSignal.emit()#카메라 종료, emit하지말고 여기서
            else:
                print("plz try again : qr not initialized")
        self.mySignal.emit(pix_img)
