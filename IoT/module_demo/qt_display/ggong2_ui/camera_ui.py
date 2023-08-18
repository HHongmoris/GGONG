# -*- coding: utf-8 -*-

################################################################################
## Form generated from reading UI file 'camera.ui'
##
## Created by: Qt User Interface Compiler version 6.5.2
##
## WARNING! All changes made in this file will be lost when recompiling UI file!
################################################################################

from PySide2.QtCore import (QCoreApplication, QDate, QDateTime, QLocale,
    QMetaObject, QObject, QPoint, QRect,
    QSize, QTime, QUrl, Qt)
from PySide2.QtGui import (QBrush, QColor, QConicalGradient, QCursor,
    QFont, QFontDatabase, QGradient, QIcon,
    QImage, QKeySequence, QLinearGradient, QPainter,
    QPalette, QPixmap, QRadialGradient, QTransform)
from PySide2.QtWidgets import (QApplication, QLabel, QPushButton, QSizePolicy,
    QWidget)

class Ui_CameraWidget(object):
    def setupUi(self, CameraWidget):
        if not CameraWidget.objectName():
            CameraWidget.setObjectName(u"CameraWidget")
        CameraWidget.setWindowModality(Qt.ApplicationModal)
        CameraWidget.resize(1050, 550)
        CameraWidget.setMinimumSize(QSize(1050, 550))
        CameraWidget.setMaximumSize(QSize(1050, 550))
        CameraWidget.setStyleSheet(u"background-color: rgb(249, 212, 66);")
        self.video_display = QLabel(CameraWidget)
        self.video_display.setObjectName(u"video_display")
        self.video_display.setGeometry(QRect(180, 150, 640, 360))
        self.video_display.setMinimumSize(QSize(640, 360))
        self.video_display.setMaximumSize(QSize(640, 360))
        self.video_display.setStyleSheet(u"background-color: rgb(255, 0, 0);\n"
"border-radius : 10px;")
        self.label = QLabel(CameraWidget)
        self.label.setObjectName(u"label")
        self.label.setGeometry(QRect(180, 30, 641, 91))
        font = QFont()
        font.setPointSize(24)
        self.label.setFont(font)
        self.label.setStyleSheet(u"background-color: rgb(255, 255, 255);border: 3px solid;\n"
"border-color: rgb(161, 161, 170);\n"
"border-radius : 10px;")
        self.label.setAlignment(Qt.AlignCenter)
        self.camera_btn = QPushButton(CameraWidget)
        self.camera_btn.setObjectName(u"camera_btn")
        self.camera_btn.setGeometry(QRect(910, 440, 71, 71))
        font1 = QFont()
        font1.setPointSize(10)
        font1.setBold(True)
        self.camera_btn.setFont(font1)
        self.camera_btn.setStyleSheet(u"background-color: rgb(255, 255, 255);\n"
"")

        self.retranslateUi(CameraWidget)
        self.camera_btn.clicked.connect(CameraWidget.cameraClose)

        QMetaObject.connectSlotsByName(CameraWidget)
    # setupUi

    def retranslateUi(self, CameraWidget):
        CameraWidget.setWindowTitle(QCoreApplication.translate("CameraWidget", u"Form", None))
        self.video_display.setText("")
        self.label.setText(QCoreApplication.translate("CameraWidget", u"QR \ucf54\ub4dc\ub97c \uce74\uba54\ub77c\uc5d0 \ubcf4\uc5ec\uc8fc\uc138\uc694", None))
        self.camera_btn.setText(QCoreApplication.translate("CameraWidget", u"\ucde8\uc18c\n"
"(\ud648 \ud654\uba74)", None))
    # retranslateUi

