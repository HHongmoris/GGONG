DROP DATABASE IF EXISTS ggong;

create database IF NOT EXISTS ggong;

use ggong;

CREATE TABLE `user` (
	`user_no` BIGINT NOT NULL AUTO_INCREMENT,
	`nickname` VARCHAR(45) NOT NULL,
	`age_range` VARCHAR(45) NOT NULL,
	`gender` VARCHAR(45) NOT NULL,
	`email` VARCHAR(45) NOT NULL,
	`favorite_cigarette` VARCHAR(45) NULL,
	`QR` VARCHAR(100) NULL,
	`user_rating` VARCHAR(45) NULL,
    PRIMARY KEY (`user_no`)
)ENGINE = InnoDB DEFAULT CHARACTER SET utf8mb4;

INSERT INTO user(nickname, age_range, gender, email)
VALUES
('정대장', '20대', '여성', 'leader@naver.com'),
('정예지', '40대', '여성', 'yehji@naver.com'),
('민박사', '30대', '남성', 'doctormin@naver.com'),
('전박사', '20대', '남성', 'jeon@naver.com'),
('서보하', '50대', '여성', 'boha@naver.com'),
('곽교원', '60대', '여성', 'kyowon@naver.com'),
('송해하', '30대', '남성', 'haeha@naver.com'),
('편고지', '30대', '여성', 'goji@naver.com'),
('장조유', '20대', '남성', 'joyou@naver.com'),
('문정령', '20대', '남성', 'jeongryung@naver.com'),
('황해언', '50대', '여성', 'haeyeon@naver.com'),
('권성린', '40대', '남성', 'sungrin@naver.com'),
('양휘진', '20대', '남성', 'huijin@naver.com'),
('곽세수', '20대', '남성', 'saesu@naver.com'),
('김초은', '30대', '여성', 'choeun@naver.com'),
('조휘준', '30대', '여성', 'huijun@naver.com'),
('류휘나', '20대', '남성', 'huina@naver.com'),
('유명소', '20대', '여성', 'myungso@naver.com'),
('은정윤', '50대', '여성', 'jeongyoon@naver.com'),
('우윤율', '40대', '여성', 'yoonyul@naver.com'),
('박교운', '40대', '남성', 'kyoyun@naver.com'),
('하가승', '30대', '남성', 'gaseung@naver.com'),
('한경화', '20대', '남성', 'gyeonghwa@naver.com'),
('장가승', '20대', '여성', 'gaseung2@naver.com');


CREATE TABLE `machine` (
	`machine_no` BIGINT NOT NULL AUTO_INCREMENT,
	`latitude` double NOT NULL,
	`longitude`	double NOT NULL,
	`name` VARCHAR(45) NOT NULL,
	`SN` VARCHAR(45) NOT NULL,
	`area_gu`VARCHAR(45) NOT NULL,
    PRIMARY KEY (`machine_no`)
)ENGINE = InnoDB DEFAULT CHARACTER SET utf8mb4;

INSERT INTO machine(latitude, longitude, name, SN, area_gu)
VALUES
(37.459882, 126.951905, '서울대학교', 'univ01', '관악구'),
(37.590799, 127.027777, '고려대학교', 'univ02', '성북구'),
(37.565784, 126.938572, '연세대학교', 'univ03', '서대문구'),
(37.588227, 126.993606, '성균관대학교', 'univ04', '종로구'),
(37.557232, 127.045322, '한양대학교', 'univ05', '성동구'),
(37.526114, 126.926816, 'LG에너지솔루션', 'com01', '영등포구'),
(37.489375, 126.994335, '현대차', 'com02', '서초구'),
(37.486204, 127.010074, '기아', 'com03', '서초구'),
(37.501334, 127.039706, '멀티캠퍼스', 'com04', '강남구'),
(37.516615, 127.100841, '삼성물산(주)', 'com05', '송파구');

CREATE TABLE `favoritemachine` (
	`favoritemachine_no` BIGINT NOT NULL AUTO_INCREMENT,
	`user_no` BIGINT NOT NULL,
	`machine_no` BIGINT NOT NULL,
    PRIMARY KEY (`favoritemachine_no`),
    FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`),
    FOREIGN KEY (`machine_no`) REFERENCES `machine` (`machine_no`)
)ENGINE = InnoDB DEFAULT CHARACTER SET utf8mb4;

INSERT INTO favoritemachine(user_no, machine_no)
VALUES
(1, 2),
(1, 3),
(1, 9),
(2, 1);

INSERT INTO favoritemachine(user_no, machine_no)
VALUE 
(5, 1);

CREATE TABLE `question` (
	`question_ID` BIGINT NOT NULL AUTO_INCREMENT,
	`content` VARCHAR(45) NOT NULL,
	`group` INT NOT NULL,
	`category` VARCHAR(45) NOT NULL,
	`optionA` VARCHAR(45) NOT NULL,
	`optionB` VARCHAR(45) NOT NULL,
	`type` ENUM('공통','대학','기업') NOT NULL,
    PRIMARY KEY (`question_ID`)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8mb4;

INSERT INTO question(content, `group`, category, optionA, optionB, `type`)
VALUES
('민초 vs 반민초', 1, '음식', '민초', '반민초', '공통'),
('부먹 vs 찍먹', 1, '음식', '부먹', '찍먹', '공통'),
('소주 vs 맥주', 1, '음식', '소주', '맥주', '공통'),
('감자 vs 고구마', 1, '음식', '감자', '고구마', '공통'),
('물냉 vs 비냉', 1, '음식', '물냉', '비냉', '공통'),
('잠수 이별 vs 환승 이별', 1, '일반', '잠수 이별', '환승 이별', '공통'),
('랜덤으로 다시 태어나기 vs 그냥 살기', 1, '일반', '랜덤으로 다시 태어나기', '그냥 살기', '공통'),
('축구 vs 야구', 1, '스포츠', '축구', '야구', '공통'),
('메시 vs 호날두', 1, '스포츠', '메시', '호날두', '공통'),
('OO없이 1년 살기', 1, '일반', '핸드폰', '친구', '공통'),
('주점 vs 연예인 공연', 1, '축제', '주점', '연예인 공연', '대학'),
('매일 1교시 vs 매일 3시간씩 공강', 1, '학업', '매일 1교시', '매일 3시간씩 공강', '대학'),
('명강 vs 꿀강', 1, '학업', '명강', '꿀강', '대학'),
('MT vs 축제', 1, '축제', 'MT', '축제', '대학'),
('더 싫은 조원은?', 1, '학업', '전 애인', '프리라이더', '대학'),
('더 싫은 룸메는?', 1, '학업', '안 씻는 룸메', '청소 안 하는 룸메', '대학'),
('축제 때 선호하는 연예인?', 1, '축제', '싸이', '카포에라', '대학'),
('왕복 4시간 통학 vs 통금 9시 기숙사', 1, '생활', '왕복 4시간 통학', '통금 9시 기숙사', '대학'),
('더 싫은 상황은?', 1, '생활', '애인의 전 애인과 팀플', '전 애인과 전부 겹강', '대학'),
('하루 시험 4개 vs 종강 후 레포트 4개', 1, '학업', '하루 시험 4개', '종강 후 레포트 4개', '대학'),
('대면 수업 vs 비대면 수업', 1, '학업', '대면 수업', '비대면 수업', '대학'),
('야근 vs 상사 잔소리', 1, '회사생활', '야근', '상사 잔소리', '기업'),
('근무 중 이어폰 끼기', 1, '회사생활', '가능', '불가능', '기업'),
('직장 내 슬리퍼 착용', 1, '회사생활', '가능', '불가능', '기업'),
('월 200 백수 vs 월 500 직장인 ', 1, '회사생활', '월 200 백수', '월 500 직장인', '기업'),
('연봉 2천 17시 칼퇴 vs 연봉 5천 휴일 x', 1, '회사생활', '야연봉 2천 17시 칼퇴근', '연봉 5천 휴일 x', '기업'),
('만원버스 30분 출퇴근 vs 텅빈 버스 90분 출퇴근', 1, '회사생활', '만원버스 30분 출퇴근', '텅빈 버스 90분 출퇴근', '기업'),
('일 잘하는데 성격 나쁜 사람 vs 일 못하는데 성격 좋은 사람', 1, '회사생활', '일 잘하는데 성격 나쁜 사람', '일 못하는데 성격 좋은 사람', '기업'),
('연차 날 계속 오는 업무 전화 vs 퇴근 10분 전 업무받아 야근', 1, '회사생활', '연차 날 계속 오는 업무 전화', '퇴근 10분 전 업무받아 야근', '기업'),
('돈 많이 주는 하기 싫은 일 vs 돈 적게 주는 하고 싶은 일', 1, '돈 많이 주는 하기 싫은 일', '돈 적게 주는 하고 싶은 일', '부장잔소리', '기업'),
('전 애인이 사수 vs 전 애인이 동기', 1, '회사생활', '전 애인이 사수', '전 애인이 동기', '기업'),
('월요일 연차 vs 금요일 연차', 1, '회사생활', '월요일 연차', '금요일 연차', '기업'),
('후라이드 치킨 vs 양념 치킨', 2, '음식', '후라이드 치킨', '양념 치킨', '공통'),
('아메리카노 vs 라떼', 2, '음식', '아메리카노', '라떼', '공통'),
('짜장면 vs 짬뽕', 2, '음식', '짜장면', '짬뽕', '공통'),
('팥 호빵 vs 야채 호빵', 2, '음식', '팥 호빵', '야채 호빵', '공통'),
('된장찌개 vs 김치찌개', 2, '음식', '된장찌개', '김치찌개', '공통'),
('지금 먹어야 한다면?', 2, '음식', '토맛 토마토', '토마토맛 토', '공통'),
('면접인데 옷이 2벌 뿐이라면', 2, '생활', '나비넥타이 연미복', '찢어진 청바지', '공통'),
('웃고 싶을 때 못 웃기 vs 울고 싶을 때 못 울기', 2, '일반', '웃고 싶을 때 못 웃기', '울고 싶을 때 못 울기', '공통'),
('일주일 동안 OO불가', 2, '일반', '머리 감기', '양치질', '공통'),
('자도자도 피곤 vs 먹어도 먹어도 배고픔', 2, '일반', '자도자도 피곤', '먹어도 먹어도 배고픔', '공통'),
('더 싫은 상황은?', 2, '생활', '현 애인의 전 애인과 팀플', '전 애인의 현 애인과 팀플', '대학'),
('왕복 4시간 통학 vs 상극인 룸메와 살기', 2, '생활', '왕복 4시간 통학', '상극인 룸메와 살기', '대학'),
('선호하는 수업은?', 2, '학업', '도움 되지만 학점 짠 수업', '도움 안되지만 학점 후한 수업', '대학'),
('월요일 공강 vs 금요일 공강', 2, '생활', '월요일 공강', '금요일 공강', '대학'),
('하나만 들고다녀야 한다면?', 2, '생활', '가방 (노트북X)', '노트북 (가방X)', '대학'),
('절친의 전 애인과 과CC vs 절친이 나의 전 애인과 과CC ', 2, '생활', '절친의 전 애인과 과CC', '절친이 나의 전 애인과 과CC', '대학'),
('술 마시고 OO하기', 2, '생활', '교수님께 전화', '전 애인에게 전화', '대학'),
('축제 무대에서 OO하기', 2, '생활', '고백하고 차이기', '고백받고 차기', '대학'),
('더 최악은?', 2, '학업', '아무것도 모르는데 개인과제', '조별과제 본인 하드캐리', '대학'),
('수강신청 중 더 최악은?', 2, '학업', '불안정한 와이파이', '핸드폰으로 수강신청', '대학'),
('더 싫은 조원은?', 2, '학업', '잠수타는 조원', '일 못하는 조원', '대학'),
('대학 입학 전으로 돌아간다면?', 2, '일반', '전공 바꾼다', '전공 안 바꾼다', '기업'),
('휴가 시즌에 당신이라면?', 2, '회사생활', '2주 연속 쉬기 (무급)', '안 쉬고 일급 2배', '기업'),
('연봉 절반 오전 근무만(겸직가능) vs 연봉 2배 365일 근무(칼퇴불가)', 2, '회사생활', '연봉 절반 오전 근무만(겸직가능)', '부장잔소리', '기업'),
('업무가 산처럼 쌓였을 때 선호하는 것은?', 2, '회사생활', '신입충원(칼퇴가능)', '주말 및 야근 수당 두배', '기업'),
('야근 잦은데 가고 싶은 부서 vs 항상 칼퇴지만 가기 싫은 부서', 2, '회사생활', '야근 잦은데 가고 싶은 부서', '항상 칼퇴지만 가기 싫은 부서', '기업'),
('최고 사양 태블릿으로 업무 vs 10년전 PC로 업무', 2, '회사생활', '최고 사양 태블릿으로 업무', '10년전 PC로 업무', '기업'),
('프로젝트 실패 시', 2, '회사생활', '위로와 격려', '냉정한 피드백', '기업'),
('라떼 선배 vs MZ 후배', 2, '회사생활', '라떼 선배', 'MZ 후배', '기업'),
('상사 가족 모임에 혼자 참석 vs 본인 가족 모임에 상사 초청', 2, '회사생활', '상사 가족 모임에 혼자 참석', '본인 가족 모임에 상사 초청', '기업'),
('사사건건 간섭하는 사수 vs 안 알려주는 사수', 2, '회사생활', '사사건건 간섭하는 사수', '안 알려주는 사수', '기업'),
('시켜야만 일 하는 사람 vs 일을 만드는 사람', 2, '회사생활', '시켜야만 일 하는 사람', '일을 만드는 사람', '기업');

CREATE TABLE `vote` (
    `vote_no` BIGINT NOT NULL AUTO_INCREMENT,
    `user_no` BIGINT,
    `machine_no` BIGINT NOT NULL,
    `question_ID` BIGINT NOT NULL,
    `vote_date` TIMESTAMP NOT NULL,
    `answer` INT NOT NULL,
    PRIMARY KEY(`vote_no`),
    FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`),
    FOREIGN KEY (`machine_no`) REFERENCES `machine` (`machine_no`),
    FOREIGN KEY (`question_ID`) REFERENCES `question` (`question_ID`)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4;

INSERT INTO vote(user_no, machine_no, question_ID, vote_date, answer)
VALUES
(1, 1, 1, '2023-08-04 12:34:56', 1),
(2, 1, 1, '2023-08-05 12:34:56', 1),
(1, 1, 2, '2023-08-08 12:34:56', 1);

CREATE TABLE `product` (
    `product_no` BIGINT NOT NULL AUTO_INCREMENT,
    `pin` VARCHAR(60) NOT NULL,
    `price` INT NOT NULL,
    PRIMARY KEY(`product_no`)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4;

INSERT INTO product(pin, price)
VALUES
('AAAA-1111-AAAA-1111', 5000),
('BBBB-2222-BBBB-2222', 5000),
('CCCC-3333-CCCC-3333', 5000),
('DDDD-4444-DDDD-4444', 10000),
('EEEE-5555-EEEE-5555', 10000),
('FFFF-6666-FFFF-6666', 10000),
('GGGG-7777-GGGG-7777', 50000),
('HHHH-8888-HHHH-8888', 50000),
('IIII-9999-IIII-9999', 50000);


CREATE TABLE `buy` (
    `buy_no` BIGINT NOT NULL AUTO_INCREMENT,
    `buy_time` TIMESTAMP NOT NULL,
    `user_no` BIGINT NOT NULL,
    `product_no` BIGINT NOT NULL,
    PRIMARY KEY(`buy_no`),
    FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`),
    FOREIGN KEY (`product_no`) REFERENCES `product` (`product_no`)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4;

INSERT INTO buy(buy_time, user_no, product_no)
VALUES
('2023-08-04 12:31:56', 1, 1),
('2023-08-05 14:28:00', 1, 5);

CREATE TABLE `point` (
    `point_no` BIGINT NOT NULL AUTO_INCREMENT,
    `event_time` TIMESTAMP NOT NULL,
    `point` INT NOT NULL,
    `balance_point` INT NOT NULL,
    `vote_no` BIGINT,
    `user_no` BIGINT NOT NULL,
    `buy_no` BIGINT,
    PRIMARY KEY(`point_no`),
    FOREIGN KEY (`vote_no`) REFERENCES `vote` (`vote_no`),
    FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`),
    FOREIGN KEY (`buy_no`) REFERENCES `buy` (`buy_no`)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4;

INSERT INTO point(event_time, point, balance_point, vote_no, user_no, buy_no)
VALUES
('2023-08-01 12:31:56', 10, 10, 1, 1, null),
('2023-08-04 12:31:56', -5000, 10000, null, 1, 1);