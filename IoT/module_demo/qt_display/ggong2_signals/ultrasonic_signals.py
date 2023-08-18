from PySide2.QtCore import Signal, QObject

class UltrasonicSignals(QObject):
    ultra_signal = Signal(int)  # 초음파 센서의 측정 결과를 메인 스레드로 전송하는 시그널
    _instance = None

    def __new__(cls):
        if cls._instance is None:
            print("ultrasonic_signals.py: __new__")
            cls._instance = super(UltrasonicSignals, cls).__new__(cls)
        else:
            print("ultrasonic_signals.py: __new__ else")

        return cls._instance

ultrasonic_signal_instance = UltrasonicSignals()