from PySide2.QtCore import Signal, QObject
from PySide2.QtGui import QPixmap

class CameraSignals(QObject):
    camera_video_signal = Signal(QPixmap)  # 카메라 실시간 영상 보내주는 시그널
    camera_exit_signal = Signal(int)  # 카메라 off 시그널

    _instance = None
    def __new__(cls):
        if cls._instance is None:
            cls._instance = super(CameraSignals, cls).__new__(cls)
        return cls._instance

camera_signal_instance = CameraSignals()