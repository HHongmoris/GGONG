from PySide2.QtCore import Signal, QObject

class AnonymousSignals(QObject):
    anonymous_signal = Signal()  # anonymous -> mqtt 전송 시그널

    _instance = None

    def __new__(cls):
        if cls._instance is None:
            cls._instance = super(AnonymousSignals, cls).__new__(cls)
        return cls._instance

anonymous_signal_instance = AnonymousSignals()