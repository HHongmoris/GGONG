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
('정대장', '20대', '남성', '123@123'),
('정예지', '40대', '여성', '123@1234'),
('민박사', '30대', '남성', '123@1234'),
('정박사', '30대', '여성', '123@1234');


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
(123.123, 12.1234, '서울대학교', 'abc', '송파구'),
(12.112, 12.12121, '하버드대학교', 'qqq', '강남구'),
(1.1234, 123.111, '어떤대학교', '111', '안구'),
(22.222, 22.133, '삼성전자', 'sss', '송파구'),
(33.222, 12.1122, '삼성SDS', 'sds', '송파구');

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
(1, 3);

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
('예지 vs 주영', 1, '싸움', '예지', '주영', '공통'),
('병기 vs 윈터', 1, '싸움', '병기', '윈터', '공통'),
('파스타 vs 샐러드', 1, '음식', '파스타', '샐러드', '대학'),
('무에타이 vs 카포에라', 1, '예지', '무에타이', '카포에라', '대학'),
('야근 vs 부장잔소리', 1, '회사생활', '야근', '부장잔소리', '기업');

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
('1213AQ1e', 5000),
('1222FW1w', 5000),
('1www', 5000),
('eevw23r', 5000);


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
('2023-08-04 12:31:56', 2, 2);

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