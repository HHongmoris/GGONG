from PySide2.QtWidgets import QApplication
from qt_display.myapp import Myapp

if __name__ == "__main__":
    app = QApplication([])
    win = Myapp()
    win.show()
    app.exec_()
