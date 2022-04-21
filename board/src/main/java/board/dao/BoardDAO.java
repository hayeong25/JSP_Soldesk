package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static board.dao.JdbcUtil.*;
import board.dto.BoardDTO;
import board.dto.SearchDTO;

public class BoardDAO {
	private Connection con;

	public BoardDAO(Connection con) {
		super();
		this.con = con;
	}
	
	/* ----------------------- 게시글 목록 ----------------------- */
	public List<BoardDTO> getList(SearchDTO searchDTO) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "";
		
		try {
			int start = searchDTO.getPage() * searchDTO.getAmount();
			int end = (searchDTO.getPage() - 1) * searchDTO.getAmount();
			
			if(searchDTO.getCriteria().isEmpty()) { // 단순 리스트 요청 시
				SQL = "SELECT * FROM (SELECT rownum as rnum, A.* "
						+ "FROM (SELECT bno, title, name, regdate, readcount, re_ref, re_lev, re_seq "
						+ "FROM board "
						+ "WHERE bno > 0 ORDER BY re_ref DESC, re_seq ASC) A "
						+ "WHERE rownum <= ?) "
						+ "WHERE rnum > ?";
				
				pstmt = con.prepareStatement(SQL);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
			}else { // 검색 요청 시
				SQL = "SELECT * FROM (SELECT rownum as rnum, A.* "
						+ "FROM (SELECT bno, title, name, regdate, readcount, re_ref, re_lev, re_seq "
						+ "FROM board "
						+ "WHERE bno > 0 AND " + searchDTO.getCriteria() + " LIKE ? ORDER BY re_ref DESC, re_seq ASC) A "
						+ "WHERE rownum <= ?) "
						+ "WHERE rnum > ?";
				
				pstmt = con.prepareStatement(SQL);
				pstmt.setString(1, "%" + searchDTO.getKeyword() + "%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			}
		
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBno(rs.getInt("bno"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setReadCount(rs.getInt("readcount"));
				dto.setRegDate(rs.getDate("regdate"));
				dto.setReRef(rs.getInt("re_ref"));
				dto.setReSeq(rs.getInt("re_seq"));
				dto.setReLev(rs.getInt("re_lev"));
				
				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	/* ----------------------- 전체 게시글 개수 ----------------------- */
	public int totalRows(String criteria, String keyword) {
		int total = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String SQL = "";
		
		try {
			if(criteria.isEmpty()) {
				SQL = "SELECT count(*) FROM board";
				pstmt = con.prepareStatement(SQL);
			}else {
				SQL = "SELECT count(*) FROM board WHERE " + criteria + " LIKE ?";
				pstmt = con.prepareStatement(SQL);
				pstmt.setString(1, "%" + keyword + "%");
			}
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				total = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return total;
	}
	
	/* ----------------------- 게시글 작성 ----------------------- */
	public boolean insertArticle(BoardDTO insertDTO) {
		boolean flag = false;
		
		PreparedStatement pstmt = null;
		
		String SQL = "INSERT INTO board(bno, name, password, title, content, attach, re_ref, re_lev, re_seq) VALUES(board_seq.nextval, ?, ?, ?, ?, ?, board_seq.currval, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, insertDTO.getName());
			pstmt.setString(2, insertDTO.getPassword());
			pstmt.setString(3, insertDTO.getTitle());
			pstmt.setString(4, insertDTO.getContent());
			pstmt.setString(5, insertDTO.getAttach());
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				flag = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return flag;
	}
	
	/* ----------------------- 답변글 작성 ----------------------- */
	public boolean replyArticle(BoardDTO dto) {
		boolean flag = false;
		
		PreparedStatement pstmt = null;
		
		String SQL = "";
		
		try {
			int re_ref = dto.getReRef();
			int re_lev = dto.getReLev();
			int re_seq = dto.getReSeq();
			
			// 댓글 삽입 전 업데이트
			SQL = "UPDATE board SET re_seq = re_seq + 1 WHERE re_ref = ? AND re_seq > ?";
			
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, re_ref);
			pstmt.setInt(2, re_seq);
			
			int updateResult = pstmt.executeUpdate();
			
			// PreparedStatement 닫고 다시 작업
			close(pstmt);
			
			// re_lev, re_seq 값 증가
			re_lev = re_lev + 1;
			re_seq = re_seq + 1;
			
			// 댓글 삽입
			SQL = "INSERT INTO board(bno, name, password, title, content, attach, re_ref, re_lev, re_seq) VALUES(board_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getAttach());
			pstmt.setInt(6, re_ref);
			pstmt.setInt(7, re_lev);
			pstmt.setInt(8, re_seq);
			
			int insertResult = pstmt.executeUpdate();
			
			if(insertResult > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return flag;
	}
	
	/* ----------------------- 게시글 보기 ----------------------- */
	public BoardDTO viewArticle(int bno) {
		BoardDTO dto = new BoardDTO();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String SQL = "SELECT bno, name, title, content, attach, re_ref, re_lev, re_seq FROM board WHERE bno = ?";
		
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, bno);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setBno(rs.getInt("bno"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setAttach(rs.getString("attach"));
				// 댓글 작업 시 필요한 부분도 가져오기
				dto.setReRef(rs.getInt("re_ref"));
				dto.setReSeq(rs.getInt("re_seq"));
				dto.setReLev(rs.getInt("re_lev"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return dto;
	}
	
	/* ----------------------- 게시글 조회수 증가 ----------------------- */
	public boolean countArticle(int bno) {
		boolean flag = false;
		
		PreparedStatement pstmt = null;
		
		String SQL = "UPDATE board SET readcount = readcount + 1 WHERE bno = ?";
		
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, bno);
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return flag;
	}
	
	/* ----------------------- 게시글 검색 ----------------------- */
	public List<BoardDTO> searchArticle(SearchDTO searchDTO) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String SQL = "SELECT bno, title, name, regdate, readcount, re_ref, re_seq, re_lev FROM board WHERE " + searchDTO.getCriteria() + " LIKE ? ORDER BY re_ref DESC, re_seq ASC";
		
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, "%" + searchDTO.getKeyword() + "%");
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBno(rs.getInt("bno"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setReadCount(rs.getInt("readcount"));
				dto.setRegDate(rs.getDate("regdate"));
				dto.setReRef(rs.getInt("re_ref"));
				dto.setReSeq(rs.getInt("re_seq"));
				dto.setReLev(rs.getInt("re_lev"));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	/* ----------------------- 게시글 수정 ----------------------- */
	public boolean modifyArticle(BoardDTO dto) {
		boolean flag = false;
		
		PreparedStatement pstmt = null;
		
		String SQL = "";
		
		// 첨부파일 유무에 따라 SQL문 달리함
		try {
			if(dto.getAttach() != null) {
				SQL = "UPDATE board SET title = ?, content = ?, attach = ? WHERE bno = ? AND password = ?";
				pstmt = con.prepareStatement(SQL);
				pstmt.setString(1, dto.getTitle());
				pstmt.setString(2, dto.getContent());
				pstmt.setString(3, dto.getAttach());
				pstmt.setInt(4, dto.getBno());
				pstmt.setString(5, dto.getPassword());
			}else {
				SQL = "UPDATE board SET title = ?, content = ? WHERE bno = ? AND password = ?";
				pstmt = con.prepareStatement(SQL);
				pstmt.setString(1, dto.getTitle());
				pstmt.setString(2, dto.getContent());
				pstmt.setInt(3, dto.getBno());
				pstmt.setString(4, dto.getPassword());
			}
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return flag;
	}
	
	
	/* ----------------------- 조회수 수정 ----------------------- */
	public boolean modifyReadCount(int num, int readcount) {
		boolean flag = false;
		
		PreparedStatement pstmt = null;
		
		String SQL = "UPDATE board SET readcount = ? WHERE bno = ?";
		
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, readcount);
			pstmt.setInt(2, num);
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				flag = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return flag;
	}
	
	
	/* ----------------------- 게시글 삭제 ----------------------- */
	public boolean deleteArticle(int bno, String password) {
		boolean flag = false;
		
		PreparedStatement pstmt = null;
		
		String SQL = "DELETE FROM board WHERE bno = (SELECT bno FROM board WHERE bno = ? AND password = ?)";
		
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, bno);
			pstmt.setString(2, password);
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return flag;
	}
}