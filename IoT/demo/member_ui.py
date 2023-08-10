# -*- coding: utf-8 -*-

################################################################################
## Form generated from reading UI file 'member.ui'
##
## Created by: Qt User Interface Compiler version 5.15.2
##
## WARNING! All changes made in this file will be lost when recompiling UI file!
################################################################################

from PySide2.QtCore import *
from PySide2.QtGui import *
from PySide2.QtWidgets import *


class Ui_MemberWidget(object):
    def setupUi(self, MemberWidget):
        if not MemberWidget.objectName():
            MemberWidget.setObjectName(u"MemberWidget")
        MemberWidget.resize(800, 480)
        MemberWidget.setMinimumSize(QSize(800, 480))
        MemberWidget.setMaximumSize(QSize(800, 480))
        MemberWidget.setStyleSheet(u"background-color: rgb(249, 212, 66);")
        self.pushButton = QPushButton(MemberWidget)
        self.pushButton.setObjectName(u"pushButton")
        self.pushButton.setGeometry(QRect(70, 100, 261, 261))
        font = QFont()
        font.setPointSize(24)
        self.pushButton.setFont(font)
        self.pushButton.setStyleSheet(u"background-color: rgb(255, 255, 255);\n"
"border-radius : 10px;border: 3px solid;\n"
"border-color: rgb(161, 161, 170);")
        self.pushButton_2 = QPushButton(MemberWidget)
        self.pushButton_2.setObjectName(u"pushButton_2")
        self.pushButton_2.setGeometry(QRect(450, 100, 261, 261))
        self.pushButton_2.setFont(font)
        self.pushButton_2.setStyleSheet(u"background-color: rgb(255, 255, 255);\n"
"border-radius : 10px;border: 3px solid;\n"
"border-color: rgb(161, 161, 170);")

        self.retranslateUi(MemberWidget)
        self.pushButton.clicked.connect(MemberWidget.clickMember)
        self.pushButton_2.clicked.connect(MemberWidget.clickAnonymous)

        QMetaObject.connectSlotsByName(MemberWidget)
    # setupUi

    def retranslateUi(self, MemberWidget):
        MemberWidget.setWindowTitle(QCoreApplication.translate("MemberWidget", u"Form", None))
        self.pushButton.setText(QCoreApplication.translate("MemberWidget", u"\ud68c\uc6d0\n"
"(QR \ub85c\uadf8\uc778)", None))
        self.pushButton_2.setText(QCoreApplication.translate("MemberWidget", u"\ube44\ud68c\uc6d0", None))
    # retranslateUi

