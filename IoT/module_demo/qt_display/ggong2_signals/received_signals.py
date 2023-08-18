from PySide2.QtCore import Signal, QObject

class ReceivedSignals(QObject):
    received_signal = Signal(dict)  # qr 전송 시그널
    _instance = None

    def __new__(cls):
        if cls._instance is None:
            cls._instance = super(ReceivedSignals, cls).__new__(cls)
        return cls._instance


received_signal_instance = ReceivedSignals()