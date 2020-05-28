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

INSERT INTO
	USERES
VALUES
	("test0", "qwer", "영유저", "01011112222", NULL, 'N')
	, ("test1", "asdf", "일유저", "01022223333", NULL, 'N')
	, ("test2", "zxcv", "이유저", "01033334444", NULL, 'N')
	, ("test3", "wert", "삼유저", "01044445555", NULL, 'N')
	, ("test4", "sdfg", "사유저", "01055556666", NULL, 'N')
	, ("test5", "aaaa", "오유저", "01066667777", NULL, 'N')
;
update useres set id='test6' where name='유저6';
select * from useres where id like '%est%';
SELECT * FROM USERES order by DATETIME_REGISTER desc;
select * from useres where id = 'test10' and DELETEFLAG = 'N';

SELECT
	U.ID
	, U.NAME
	, U.PHONE
	, (
		IFNULL((
			SELECT
				IL.VISABLE
			FROM
				INVITATION_LIST IL
			WHERE
				IL.ID = U.ID
				AND NOW()  >=  IL.DATE_BEGIN
				AND NOW()  <=  IL.DATE_END)
		, 'X')
	) AS STATUSSEE
FROM
	USERES U
WHERE
	U.DELETEFLAG  <>  'Y'
ORDER BY 
	DATETIME_REGISTER desc
LIMIT 0, 10;

select
	u.id, u.password, u.name, u.phone, date_format(u.datetime_register, '%Y-%m-%d') as registerDate, (
		select il_a.date_begin from invitation_list il_a where il_a.date_begin = (select max(il_b.date_begin) from invitation_list il_b where il_b.id='test1')
	) as latestInvitationBegin, (
		select il_a.date_end from invitation_list il_a where il_a.date_begin = (select max(il_b.date_begin) from invitation_list il_b where il_b.id='test1')
	) as latestInvitationEnd
from useres u
where u.id='test1';

SELECT
			U.ID
			, U.PASSWORD
			, U.NAME
			, U.PHONE
			, DATE_FORMAT(U.DATETIME_REGISTER, '%Y-%m-%d') AS REGISTERDATE
			, ( 
				SELECT
					IL_A.DATE_BEGIN
				FROM 
					INVITATION_LIST IL_A
				WHERE 
					IL_A.DATE_BEGIN = (
						SELECT
							MAX(IL_B.DATE_BEGIN)
						FROM
							INVITATION_LIST IL_B
						WHERE
							IL_B.ID = 'test2'
					)
			) AS LATESTINVITATIONBEGIN
			, ( 
				SELECT
					IL_A.DATE_END
				FROM 
					INVITATION_LIST IL_A
				WHERE 
					IL_A.DATE_BEGIN = (
						SELECT
							MAX(IL_B.DATE_BEGIN)
						FROM
							INVITATION_LIST IL_B
						WHERE
							IL_B.ID = 'test2'
					)
			) AS LATESTINVITATIONEND
		FROM
			USERES U
		WHERE U.ID = 'test2'
		;


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
	, FORMCODE VARCHAR(10) NOT NULL
	, FOREIGN KEY (ID) REFERENCES USERES (ID)
	, FOREIGN KEY (FORMCODE) REFERENCES INVITATION_FORM (FORMCODE)
);
alter table invitation_list change visible VISIBLE varchar(20) not null;
INSERT INTO
	INVITATION_LIST
values
	(NULL, "test0", "영유저", "N", "20191201", "20191225", "hookup")
	, (NULL, "test3", "삼유저", "Y", "20191229", "20200229", "hookup")
	, (NULL, "test2", "이유저", "N", "20200301", "20200512", "hookup")
	, (NULL, "test3", "삼유저", "N", "20200310", "20200329", "hookup")
;

select * from invitation_list where date_begin = (select max(date_begin) from invitation_list where id='test1');

SELECT * FROM INVITATION_LIST;
select CURDATE(); -- 2020-02-18
select NOW(); -- 2020-02-18 23:53:09
select DATE_FORMAT(NOW(), '%y%m%d'); -- 200218
select * from INVITATION_LIST 
where 
	now() >= DATE_BEGIN 
	and now() <= DATE_END ;

-- 청첩장 카테고리 분류 (첨부파일이 있는)
CREATE TABLE CATEGORY (
	CODE VARCHAR(5) PRIMARY KEY
	, NAME VARCHAR(15) NOT NULL
);

INSERT INTO
	CATEGORY
VALUES
	-- ("MI", "메인 및 기본 정보")
	-- ("LS", "LOVE STORY")
	-- ("G", "GALLERY")
;

SELECT * FROM CATEGORY;


-- 첨부파일 모음
-- 첨부파일(이미지, 썸네일) 싸그리 모으는 테이블로 하고 각 메뉴 테이블 컬럼에 첨부파일명 같이 넣어주기
CREATE TABLE ATTACH (
	SEQ INT PRIMARY KEY AUTO_INCREMENT
	, FULLNAME VARCHAR(150)
	, CATEGORY VARCHAR(5)
	, CATEGORY_SEQ INT
	, DATETIME_REGISTER TIMESTAMP DEFAULT NOW()
	, FOREIGN KEY (CATEGORY) REFERENCES CATEGORY(CODE)
);

SELECT * FROM ATTACH;

-- 메인 및 기본 정보 
CREATE TABLE MAIN_INFO (
	SEQ INT PRIMARY KEY
	, ID VARCHAR(20) NOT NULL
	, DATE_WEDDING VARCHAR(8) NOT NULL
	, TIME_WEDDING VARCHAR(4) NOT NULL
	, X_PLACE VARCHAR(30) NOT NULL
	, Y_PLACE VARCHAR(30) NOT NULL
	, ROAD_ADDR_PART1 VARCHAR(30) NOT NULL
	, JIBUN_ADDR VARCHAR(30) NOT NULL
	, ADDR_DETAIL VARCHAR(30) NOT NULL
	, CONTENT_GROOM VARCHAR(1000) NOT NULL
	, CONTENT_BRIDE VARCHAR(1000) NOT NULL
	, SEQ_IMG_MAIN INT
	, YN_EACH_IMG VARCHAR(1) DEFAULT 'N'
	, SEQ_IMG_GROOM INT
	, SEQ_IMG_BRIDE INT
	, FOREIGN KEY (SEQ) REFERENCES INVITATION_LIST (SEQ)
	, FOREIGN KEY (ID) REFERENCES USERES (ID)
);

INSERT INTO
	MAIN_INFO
VALUES
	(1, "test0", "20191220", "1000", "958312.8430575368", "1946055.0089419535", "서울특별시 강남구 학동로 158", "서울특별시 강남구 논현동 127-2 율암빌딩", "2층", "누구의 차남", "누구의 차녀", "", "N", "", "")
	, (2, "test3", "20191225", "1300", "967868.67319358", "1934771.4323645737", "경기도 성남시 분당구 벌말로39번길 13", "경기도 성남시 분당구 야탑동 302-9", "204호", "소오오오오개", "소오오오오개", "", "N", "", "")
	, (3, "test2", "20200228", "1100", "917327.0029600007", "1620377.7687504846", "전라남도 해남군 해남읍 군청길 27", "전라남도 해남군 해남읍 수성리 184-1 금영아파트", "103호", "누구의 차남2222", "누구의 차녀222", "", "N", "", "")
;

SELECT * FROM MAIN_INFO;


-- LOVE STORY
CREATE TABLE LOVE_STORY (
	SEQ INT PRIMARY KEY AUTO_INCREMENT
	, INV_SEQ INT NOT NULL
	, ID VARCHAR(20) NOT NULL
	, IS_USE BOOLEAN
	, IS_DELETE BOOLEAN
	, DATE_STORY VARCHAR(8) NOT NULL
	, TITLE VARCHAR(20) NOT NULL
	, CONTENT VARCHAR(200) NOT NULL
	, SEQ_IMAGE INT
	, FOREIGN KEY (INV_SEQ) REFERENCES INVITATION_LIST (SEQ)
	, FOREIGN KEY (ID) REFERENCES USERES (ID)
);

SELECT * FROM LOVE_STORY;


-- WHEN WHERE
CREATE TABLE WHEN_WHERE (
	SEQ INT NOT NULL
	, ID VARCHAR(20) NOT NULL
	, IS_PYEBAEK BOOLEAN
	, DATE_WW VARCHAR(8) DEFAULT '-'
	, TIME_WW VARCHAR(4) DEFAULT '-'
	, X_PLACE VARCHAR(30) DEFAULT '-'
	, Y_PLACE VARCHAR(30) DEFAULT '-'
	, ROAD_ADDR_PART1 VARCHAR(30) DEFAULT '-'
	, JIBUN_ADDR VARCHAR(30) DEFAULT '-'
	, ADDR_DETAIL VARCHAR(30) DEFAULT '-'
	, TITLE VARCHAR(20) NOT NULL
	, CONTENT VARCHAR(100) NOT NULL
	, FOREIGN KEY (ID) REFERENCES USERES (ID)
);

INSERT INTO
	WHEN_WHERE
VALUES
	(1, "test0", FALSE, '-', '-', '-', '-', '-', '-', '-', "결혼장소1", "결혼장소내용1")
	, (2, "test3", FALSE, '-', '-', '-', '-', '-', '-', '-', "결혼장소2", "결혼장소내용2")
	, (3, "test2", FALSE, '-', '-', '-', '-', '-', '-', '-', "결혼장소3", "결혼장소내용3")
;

SELECT * FROM WHEN_WHERE;

-- GALLERY
CREATE TABLE GALLERY (
	SEQ INT PRIMARY KEY AUTO_INCREMENT
	, INV_SEQ INT NOT NULL
	, ID VARCHAR(20) NOT NULL
	, IS_USE BOOLEAN
	, IS_DELETE BOOLEAN
	, SEQ_IMAGE INT
	, FOREIGN KEY (INV_SEQ) REFERENCES INVITATION_LIST (SEQ)
	, FOREIGN KEY (ID) REFERENCES USERES (ID)
);

SELECT * FROM GALLERY;

-- SWEET MESSAGE
CREATE TABLE SWEET_MESSAGE (
	SEQ INT PRIMARY KEY AUTO_INCREMENT
	, INV_SEQ INT NOT NULL
	, ID VARCHAR(20) NOT NULL
	, IS_USE BOOLEAN
	, IS_DELETE BOOLEAN
	, DATETIME_REGISTER TIMESTAMP DEFAULT NOW()
	, REGISTER_NAME VARCHAR(20) NOT NULL
	, REGISTER_CONTENT VARCHAR(100) NOT NULL
	, REGISTER_PASSWORD VARCHAR(20) NOT NULL
	, FOREIGN KEY (INV_SEQ) REFERENCES INVITATION_LIST (SEQ)
	, FOREIGN KEY (ID) REFERENCES USERES (ID)
);

INSERT INTO
	SWEET_MESSAGE
VALUES
	(NULL, 1, "test0", TRUE, FALSE, NULL, "GUEST1", "CON1", "1111")
	, (NULL, 2, "test3", TRUE, FALSE, NULL, "GUEST2", "CON2", "2222")
	, (NULL, 2, "test3", TRUE, FALSE, NULL, "GUEST3", "CON3", "3333")
	, (NULL, 2, "test3", TRUE, FALSE, NULL, "GUEST4", "CON4", "4444")
;

SELECT * FROM SWEET_MESSAGE;

select host, user, password from mysql.user;
show grants for 'root'@'%';
create user 'invitation'@'localhost' identified by '1111';
grant all privileges on INVITATION.* to 'invitation'@'localhost';