CREATE TABLE item (
	num NUMBER PRIMARY KEY,
	category NVARCHAR2(30) NOT NULL,
	name NVARCHAR2(50) NOT NULL,
	content NVARCHAR2(100) NOT NULL,
	psize VARCHAR2(30) NOT NULL,
	price NUMBER NOT NULL,
	register_at DATE DEFAULT SYSDATE
);

CREATE SEQUENCE item_seq;

INSERT INTO item(num, category, name, content, psize, price) VALUES(item_seq.nextval, 'outwear', '바지', '파란색 바지 XL', 'XL', 38000);

select * from item;
