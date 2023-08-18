from PySide2.QtCore import Signal, QObject

class QRSignals(QObject):
    qr_signal = Signal()  # qr 전송 시그널
    _instance = None

    def __new__(cls):
        if cls._instance is None:
            cls._instance = super(QRSignals, cls).__new__(cls)
        return cls._instance



qr_signal_instance = QRSignals()