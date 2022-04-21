CREATE TABLE board(
	bno NUMBER(8), -- 글번호
	name NVARCHAR2(10) NOT NULL, -- 작성자
	password VARCHAR2(15) NOT NULL, -- 비밀번호
	title NVARCHAR2(50) NOT NULL, -- 글제목
	content NVARCHAR2(1000) NOT NULL, -- 글내용
	attach NVARCHAR2(100), -- 파일첨부
	re_ref NUMBER(8) NOT NULL, -- 답변글 작성시 참조되는 글 번호 (원본글과 댓글을 하나의 그룹으로 처리)
	re_lev NUMBER(8) NOT NULL, -- 답변글 레벨 (원본글에 대한 답변글인지, 답변글에 대한 답변글인지)
	re_seq NUMBER(8) NOT NULL, -- 답변글 순서 (같은 레벨의 답변글이어도 최신순으로 정렬)
	readcount NUMBER(8) DEFAULT 0, -- 조회수
	regdate DATE DEFAULT SYSDATE -- 작성일
);

ALTER TABLE board ADD CONSTRAINT pk_board PRIMARY KEY(bno);

CREATE SEQUENCE board_seq;

INSERT INTO board(bno, name, password, title, content, attach, re_ref, re_lev, re_seq) VALUES(board_seq.nextval, '김모씨', '1234', 'JSP/Servlet 게시판', '게시판 작성하기', NULL, board_seq.currval, 0, 0);

select * from board;

-- <댓글>
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
	SELECT bno, title, re_ref, re_seq, re_lev FROM board WHERE re_ref = 704 ORDER BY re_seq;
	
	-- 답변 작성 시 댓글을 최신순으로 추출할 수 있어야 함 (re_seq 사용)
	-- ① 기존 댓글의 re_seq 값 업데이트 ex) UPDATE board SET re_seq = re_seq + 1 WHERE re_ref = 원본글의 re_ref and re_seq > 원본글의 re_seq
	UPDATE board SET re_seq = re_seq + 1 WHERE re_ref = 704 AND re_seq > 0;
	-- ② 새로운 댓글 삽입
		-- re_ref : 원본글의 re_ref 값과 동일하게 삽입
		-- re_lev : 원본글의 re_lev + 1 삽입
		-- re_seq : 원본글의 re_seq + 1 삽입
	
	-- 두 번째 답변글 작성
	INSERT INTO board(bno, name, password, title, content, attach, re_ref, re_lev, re_seq) VALUES(board_seq.nextval, '답변2', '1234', 're : 답변2', '답변 작성2', NULL, 704, 1, 1);
	
	-- ③ 댓글의 댓글 삽입 : 기존 댓글의 re_seq 값 업데이트 후 삽입 ex) UPDATE board SET re_seq = re_seq + 1 WHERE re_ref = 원본글의 re_ref and re_seq > 원본글의 re_seq
	UPDATE board SET re_seq = re_seq + 1 WHERE re_ref = 704 AND re_seq > 1;
	INSERT INTO board(bno, name, password, title, content, attach, re_ref, re_lev, re_seq) VALUES(board_seq.nextval, '답변3', '1234', 're:re : 답변3', '답변 작성3', NULL, 704, 2, 1);
	
	SELECT bno, title, name, regdate, readcount, re_ref, re_seq, re_lev FROM board ORDER BY re_ref DESC, re_seq ASC;

-- <페이지 나누기>
	-- MySQL : limit
	-- Oracle : rownum(가상의 컬럼) - 조회된 결과에 번호를 순서적으로 매겨줌
	SELECT rownum, bno, title FROM board ORDER BY bno DESC;
	
	-- rownum 사용할 때 ORDER BY 절 사용시 ORDER BY에 오는 컬럼은 index로 설정되어 있어야 함
	-- 1페이지 클릭 => 가장 최신 게시물 1번부터 10번까지
	-- 2페이지 클릭 => 가장 최신 게시물 11번부터 20번까지
	-- rownum > int는 불가. rownum < int만 가능
	SELECT rownum, bno, title FROM board WHERE rownum > 1; -- (X)
	SELECT rownum, bno, title FROM board WHERE rownum <= 10; -- (O)
	
	-- 원하는 대로 최신 글 추출하기
	SELECT rownum, bno, title FROM board WHERE rownum <= 10 ORDER BY bno DESC; -- (X)
	SELECT rownum, bno, title FROM board WHERE rownum <= 10 ORDER BY re_ref DESC, re_seq ASC; -- (X)
	
	-- 게시판에서 사용하려면 우선 정렬한 후에 rownum을 부여해야 함
	SELECT rownum, bno, title FROM (SELECT bno, title FROM board WHERE bno > 0 ORDER BY re_ref DESC, re_seq ASC) WHERE rownum <= 10; -- (X)
	
	-- 1) 전체 게시물 조회 후 정렬하여 보여주기
	-- 2) 1)번 결과에 rownum 부여 후 10 이하인 것만 추출
	-- 3) 2)번 결과에서 ruum이 0보다 큰 것만 추출
		-- 페이지번호 * 한 페이지 당 보여줄 게시물 수 / (페이지번호 - 1) * 한 페이지 당 보여줄 게시물 수
		-- 1페이지 : rownum <= 10, rnum > 0, start = 1*10, end = (1-1)*10
		-- 2페이지 : rownum <= 20, rnum > 10, start = 2*10, end = (2-1)*10
	SELECT *
	FROM (SELECT rownum as rnum, A.*
		  FROM (SELECT bno, title, name, regdate, readcount, re_ref, re_lev, re_seq
		  		FROM board
		  		WHERE bno > 0 AND title LIKE '%jsp%'
		  		ORDER BY re_ref DESC, re_seq ASC) A
		  WHERE rownum <= 10)
	WHERE rnum > 0;
	
	
	
	
	
	
