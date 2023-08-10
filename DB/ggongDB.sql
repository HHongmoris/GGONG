DROP DATABASE IF EXISTS ggong;

create database IF NOT EXISTS ggong;

use ggong;

CREATE TABLE `user` (
	`user_no` INT NOT NULL AUTO_INCREMENT,
	`nickname` VARCHAR(45) NOT NULL,
	`age_range` VARCHAR(45) NOT NULL,
	`gender` VARCHAR(45) NOT NULL,
	`email` VARCHAR(45) NOT NULL,
	`favorite_cigarette` VARCHAR(45) NOT NULL,
	`QR` VARCHAR(100) NOT NULL,
	`user_rating` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`user_no`)
)ENGINE = InnoDB DEFAULT CHARACTER SET utf8mb4;

CREATE TABLE `machine` (
	`machine_no` INT NOT NULL AUTO_INCREMENT,
	`latitude` POINT NOT NULL,
	`longitude`	POINT NOT NULL,
	`name` VARCHAR(45) NOT NULL,
	`SN` VARCHAR(45) NOT NULL,
	`area_gu`VARCHAR(45) NOT NULL,
    PRIMARY KEY (`machine_no`)
)ENGINE = InnoDB DEFAULT CHARACTER SET utf8mb4;

CREATE TABLE `favoritemachine` (
	`user_no` INT NOT NULL,
	`machine_no` INT NOT NULL,
    PRIMARY KEY (`user_no`, `machine_no`),
    FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`),
    FOREIGN KEY (`machine_no`) REFERENCES `machine` (`machine_no`)
)ENGINE = InnoDB DEFAULT CHARACTER SET utf8mb4;

CREATE TABLE `question` (
	`question_ID` INT NOT NULL AUTO_INCREMENT,
	`content` VARCHAR(45) NOT NULL,
	`group` INT NOT NULL,
	`category` VARCHAR(45) NOT NULL,
	`optionA` VARCHAR(45) NOT NULL,
	`optionB` VARCHAR(45) NOT NULL,
	`type` INT NOT NULL,
    PRIMARY KEY (`question_ID`)
) ENGINE = InnoDB DEFAULT CHARACTER SET utf8mb4;

CREATE TABLE `vote` (
    `vote_no` INT NOT NULL AUTO_INCREMENT,
    `user_no` INT,
    `machine_no` INT NOT NULL,
    `question_ID` INT NOT NULL,
    `vote_date` DATETIME NOT NULL,
    `answer` TINYINT NOT NULL,
    PRIMARY KEY(`vote_no`),
    FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`),
    FOREIGN KEY (`machine_no`) REFERENCES `machine` (`machine_no`),
    FOREIGN KEY (`question_ID`) REFERENCES `question` (`question_ID`)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4;

CREATE TABLE `product` (
    `product_no` INT NOT NULL AUTO_INCREMENT,
    `pin` VARCHAR(60) NOT NULL,
    `price` INT NOT NULL,
    PRIMARY KEY(`product_no`)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4;

CREATE TABLE `buy` (
    `buy_no` INT NOT NULL AUTO_INCREMENT,
    `buy_time` DATETIME NOT NULL,
    `user_no` INT NOT NULL,
    `product_no` INT NOT NULL,
    PRIMARY KEY(`buy_no`),
    FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`),
    FOREIGN KEY (`product_no`) REFERENCES `product` (`product_no`)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4;

CREATE TABLE `point` (
    `point_no` INT NOT NULL AUTO_INCREMENT,
    `event_time` DATETIME NOT NULL,
    `point` INT NOT NULL,
    `balance_point` INT NOT NULL,
    `vote_no` INT NOT NULL,
    `user_no` INT NOT NULL,
    `buy_no` INT NOT NULL,
    PRIMARY KEY(`point_no`),
    FOREIGN KEY (`vote_no`) REFERENCES `vote` (`vote_no`),
    FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`),
    FOREIGN KEY (`buy_no`) REFERENCES `buy` (`buy_no`)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4;