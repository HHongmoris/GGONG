from PySide2.QtCore import QThread, Signal
import RPi.GPIO as gpio
import time
from datetime import datetime
from qt_display.msgDTO import msg_instance


TRIGER = 24
ECHO = 23
gpio.setmode(gpio.BCM)
gpio.setup(TRIGER, gpio.OUT)
gpio.setup(ECHO, gpio.IN)

class UltraThread(QThread):
    ultraSignal = Signal(int)  # 초음파 센서의 측정 결과를 메인 스레드로 전송하는 시그널

    def __init__(self):
        super().__init__()

    def run(self):
        print("ultra thread run ")
        try:
            while True:
                gpio.output(TRIGER, gpio.LOW)
                time.sleep(0.1)
                gpio.output(TRIGER, gpio.HIGH)
                time.sleep(0.00002)
                gpio.output(TRIGER, gpio.LOW)

                while gpio.input(ECHO) == gpio.LOW:
                    startTime = time.time()

                while gpio.input(ECHO) == gpio.HIGH:
                    endTime = time.time()

                period = endTime - startTime
                dist = round(period * 17241, 2)
                print(dist)
                if dist < 40:
                    if dist < 20:
                        #emit 안하고 dto 인스턴스(singleton) 수정
                        print("vote 1")
                        #3번쨰 qid
                        msg_instance.set_vote(1,2,1,datetime.now(),0)
                        self.ultraSignal.emit(0)#좌
                        time.sleep(1)
                    else:
                        print("vote 2")
                        msg_instance.set_vote(1, 2, 1, datetime.now(), 1)
                        self.ultraSignal.emit(1)#우
                        time.sleep(1)
        except:
            gpio.cleanup()
