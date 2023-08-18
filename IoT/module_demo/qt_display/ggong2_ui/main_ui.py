# -*- coding: utf-8 -*-

################################################################################
## Form generated from reading UI file 'main.ui'
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
from PySide2.QtWidgets import (QApplication, QLabel, QMainWindow, QSizePolicy,
    QStatusBar, QWidget)

class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        if not MainWindow.objectName():
            MainWindow.setObjectName(u"MainWindow")
        MainWindow.resize(1050, 550)
        MainWindow.setMinimumSize(QSize(1050, 550))
        MainWindow.setMaximumSize(QSize(1050, 550))
        MainWindow.setStyleSheet(u"background-color: rgb(249, 212, 66);")
        self.centralwidget = QWidget(MainWindow)
        self.centralwidget.setObjectName(u"centralwidget")
        self.widget = QWidget(self.centralwidget)
        self.widget.setObjectName(u"widget")
        self.widget.setGeometry(QRect(210, 160, 250, 60))
        self.widget.setMinimumSize(QSize(250, 60))
        self.widget.setMaximumSize(QSize(250, 60))
        self.widget.setStyleSheet(u"background-color: rgb(244, 136, 122);\n"
"\n"
"border: 3px solid;\n"
"border-color: rgb(161, 161, 170);\n"
"border-bottom: none;")
        self.main_atitle_var = QLabel(self.widget)
        self.main_atitle_var.setObjectName(u"main_atitle_var")
        self.main_atitle_var.setGeometry(QRect(20, 10, 200, 40))
        self.main_atitle_var.setMinimumSize(QSize(200, 40))
        self.main_atitle_var.setMaximumSize(QSize(200, 40))
        font = QFont()
        font.setPointSize(24)
        self.main_atitle_var.setFont(font)
        self.main_atitle_var.setStyleSheet(u"border:none")
        self.main_atitle_var.setAlignment(Qt.AlignCenter)
        self.widget_2 = QWidget(self.centralwidget)
        self.widget_2.setObjectName(u"widget_2")
        self.widget_2.setGeometry(QRect(590, 160, 250, 60))
        self.widget_2.setMinimumSize(QSize(250, 60))
        self.widget_2.setMaximumSize(QSize(250, 60))
        self.widget_2.setStyleSheet(u"background-color: rgb(102, 159, 244);\n"
"\n"
"border: 3px solid;\n"
"border-color: rgb(161, 161, 170);\n"
"border-bottom: none;")
        self.main_btitle_var = QLabel(self.widget_2)
        self.main_btitle_var.setObjectName(u"main_btitle_var")
        self.main_btitle_var.setGeometry(QRect(20, 10, 200, 40))
        self.main_btitle_var.setMinimumSize(QSize(200, 40))
        self.main_btitle_var.setMaximumSize(QSize(200, 40))
        self.main_btitle_var.setFont(font)
        self.main_btitle_var.setLayoutDirection(Qt.LeftToRight)
        self.main_btitle_var.setStyleSheet(u"border:none")
        self.main_btitle_var.setAlignment(Qt.AlignCenter)
        self.widget_3 = QWidget(self.centralwidget)
        self.widget_3.setObjectName(u"widget_3")
        self.widget_3.setGeometry(QRect(210, 220, 250, 180))
        self.widget_3.setMinimumSize(QSize(250, 180))
        self.widget_3.setMaximumSize(QSize(250, 180))
        self.widget_3.setStyleSheet(u"background-color: rgb(255, 255, 255);\n"
"\n"
"border: 3px solid;\n"
"border-color: rgb(161, 161, 170);")
        self.main_acount_var = QLabel(self.widget_3)
        self.main_acount_var.setObjectName(u"main_acount_var")
        self.main_acount_var.setGeometry(QRect(10, 10, 230, 160))
        self.main_acount_var.setMinimumSize(QSize(230, 160))
        self.main_acount_var.setMaximumSize(QSize(230, 160))
        self.main_acount_var.setFont(font)
        self.main_acount_var.setStyleSheet(u"border:none")
        self.main_acount_var.setAlignment(Qt.AlignCenter)
        self.widget_4 = QWidget(self.centralwidget)
        self.widget_4.setObjectName(u"widget_4")
        self.widget_4.setGeometry(QRect(590, 220, 250, 180))
        self.widget_4.setMinimumSize(QSize(250, 180))
        self.widget_4.setMaximumSize(QSize(250, 180))
        self.widget_4.setStyleSheet(u"background-color: rgb(255, 255, 255);\n"
"border: 3px solid;\n"
"border-color: rgb(161, 161, 170);")
        self.main_bcount_var = QLabel(self.widget_4)
        self.main_bcount_var.setObjectName(u"main_bcount_var")
        self.main_bcount_var.setGeometry(QRect(10, 10, 230, 160))
        self.main_bcount_var.setMinimumSize(QSize(230, 160))
        self.main_bcount_var.setMaximumSize(QSize(230, 160))
        self.main_bcount_var.setFont(font)
        self.main_bcount_var.setStyleSheet(u"border:none")
        self.main_bcount_var.setAlignment(Qt.AlignCenter)
        self.widget_5 = QWidget(self.centralwidget)
        self.widget_5.setObjectName(u"widget_5")
        self.widget_5.setGeometry(QRect(210, 440, 630, 50))
        self.widget_5.setMinimumSize(QSize(630, 50))
        self.widget_5.setMaximumSize(QSize(630, 50))
        self.widget_5.setStyleSheet(u"background-color: rgb(255, 255, 255);\n"
"\n"
"border: 3px solid;\n"
"border-color: rgb(161, 161, 170);\n"
"border-radius : 10px;")
        self.foot_nxt_fix = QLabel(self.widget_5)
        self.foot_nxt_fix.setObjectName(u"foot_nxt_fix")
        self.foot_nxt_fix.setGeometry(QRect(10, 10, 120, 35))
        self.foot_nxt_fix.setMinimumSize(QSize(120, 35))
        self.foot_nxt_fix.setMaximumSize(QSize(120, 35))
        self.foot_nxt_fix.setFont(font)
        self.foot_nxt_fix.setStyleSheet(u"border:none")
        self.foot_nxt_fix.setAlignment(Qt.AlignCenter)
        self.foot_lefttime_fix = QLabel(self.widget_5)
        self.foot_lefttime_fix.setObjectName(u"foot_lefttime_fix")
        self.foot_lefttime_fix.setGeometry(QRect(320, 10, 200, 35))
        self.foot_lefttime_fix.setMinimumSize(QSize(200, 35))
        self.foot_lefttime_fix.setMaximumSize(QSize(200, 35))
        self.foot_lefttime_fix.setFont(font)
        self.foot_lefttime_fix.setStyleSheet(u"border:none")
        self.foot_lefttime_fix.setAlignment(Qt.AlignCenter)
        self.foot_timer_var = QLabel(self.widget_5)
        self.foot_timer_var.setObjectName(u"foot_timer_var")
        self.foot_timer_var.setGeometry(QRect(520, 10, 100, 35))
        self.foot_timer_var.setMinimumSize(QSize(100, 35))
        self.foot_timer_var.setMaximumSize(QSize(100, 35))
        self.foot_timer_var.setFont(font)
        self.foot_timer_var.setStyleSheet(u"border:none")
        self.foot_timer_var.setAlignment(Qt.AlignCenter)
        self.foot_nxt_var = QLabel(self.widget_5)
        self.foot_nxt_var.setObjectName(u"foot_nxt_var")
        self.foot_nxt_var.setGeometry(QRect(120, 10, 210, 35))
        self.foot_nxt_var.setMinimumSize(QSize(210, 35))
        self.foot_nxt_var.setMaximumSize(QSize(210, 35))
        font1 = QFont()
        font1.setFamilies([u"\uad74\ub9bc"])
        font1.setPointSize(24)
        self.foot_nxt_var.setFont(font1)
        self.foot_nxt_var.setStyleSheet(u"border:none")
        self.widget_6 = QWidget(self.centralwidget)
        self.widget_6.setObjectName(u"widget_6")
        self.widget_6.setGeometry(QRect(210, 30, 630, 100))
        self.widget_6.setMinimumSize(QSize(630, 100))
        self.widget_6.setMaximumSize(QSize(630, 100))
        self.widget_6.setStyleSheet(u"background-color: rgb(255, 255, 255);border: 3px solid;\n"
"border-color: rgb(161, 161, 170);\n"
"border-radius : 10px;")
        self.title_question_var = QLabel(self.widget_6)
        self.title_question_var.setObjectName(u"title_question_var")
        self.title_question_var.setGeometry(QRect(10, 10, 610, 40))
        self.title_question_var.setMinimumSize(QSize(610, 40))
        self.title_question_var.setMaximumSize(QSize(610, 40))
        self.title_question_var.setFont(font)
        self.title_question_var.setStyleSheet(u"border:none")
        self.title_question_var.setAlignment(Qt.AlignCenter)
        self.title_entire_fix = QLabel(self.widget_6)
        self.title_entire_fix.setObjectName(u"title_entire_fix")
        self.title_entire_fix.setGeometry(QRect(140, 50, 120, 40))
        self.title_entire_fix.setMinimumSize(QSize(120, 40))
        self.title_entire_fix.setMaximumSize(QSize(120, 40))
        self.title_entire_fix.setFont(font)
        self.title_entire_fix.setStyleSheet(u"border:none")
        self.title_entire_fix.setAlignment(Qt.AlignCenter)
        self.title_acount_var = QLabel(self.widget_6)
        self.title_acount_var.setObjectName(u"title_acount_var")
        self.title_acount_var.setGeometry(QRect(250, 50, 100, 40))
        self.title_acount_var.setMinimumSize(QSize(100, 40))
        self.title_acount_var.setMaximumSize(QSize(100, 40))
        self.title_acount_var.setFont(font)
        self.title_acount_var.setStyleSheet(u"border:none")
        self.title_acount_var.setAlignment(Qt.AlignCenter)
        self.title_bcount_var = QLabel(self.widget_6)
        self.title_bcount_var.setObjectName(u"title_bcount_var")
        self.title_bcount_var.setGeometry(QRect(380, 50, 100, 40))
        self.title_bcount_var.setMinimumSize(QSize(100, 40))
        self.title_bcount_var.setMaximumSize(QSize(100, 40))
        self.title_bcount_var.setFont(font)
        self.title_bcount_var.setStyleSheet(u"border:none")
        self.title_bcount_var.setAlignment(Qt.AlignCenter)
        self.title_simbol1_fix = QLabel(self.widget_6)
        self.title_simbol1_fix.setObjectName(u"title_simbol1_fix")
        self.title_simbol1_fix.setGeometry(QRect(480, 50, 30, 40))
        self.title_simbol1_fix.setMinimumSize(QSize(30, 40))
        self.title_simbol1_fix.setMaximumSize(QSize(30, 40))
        self.title_simbol1_fix.setFont(font)
        self.title_simbol1_fix.setStyleSheet(u"border:none")
        self.title_simbol1_fix.setAlignment(Qt.AlignCenter)
        self.title_symbol0_fix = QLabel(self.widget_6)
        self.title_symbol0_fix.setObjectName(u"title_symbol0_fix")
        self.title_symbol0_fix.setGeometry(QRect(350, 50, 30, 40))
        self.title_symbol0_fix.setMinimumSize(QSize(30, 40))
        self.title_symbol0_fix.setMaximumSize(QSize(30, 40))
        self.title_symbol0_fix.setFont(font)
        self.title_symbol0_fix.setStyleSheet(u"border:none")
        self.title_symbol0_fix.setAlignment(Qt.AlignCenter)
        MainWindow.setCentralWidget(self.centralwidget)
        self.statusbar = QStatusBar(MainWindow)
        self.statusbar.setObjectName(u"statusbar")
        MainWindow.setStatusBar(self.statusbar)

        self.retranslateUi(MainWindow)

        QMetaObject.connectSlotsByName(MainWindow)
    # setupUi

    def retranslateUi(self, MainWindow):
        MainWindow.setWindowTitle(QCoreApplication.translate("MainWindow", u"MainWindow", None))
        self.main_atitle_var.setText(QCoreApplication.translate("MainWindow", u"\ubbfc\ubcd1\uae30", None))
        self.main_btitle_var.setText(QCoreApplication.translate("MainWindow", u"\uc708\ud130", None))
        self.main_acount_var.setText(QCoreApplication.translate("MainWindow", u"87", None))
        self.main_bcount_var.setText(QCoreApplication.translate("MainWindow", u"10", None))
        self.foot_nxt_fix.setText(QCoreApplication.translate("MainWindow", u"next : ", None))
        self.foot_lefttime_fix.setText(QCoreApplication.translate("MainWindow", u"\ub0a8\uc740\uc2dc\uac04 :", None))
        self.foot_timer_var.setText(QCoreApplication.translate("MainWindow", u"30\ubd84", None))
        self.foot_nxt_var.setText(QCoreApplication.translate("MainWindow", u"\uc74c\uc2dd", None))
        self.title_question_var.setText(QCoreApplication.translate("MainWindow", u"\ub204\uad6c\ub97c \uc88b\uc544\ud558\uc138\uc694?", None))
        self.title_entire_fix.setText(QCoreApplication.translate("MainWindow", u"( \uc804\uccb4 ", None))
        self.title_acount_var.setText(QCoreApplication.translate("MainWindow", u"121", None))
        self.title_bcount_var.setText(QCoreApplication.translate("MainWindow", u"87", None))
        self.title_simbol1_fix.setText(QCoreApplication.translate("MainWindow", u")", None))
        self.title_symbol0_fix.setText(QCoreApplication.translate("MainWindow", u":", None))
    # retranslateUi

