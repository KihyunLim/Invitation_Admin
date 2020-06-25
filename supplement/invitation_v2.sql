-- 관리자
CREATE TABLE ADMIN (
	ID VARCHAR(20) PRIMARY KEY
	, PASSWORD VARCHAR(20) NOT NULL
);

INSERT INTO 
	ADMIN
VALUES
	("root", "1234")
;

SELECT * FROM ADMIN;

-- 사용자
CREATE TABLE USERES (
	ID VARCHAR(20) PRIMARY KEY
	, PASSWORD VARCHAR(20) NOT NULL
	, NAME VARCHAR(20) NOT NULL
	, PHONE VARCHAR(11) NOT NULL
	, DATETIME_REGISTER TIMESTAMP DEFAULT NOW()
	, DELETEFLAG VARCHAR(1) default 'N'
);

select * from useres;

-- 청첩장 양식
CREATE TABLE INVITATION_FORM (
	FORMCODE VARCHAR(10) PRIMARY KEY
	, FORMNAME VARCHAR(10) NOT NULL
);

INSERT INTO
	INVITATION_FORM
VALUES
	("hookup", "hookup")
;

SELECT * FROM INVITATION_FORM;


-- 청첩장 목록
CREATE TABLE INVITATION_LIST (
	SEQ INT PRIMARY KEY AUTO_INCREMENT
	, ID VARCHAR(20) NOT NULL
	, NAME VARCHAR(20) NOT NULL
	, VISIBLE VARCHAR(1) DEFAULT 'N'
	, DATE_BEGIN VARCHAR(8) NOT NULL
	, DATE_END VARCHAR(8) NOT NULL
	, FORMCODE VARCHAR(10) NOT null
	, USE_EACH_IMAGE VARCHAR(1)
	, USE_LS VARCHAR(1)
	, USE_PYEBAEK VARCHAR(1)
	, USE_G VARCHAR(1)
	, USE_SM VARCHAR(1)
	, DATETIME_REGISTER TIMESTAMP DEFAULT NOW()
	, DATETIME_UPDATE TIMESTAMP DEFAULT NOW()
	, FOREIGN KEY (ID) REFERENCES USERES (ID)
	, FOREIGN KEY (FORMCODE) REFERENCES INVITATION_FORM (FORMCODE)
);
DROP TABLE invitation_list;

SELECT * FROM INVITATION_LIST;
TRUNCATE TABLE invitation_list;

-- 청첩장 카테고리 분류 (첨부파일이 있는)
CREATE TABLE CATEGORY (
	CODE VARCHAR(5) PRIMARY KEY
	, FORMCODE VARCHAR(10) NOT NULL
	, NAME VARCHAR(15) NOT NULL
);

INSERT INTO
	CATEGORY
VALUES
	("MI", "hookup", "메인 및 기본 정보")
	, ("LS", "hookup", "LOVE STORY")
	, ("G", "hookup", "GALLERY")
;
DROP TABLE category;

SELECT * FROM CATEGORY;


-- 첨부파일 모음
-- 첨부파일(이미지, 썸네일) 싸그리 모으는 테이블로 하고 각 메뉴 테이블 컬럼에 첨부파일명 같이 넣어주기
CREATE TABLE ATTACH (
	SEQ INT PRIMARY KEY AUTO_INCREMENT
	, INV_SEQ INT
	, FULLNAME VARCHAR(150)
	, CATEGORY VARCHAR(5)
	, FORMCODE VARCHAR(10)
	, DATETIME_REGISTER TIMESTAMP DEFAULT NOW()
	, FOREIGN KEY (CATEGORY) REFERENCES CATEGORY(CODE)
	, FOREIGN KEY (FORMCODE) REFERENCES INVITATION_FORM(FORMCODE)
);
DROP TABLE ATTACH;

SELECT * FROM ATTACH;
TRUNCATE TABLE attach;

-- 메인 및 기본 정보 
CREATE TABLE MAIN_INFO (
	SEQ INT PRIMARY KEY AUTO_INCREMENT
	, INV_SEQ INT NOT NULL
	, ID VARCHAR(20) NOT NULL
	, DATE_WEDDING VARCHAR(8) NOT NULL
	, TIME_WEDDING VARCHAR(4) NOT NULL
	, X_PLACE VARCHAR(30) NOT NULL
	, Y_PLACE VARCHAR(30) NOT NULL
	, ADDRESS VARCHAR(100) NOT NULL
	, CONTENT_GROOM VARCHAR(1000) NOT NULL
	, CONTENT_BRIDE VARCHAR(1000) NOT NULL
	, SEQ_IMG_MAIN INT
	, YN_EACH_IMAGE VARCHAR(1) DEFAULT 'N'
	, SEQ_IMG_GROOM INT
	, SEQ_IMG_BRIDE INT
	, DATETIME_REGISTER TIMESTAMP DEFAULT NOW()
	, DATETIME_UPDATE TIMESTAMP DEFAULT NOW()
	, FOREIGN KEY (INV_SEQ) REFERENCES INVITATION_LIST (SEQ)
	, FOREIGN KEY (ID) REFERENCES USERES (ID)
);
DROP TABLE main_info;

SELECT * FROM MAIN_INFO;

-- LOVE STORY
CREATE TABLE LOVE_STORY (
	SEQ INT PRIMARY KEY AUTO_INCREMENT
	, INV_SEQ INT NOT NULL
	, ORDER_SEQ INT NOT NULL
	, ID VARCHAR(20) NOT NULL
	, IS_DELETE BOOLEAN
	, DATE_STORY VARCHAR(8) NOT NULL
	, TITLE VARCHAR(20) NOT NULL
	, CONTENT VARCHAR(200) NOT NULL
	, SEQ_IMAGE INT
	, DATETIME_REGISTER TIMESTAMP DEFAULT NOW()
	, DATETIME_UPDATE TIMESTAMP DEFAULT NOW()
	, FOREIGN KEY (INV_SEQ) REFERENCES INVITATION_LIST (SEQ)
	, FOREIGN KEY (ID) REFERENCES USERES (ID)
);
DROP TABLE love_story;

SELECT * FROM LOVE_STORY;

-- WHEN WHERE
CREATE TABLE WHEN_WHERE (
	SEQ INT PRIMARY KEY AUTO_INCREMENT
	, INV_SEQ INT NOT NULL
	, ID VARCHAR(20) NOT NULL
	, FLAG_PYEBAEK VARCHAR(1) NOT NULL
	, DATE_WW VARCHAR(8) NOT NULL
	, TIME_WW VARCHAR(4) NOT NULL
	, X_PLACE VARCHAR(30) NOT NULL
	, Y_PLACE VARCHAR(30) NOT NULL
	, ADDRESS VARCHAR(100) NOT NULL
	, TITLE VARCHAR(20) NOT NULL
	, CONTENT VARCHAR(100) NOT null
	, DATETIME_REGISTER TIMESTAMP DEFAULT NOW()
	, DATETIME_UPDATE TIMESTAMP DEFAULT NOW()
	, FOREIGN KEY (INV_SEQ) REFERENCES INVITATION_LIST (SEQ)
	, FOREIGN KEY (ID) REFERENCES USERES (ID)
);
DROP TABLE when_where;

SELECT * FROM WHEN_WHERE;

-- GALLERY
CREATE TABLE GALLERY (
	SEQ INT PRIMARY KEY AUTO_INCREMENT
	, INV_SEQ INT NOT NULL
	, ORDER_SEQ INT NOT NULL
	, ID VARCHAR(20) NOT NULL
	, IS_DELETE BOOLEAN
	, SEQ_IMAGE INT
	, DATETIME_REGISTER TIMESTAMP DEFAULT NOW()
	, DATETIME_UPDATE TIMESTAMP DEFAULT NOW()
	, FOREIGN KEY (INV_SEQ) REFERENCES INVITATION_LIST (SEQ)
	, FOREIGN KEY (ID) REFERENCES USERES (ID)
);
DROP TABLE gallery;

SELECT * FROM GALLERY;
SELECT
			SEQ
			, INV_SEQ
			, ID
			, IS_DELETE
			, SEQ_IMAGE
			, (SELECT FULLNAME FROM ATTACH WHERE SEQ = G.SEQ_IMAGE) FULLNAME_IMAGE
			, DATETIME_REGISTER
			, DATETIME_UPDATE
		FROM
			GALLERY G
		WHERE
			IS_DELETE = FALSE
			AND INV_SEQ = 1
		ORDER BY
			ORDER_SEQ ASC
		;

-- SWEET MESSAGE
CREATE TABLE SWEET_MESSAGE (
	SEQ INT PRIMARY KEY AUTO_INCREMENT
	, INV_SEQ INT NOT NULL
	, ID VARCHAR(20) NOT NULL
	, IS_DELETE BOOLEAN
	, REGISTER_NAME VARCHAR(20) NOT NULL
	, REGISTER_CONTENT VARCHAR(100) NOT NULL
	, REGISTER_PASSWORD VARCHAR(20) NOT null
	, DATETIME_REGISTER TIMESTAMP DEFAULT NOW()
	, DATETIME_UPDATE TIMESTAMP DEFAULT NOW()
	, FOREIGN KEY (INV_SEQ) REFERENCES INVITATION_LIST (SEQ)
	, FOREIGN KEY (ID) REFERENCES USERES (ID)
);
DROP TABLE sweet_message;

SELECT * FROM SWEET_MESSAGE;

select host, user, password from mysql.user;
show grants for 'root'@'%';
create user 'invitation'@'localhost' identified by '1111';
grant all privileges on INVITATION.* to 'invitation'@'localhost';

SET foreign_key_checks = 0;
SET foreign_key_checks = 1;

DROP TABLE invitation_list;
SELECT * FROM INVITATION_LIST;
TRUNCATE TABLE invitation_list;

DROP TABLE ATTACH;
SELECT * FROM ATTACH;
TRUNCATE TABLE attach;

DROP TABLE main_info;
SELECT * FROM MAIN_INFO;
TRUNCATE TABLE MAIN_INFO;

DROP TABLE love_story;
SELECT * FROM LOVE_STORY;
TRUNCATE TABLE LOVE_STORY;

DROP TABLE when_where;
SELECT * FROM WHEN_WHERE;
TRUNCATE TABLE WHEN_WHERE;

DROP TABLE gallery;
SELECT * FROM GALLERY;
TRUNCATE TABLE GALLERY;

DROP TABLE sweet_message;
SELECT * FROM SWEET_MESSAGE;
TRUNCATE TABLE SWEET_MESSAGE;

SHOW TRIGGERS FROM INVITATION;

CREATE DEFINER=`root`@`localhost` TRIGGER main_info_after_update
AFTER UPDATE 
ON main_info FOR EACH ROW
BEGIN
	DECLARE _INV_SEQ INT;
	DECLARE _DATE_WEDDING VARCHAR(8);
	DECLARE _TIME_WEDDING VARCHAR(4);
	DECLARE _X_PLACE VARCHAR(30);
	DECLARE _Y_PLACE VARCHAR(30);
	DECLARE _ADDRESS VARCHAR(100);
	DECLARE _YN_EACH_IMAGE VARCHAR(1);
	
	SET _INV_SEQ = new.INV_SEQ;
	SET _DATE_WEDDING = new.DATE_WEDDING;
	SET _TIME_WEDDING = new.TIME_WEDDING;
	SET _X_PLACE = new.X_PLACE;
	SET _Y_PLACE = new.Y_PLACE;
	SET _ADDRESS = new.ADDRESS;
	SET _YN_EACH_IMAGE  = new.YN_EACH_IMAGE;
	
	UPDATE 
		WHEN_WHERE 
	SET 
		DATE_WW = _DATE_WEDDING
		, TIME_WW = _TIME_WEDDING
		, X_PLACE = _X_PLACE
		, Y_PLACE = _Y_PLACE
		, ADDRESS = _ADDRESS
		, DATETIME_UPDATE = NOW()
	WHERE
		INV_SEQ = _INV_SEQ
		AND FLAG_PYEBAEK = 'N'
	;
	
	UPDATE
		INVITATION_LIST
	SET
		USE_EACH_IMAGE = _YN_EACH_IMAGE
		, DATETIME_UPDATE = NOW()
	WHERE
		SEQ = _INV_SEQ
	;
END