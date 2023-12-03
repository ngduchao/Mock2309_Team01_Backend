DROP DATABASE IF EXISTS QL_Ban_Ve_Xem_phim;

CREATE DATABASE IF NOT EXISTS QL_Ban_Ve_Xem_phim;

USE QL_Ban_Ve_Xem_phim;

DROP TABLE IF EXISTS `User`;
CREATE TABLE IF NOT EXISTS `User` (
	`user_id`		INT AUTO_INCREMENT PRIMARY KEY,
    `username`	 	VARCHAR(50) NOT NULL UNIQUE CHECK (LENGTH(`username`) >= 6 AND LENGTH(`username`) <= 50),
    `email`			VARCHAR(50) NOT NULL UNIQUE CHECK (LENGTH(`email`) >= 6 AND LENGTH(`email`) <= 50),
    `password` 		VARCHAR(800) NOT NULL,
    `firstName` 	NVARCHAR(50) NOT NULL,
	`lastName` 		NVARCHAR(50) NOT NULL,
    `role` 			ENUM('Admin','Manager','User') DEFAULT 'User',
    `status`		BOOLEAN DEFAULT 0 -- 0: Not Active, 1: Active
);

DROP TABLE IF EXISTS `Film`;
CREATE TABLE IF NOT EXISTS `Film`(
	`film_id`			INT AUTO_INCREMENT PRIMARY KEY,
    `name`				VARCHAR(100) NOT NULL UNIQUE KEY,
    `directors`			VARCHAR(50) NOT NULL CHECK (LENGTH(directors) >= 6 AND LENGTH(directors) <= 50),
    `actors`			VARCHAR(200) NOT NULL,
    `genre`				VARCHAR(100) NOT NULL,
    `duration`			VARCHAR(30) NOT NULL,
    `description`		TEXT NOT NULL,
    `release_date` 		DATETIME NOT NULL DEFAULT NOW(),
    `ticket_price`		INT NOT NULL,
    `poster`			TEXT NOT NULL,
    `creator_id` 		INT NOT NULL,
    FOREIGN KEY (`creator_id`) REFERENCES `User`(`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS `Film_Schedule`;
CREATE TABLE IF NOT EXISTS `Film_Schedule`(
	`schedule_id`		INT AUTO_INCREMENT PRIMARY KEY,
    `film_id`			INT,
    `seat_number`		INT CHECK (seat_number > 0 AND seat_number <= 100),
    `time_slot`			DATETIME NOT NULL,
    FOREIGN KEY (`film_id`) REFERENCES `Film`(`film_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS `Ticket`;
CREATE TABLE IF NOT EXISTS `Ticket`(
	`creator_id`		INT,
    `schedule_id`		INT,
    `quantity`			INT NOT NULL,
    `total`				INT NOT NULL, 
    `booking_date`		DATETIME DEFAULT NOW(),
    `status`			BOOLEAN DEFAULT 0,
    PRIMARY KEY (`creator_id`, `schedule_id`),
    FOREIGN KEY (`creator_id`) REFERENCES `User`(`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`schedule_id`) REFERENCES `Film_schedule`(`schedule_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Create table Registration_User_Token
DROP TABLE IF EXISTS 	`Registration_User_Token`;
CREATE TABLE IF NOT EXISTS `Registration_User_Token` ( 	
	id 				INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	`token`	 		CHAR(36) NOT NULL UNIQUE,
	`user_id` 		SMALLINT UNSIGNED NOT NULL,
	`expiryDate` 	DATETIME NOT NULL
);

-- Create table Reset_Password_Token
DROP TABLE IF EXISTS 	`Reset_Password_Token`;
CREATE TABLE IF NOT EXISTS `Reset_Password_Token` ( 	
	id 				INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	`token`	 		CHAR(36) NOT NULL UNIQUE,
	`user_id` 		SMALLINT UNSIGNED NOT NULL,
	`expiryDate` 	DATETIME NOT NULL
);

-- password: 123456
INSERT INTO `User` (`username`, `email`, `password`, `firstname`, `lastname`, `role`, `status`)
VALUES				('duchao',    	'duchao0202@gmail.com', 	'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi', 'Nguyen', 	'Hao', 		'Admin',  1),
					('quanghieu', 	'quanghieu@gmail.com',  	'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi', 'Quang',  	'Hieu', 	'Manager', 1),
                    ('dinhdai',   	'dinhdai@gmail.com',    	'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi', 'Dinh',   	'Dai', 		'Manager',  1),
                    ('hoanggiang', 	'hoanggiang@gmail.com', 	'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi', 'Hoang', 	'Giang', 	'Manager', 1),
                    ('truongduong', 'truongduong@gmail.com', 	'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi', 'Truong', 	'Duong', 	'Manager', 1),
                    ('hongphong', 	'hongphong@gmail.com', 		'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi', 'Hong', 	'Phong', 	'Manager', 1),
                    ('ngoloi', 		'ngoloi@gmail.com', 		'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi', 'Ngo', 		'Loi', 		'Manager', 1),
                    ('nguyenvana', 	'nguyena@gmail.com', 		'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi', 'Nguyen', 	'A', 		'User', 1),
                    ('nguyenvanb', 	'nguyenb@gmail.com', 		'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi', 'Nguyen', 	'B', 		'User', 1);
                     
INSERT INTO `Film` (`name`, `directors`, `actors`, `genre`, `duration`, `description`, `release_date`, `ticket_price`, `poster`, `creator_id`)
VALUES				('Năm đêm kinh hoàng', 'Emma Tammi','Matthew Lillard, Josh Hutcherson, Mary Stuart Masterson', 'Kinh Dị', '110 phút', 'Nhân viên bảo vệ Mike bắt đầu làm việc tại Freddy Fazbear pizza...', '2023/10/27', 70000,'https://files.betacorp.vn/files/media%2fimages%2f2023%2f10%2f03%2f700x1000-5demkinhhoang-115804-031023-17.png' , 1),
					('Kị sĩ bóng đêm', 'Christopher Nolan', 'Christian Bale, Michael Caine, Heath Ledger, Aaron Eckhart', 'Hành động', '152 phút', 'Kị sĩ bóng đêm mở đầu bằng cuộc oanh tạc...', '2023/10/18', 70000,'https://drive.google.com/uc?export=view&id=1YJN9IhQUFz1putzE5frxvGRaR6uci6t4', 1),
                    ('Wolfoo và hòn đảo kỳ bí', 'Phan Thị Thơ', 'Sony Minh Hiếu, Đạt Phi, Như Ý', 'Hài, Hoạt Hình', '100 phút', 'Câu chuyện xoay quanh nhân vật chính là chú sói nhỏ ...', '2023/10/13', 70000,'https://drive.google.com/uc?export=view&id=1WOnjDPSBSLTVvJ161UF5E7wUDNnKVt6C', 2),
                    ('Vầng trăng máu', 'Martin Scorsese', 'Leonardo DiCaprio, Robert De Niro, Lily Gladstone', 'Bí ẩn, Hồi hộp', '206 phút', 'Vào những năm 1920, các thành viên của khu tự trị Osage...', '2023/10/20', 70000,'https://drive.google.com/uc?export=view&id=1YubEcYMc8_Wh9oK4VszqinQX5kOMOE8O', 3),
                    ('Cô giáo em là số 1', 'Park Jin - pyo', 'Shin Hae-sun, Lee Jun-young, Cha Chung-hwa,...', 'Hài hước, Hành động', '112 phút', 'Si-min là một võ sĩ quyền anh đầy triển vọng nhưng đã từ bỏ quyền thi đấu tại kỳ Thế vận hội Olympic để lấy tiền trả nợ cho cha.', '2023/11/24', 70000, 'https://drive.google.com/uc?export=view&id=18Hy9OV9Mt2pFzeYxeKhml-q9CkFmDwkJ', 1),
                    ('Biệt đội Marvel', 'Nia DaCosta', 'Brie Larson, Iman Vellani, Teyonah Parris, Zawe Ashton', 'Hành động, Phiêu lưu', '104 phút', 'Carol Danvers bị vướng vào sức mạnh của Kamala Khan và Monica Rambeau, buộc họ phải hợp tác cùng nhau để cứu vũ trụ.', '2023/11/10', 70000, 'https://drive.google.com/uc?export=view&id=16YEvdp5NJ2JSle4UUN2tKv5QWkdNgh5x', 1),
                    ('Quỷ Lùn Tinh Nghịch: Đồng Tâm Hiệp Nhạc', 'Tim Heitz', 'Anna Kendrick, Justin Timberlake, Troye Sivan', 'Phiêu lưu, Âm Nhạc', '92 phút', 'Sự xuất hiện đột ngột của John Dory, anh trai thất lạc đã lâu của Branch mở ra quá khứ bí mật được che giấu bấy lâu nay của Branch.', '2023/11/17', 70000, 'https://drive.google.com/uc?export=view&id=11t4grLsh1Nbrj63EUsrqEStXLFwBWY16', 1),
                    ('Đấu Trường Sinh Tử: Khúc Hát Của Chim Ca Và Rắn Độc', 'Francis Lawrence', 'Rachel Zegler, Hunter Schafer, Tom Blyth, Jason Schwartzman', 'Hành động, Phiêu lưu', '157 phút', 'Đấu Trường Sinh Tử: Khúc Hát Của Chim Ca Và Rắn Độc là phần tiền truyện của mạch truyện chính, lấy bối cảnh 64 năm trước phần phim đầu tiên.', '2023/11/17', 100000, 'https://drive.google.com/uc?export=view&id=1kwVEXWPWxcWjfiNRpSm5MMwdyj3_vyTg',1);
                
INSERT INTO `Film_Schedule` (`film_id`, `seat_number`, `time_slot`)
VALUES							(1, 100, '2023/10/25'),
								(2, 50, '2023/10/26'),
								(2, 50, '2023/10/27'),
								(2, 70, '2023/10/28'),
								(2, 100, '2023/10/29'),
                                (3, 70, '2023/10/27'),
                                (3, 100, '2023/10/28'),
                                (4, 100, '2023/10/29');
                                
INSERT INTO `Ticket` (`creator_id`, `schedule_id`, `quantity`, `total`, `booking_date`, `status`)
VALUES				(8, 1, 2, 140000, '2023/10/30', 1),
					(8, 2, 1, 70000,  '2023/11/01', 1),
                    (8, 3, 2, 140000, '2023/11/03', 1),
                    (9, 4, 2, 140000, '2023/11/05', 1),
                    (9, 5, 1, 70000,  '2023/11/10', 1);
				