CREATE TABLE bookTBL(
	code NUMBER(4) primary key,
	title NVARCHAR2(50) not null,
	writer NVARCHAR2(10) not null,
	price NUMBER(8)
);

INSERT INTO bookTBL VALUES(1000, '이것이 자바다', '신용균', 28000);
INSERT INTO bookTBL VALUES(1001, '자바의 신', '강신용', 29000);
INSERT INTO bookTBL VALUES(1002, '자바의 정석', '남궁성', 27000);
INSERT INTO bookTBL VALUES(1003, '자바 1000제', '김용만', 26000);