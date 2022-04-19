CREATE TABLE board(
	bno NUMBER(8), -- 글번호
	name NVARCHAR2(10) NOT NULL, -- 작성자
	password VARCHAR2(15) NOT NULL, -- 비밀번호
	title NVARCHAR2(50) NOT NULL, -- 글제목
	content NVARCHAR2(1000) NOT NULL, -- 글내용
	attach NVARCHAR2(100), -- 파일첨부
	re_ref NUMBER(8) NOT NULL, -- 답변글 작성시 참조되는 글 번호
	re_lev NUMBER(8) NOT NULL, -- 답변글 레벨
	re_seq NUMBER(8) NOT NULL, -- 답변글 순서
	readcount NUMBER(8) DEFAULT 0, -- 조회수
	regdate DATE DEFAULT SYSDATE -- 작성일
);

ALTER TABLE board ADD CONSTRAINT pk_board PRIMARY KEY(bno);

CREATE SEQUENCE board_seq;

INSERT INTO board(bno, name, password, title, content, attach, re_ref, re_lev, re_seq) VALUES(board_seq.nextval, '김모씨', '1234', 'JSP/Servlet 게시판', '게시판 작성하기', NULL, board_seq.currval, 0, 0);

select * from board;

-- 댓글, 검색, 페이지 나누기

-- 더미 데이터
insert into board(bno, name, password, title, content, re_ref, re_lev, re_seq)
(select board_seq.nextval, name, password, title, content, board_seq.currval, re_lev, re_seq from board);

SELECT bno, title, re_ref, re_seq, re_lev FROM board WHERE bno = 704;

-- 첫 답변글 작성
-- re_ref : 원본글의 re_ref 값과 동일하게 삽입
-- re_lev : 원본글의 re_lev + 1 삽입
-- re_seq : 원본글의 re_seq + 1 삽입
INSERT INTO board(bno, name, password, title, content, attach, re_ref, re_lev, re_seq) VALUES(board_seq.nextval, '답변', '1234', 're : 답변1', '답변 작성', NULL, 704, 1, 1);

-- 원본글과 댓글을 그룹으로 가져오기 (re_ref 사용)
SELECT bno, title, re_ref, re_seq, re_lev FROM board WHERE re_ref = 704;

-- 답변 작성 시 댓글을 최신순으로 추출할 수 있어야 함 (re_seq 사용)
-- ① 기존 댓글의 re_seq 값 업데이트 ex) UPDATE board SET re_seq = re_seq + 1 WHERE re_ref = 원본글의 re_ref and re_seq > 원본글의 re_seq
-- ② 새로운 댓글 삽입
	-- re_ref : 원본글의 re_ref 값과 동일하게 삽입
	-- re_lev : 원본글의 re_lev + 1 삽입
	-- re_seq : 원본글의 re_seq + 1 삽입

-- 두 번째 답변글 작성
INSERT INTO board(bno, name, password, title, content, attach, re_ref, re_lev, re_seq) VALUES(board_seq.nextval, '답변2', '1234', 're : 답변2', '답변 작성2', NULL, 704, 1, 1);