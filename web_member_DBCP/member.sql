CREATE TABLE membertbl(
	userid VARCHAR2(15) PRIMARY KEY,
	password VARCHAR2(20) NOT NULL,
	name NVARCHAR2(10) NOT NULL,
	gender NVARCHAR2(2) NOT NULL,
	email VARCHAR2(50) NOT NULL
);

INSERT INTO membertbl VALUES('gom', 'gom123', '곰탱이', '남', 'gom@gmail.com');
INSERT INTO membertbl VALUES('kim', 'kim123', '김모씨', '여', 'kim@gmail.com');

select * from MEMBERTBL;