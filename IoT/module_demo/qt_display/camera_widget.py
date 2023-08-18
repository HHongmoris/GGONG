from PySide2.QtWidgets import QDialog

from qt_display.ggong2_hw.qr_recog import CameraThread
from qt_display.ggong2_ui.camera_ui import Ui_CameraWidget

from qt_display.msgDTO import msg_instance

class CameraWidget(QDialog,Ui_CameraWidget):

    def __init__(self):
        print("camera_widget init")
        super().__init__()
        self.setupUi(self)
        self.th = None

    def startCameraThread(self):
        if self.th is None:
            print("camera_widget startCameraThread")
            self.th = CameraThread()
            self.th.mySignal.connect(self.setImage)
            self.th.cameraExitSignal.connect(self.cameraClose)
            self.th.start()

    def stopCameraThread(self):
        if self.th:
            print("camera_widget stopCameraThread")
            self.th.cam.release()
            self.th.terminate()
            self.th = None

    def setImage(self, img):
        self.video_display.setPixmap(img)

    def cameraClose(self):
        print("th quit")
        self.stopCameraThread()
        self.close()
