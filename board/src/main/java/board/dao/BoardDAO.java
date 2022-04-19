package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static board.dao.JdbcUtil.*;
import board.dto.BoardDTO;

public class BoardDAO {
	private Connection con;

	public BoardDAO(Connection con) {
		super();
		this.con = con;
	}
	
	/* ----------------------- 게시글 목록 ----------------------- */
	public List<BoardDTO> getList() {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String SQL = "SELECT bno, title, name, regdate, readcount FROM board ORDER BY bno DESC";
		
		try {
			pstmt = con.prepareStatement(SQL);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBno(rs.getInt("bno"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setReadCount(rs.getInt("readcount"));
				dto.setRegDate(rs.getDate("regdate"));
				
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
	
	/* ----------------------- 게시글 보기 ----------------------- */
	public BoardDTO viewArticle(int bno) {
		BoardDTO dto = new BoardDTO();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String SQL = "SELECT bno, name, title, content, attach FROM board WHERE bno = ?";
		
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