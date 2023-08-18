import re

def confirm_code(plain_code):
    #인식된 qr이 시리얼 규칙에 맞으면
    #시리얼 자체를 qr로 보낸다고 가정한 상태

    #정규표현식으로 시리얼 번호 형식 맞는지 확인
    #숫자이기만 하면 ok -> 간혹 의도하지 않은 숫자 나옴
    pattern = r'^\d+$' # user_no 사용 X -> 인한 주석처리 ->다시 user_no로 롤백
    #pattern = '^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$' # email format
    #email_code = "error"  # error 리턴되면 에러
    user_account = -1

    if re.match(pattern, plain_code):
        user_account = int(plain_code)
        #print("your email : ", email_code)
        print("your account : ", str(user_account))

    else:
        # print("not match for email format")
        print("not match for user_account format")

    return user_account