# Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
# SPDX-License-Identifier: Apache-2.0.

from awsiot import mqtt5_client_builder
from awscrt import mqtt5, http
import threading
from concurrent.futures import Future
import time
import json
#from utils.command_line_utils import CommandLineUtils

from PySide2.QtCore import QThread, Signal

from qt_display.ggong2_signals.qr_signals import qr_signal_instance
from qt_display.ggong2_signals.anonymous_signals import anonymous_signal_instance
from qt_display.ggong2_signals.received_signals import received_signal_instance

from qt_display.msgDTO import msg_instance

import mysql.connector
from datetime import datetime

class MqttThread(QThread):


    def __init__(self):
        super().__init__()

        self.client = None#
        self.TIMEOUT = 100
        self.received_all_event = threading.Event()
        self.future_stopped = Future()
        self.future_connection_success = Future()

        self.endpoint = "avauxcvan5r71-ats.iot.ap-northeast-2.amazonaws.com"
        self.port = 8883
        self.cert_filepath = "qt_display/ggong2_telecom/certificates/ggong2.cert.pem"
        self.pri_key_filepath = "qt_display/ggong2_telecom/certificates/ggong2.private.key"
        self.ca_filepath = "qt_display/ggong2_telecom/certificates/root-CA.crt"
        self.client_id = "ggong2"
        self.topic_filter = "IoT/company/machine"
        self.my_topic = "IoT/company/machine/2"

        self.proxy_options = None
        self.subscribe_future = None
        anonymous_signal_instance.anonymous_signal.connect(self.publish_message)#메시지 보내기
        qr_signal_instance.qr_signal.connect(self.publish_message)



    def run(self):#main->
        print("mqtt_run")
        # self.proxy_options = http.HttpProxyOptions(
        #     host_name=cmdData.input_proxy_host,
        #     port=cmdData.input_proxy_port)

        self.client = mqtt5_client_builder.mtls_from_path(
            endpoint=self.endpoint,
            port=self.port,

            cert_filepath=self.cert_filepath,
            pri_key_filepath=self.pri_key_filepath,
            ca_filepath=self.ca_filepath,

            http_proxy_options=None,
            #init 밖에 있어서 client 여기
            on_publish_received=self.on_publish_received,
            on_lifecycle_stopped=self.on_lifecycle_stopped,
            on_lifecycle_connection_success=self.on_lifecycle_connection_success,
            on_lifecycle_connection_failure=self.on_lifecycle_connection_failure,

            client_id=self.client_id
        )
        self.client.start()

        # self.lifecycle_connect_success_data = self.future_connection_success.result(self.TIMEOUT)
        # self.connack_packet = self.lifecycle_connect_success_data.connack_packet
        # self.negotiated_settings = self.lifecycle_connect_success_data.negotiated_settings

        #subscribing
        self.subscribe_future = self.client.subscribe(subscribe_packet=mqtt5.SubscribePacket(
            subscriptions=[mqtt5.Subscription(topic_filter=self.topic_filter, qos=mqtt5.QoS.AT_LEAST_ONCE)]
        ))
        suback = self.subscribe_future.result(self.TIMEOUT)
        print("Subscribed with {}".format(suback.reason_codes))


        # self.client.disconnect()

    # Callback for the lifecycle event Stopped
    def on_lifecycle_stopped(self,lifecycle_stopped_data: mqtt5.LifecycleStoppedData):
        print("Lifecycle Stopped")
        global future_stopped
        future_stopped.set_result(lifecycle_stopped_data)

    # Callback for the lifecycle event Connection Success
    def on_lifecycle_connection_success(self,lifecycle_connect_success_data: mqtt5.LifecycleConnectSuccessData):
        print("Lifecycle Connection Success")
        self.future_connection_success.set_result(lifecycle_connect_success_data)

    # Callback for the lifecycle event Connection Failure
    def on_lifecycle_connection_failure(self,lifecycle_connection_failure: mqtt5.LifecycleConnectFailureData):
        print("Lifecycle Connection Failure")
        print("Connection failed with exception: {}".format(lifecycle_connection_failure.exception))


    # 메세지 오면 호출되는 함수
    def on_publish_received(self, publish_packet_data):
        print("on_pub_received")
        publish_packet = publish_packet_data.publish_packet
        assert isinstance(publish_packet, mqtt5.PublishPacket)
        print("Received message from topic '{}': {}".format(publish_packet.topic, publish_packet.payload))
        # global received_count
        # received_count += 1
        # 메시지를 받았을 때 message_received 시그널 emit.

        # dict로 보냈으니 dict로 받음 -> answer만 받아서 int로 emit
        #self.message_received.emit(json.loads(publish_packet.payload))
        received_signal_instance.received_signal.emit(json.loads(publish_packet.payload))
    def publish_message(self):
        self.insert_message()
        message = msg_instance.get_dist()
        #int 64 에러 -> get dist 안에 int변환 포함시키기

        publish_future = self.client.publish(mqtt5.PublishPacket(
            topic=self.my_topic,
            payload=json.dumps(message),
            qos=mqtt5.QoS.AT_LEAST_ONCE
        ))
        publish_completion_data = publish_future.result(self.TIMEOUT)
        print("PubAck received with {}".format(repr(publish_completion_data.puback.reason_code)))

    def insert_message(self):
        print("insert_message start")
        my_dist = msg_instance.get_dist()

        # 변수 설정
        # user_no = 3
        # machine_no = 1
        # question_ID = 1
        # answer = 3
        # point = 10
        # buy_no = 1
        # current_time = datetime.now()
        #int64->int
        user_no = int(my_dist["user_no"])
        #machine_no = int(my_dist["machine_no"])
        machine_no = int(2)
        question_ID = int(my_dist["question_ID"])
        answer = int(my_dist["answer"])
        point = int(10)
        buy_no = int(1)
        current_time = my_dist["vote_date"]

        try:
            connection = mysql.connector.connect(user='ggong', password='1234', host='i9A304.p.ssafy.io',
                                                 database='ggong')
            # connection = mysql.connector.connect(user='root', password='1', host='127.0.0.1', database='ggong')
            if connection.is_connected():
                db_info = connection.get_server_info()
                print("connection start")
                print('mysql Version : ', db_info)

                cursor = connection.cursor()

                connection.start_transaction()

                query_vote = """
                INSERT INTO vote (user_no, machine_no, question_ID, vote_date, answer)
                VALUES (%s, %s, %s, %s, %s);
                """
                print(query_vote)
                cursor.execute(query_vote, (user_no, machine_no, question_ID, current_time, answer))

                vote_no = cursor.lastrowid

                cursor.execute("SELECT balance_point FROM point WHERE user_no = %s ORDER BY point_no DESC LIMIT 1",
                               (user_no,))
                last_balance_point = cursor.fetchone()

                if last_balance_point:
                    last_balance_point = last_balance_point[0]
                else:
                    last_balance_point = 0

                new_balance_point = last_balance_point + point

                query_point = """
                INSERT INTO point (event_time, point, balance_point, vote_no, user_no, buy_no)
                VALUES (%s, %s, %s, %s, %s, %s);
                """
                cursor.execute(query_point, (current_time, point, new_balance_point, vote_no, user_no, buy_no))


                tem_rate = "seed"
                if new_balance_point >100000:
                    tem_rate = "forest"
                elif new_balance_point >10000:
                    tem_rate = "tree"
                elif new_balance_point >1000:
                    tem_rate = "branch"
                elif new_balance_point >500:
                    tem_rate = "leaf"
                elif new_balance_point >100:
                    tem_rate = "sprout"
                else:
                    tem_rate = "seed"

                query_user = """
                UPDATE user SET user_rating = %s WHERE user_no = %s;
                """
                cursor.execute(query_user, (tem_rate,user_no))


                connection.commit()
                print("insert_message commit end")
        except mysql.connector.Error as e:
            connection.rollback()
            print('Database Error: ', e)

        finally:
            if connection.is_connected():
                cursor.close()
                connection.close()
                print('MySQL Connection Close')


if __name__ == '__main__':

    mqtt_thread = MqttThread()
    mqtt_thread.publish_message("hihi")
    mqtt_thread.start()

