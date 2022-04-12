package book.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import book.dto.BookDTO;
import static book.dao.JdbcUtil.*;

public class BookDAO {
	private Connection con;

	public BookDAO(Connection con) {
		super();
		this.con = con;
	}
	
	/* ---------------- 도서 전체 조회 (SELECT * FROM bookTBL) ---------------- */
	public List<BookDTO> getList() {
		List<BookDTO> list = new ArrayList<BookDTO>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String SQL = "SELECT * FROM bookTBL";
		
		try {
			pstmt = con.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BookDTO dto = new BookDTO();
				dto.setCode(rs.getInt("code"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setPrice(rs.getInt("price"));
				
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
	
	/* ---------------- 도서 입력 (INSERT INTO bookTBL VALUES(?, ?, ?, ?) ---------------- */
	public boolean insert(BookDTO dto) {
		boolean flag = false;
		
		PreparedStatement pstmt = null;
		
		String SQL = "INSERT INTO bookTBL VALUES(?, ?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(SQL);
			
			pstmt.setInt(1, dto.getCode());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getWriter());
			pstmt.setInt(4, dto.getPrice());
			
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
	
	/* ---------------- 도서 가격 수정 (UPDATE bookTBL SET price = ? WHERE code = ?) ---------------- */
	public boolean update(int price, int code) {
		boolean flag = false;
		
		PreparedStatement pstmt = null;
		
		String SQL = "UPDATE bookTBL SET price = ? WHERE code = ?";
		
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, price);
			pstmt.setInt(2, code);
			
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
	
	/* ---------------- 도서 삭제 (DELETE FROM bookTBL WHERE code = ?) ---------------- */
	public boolean delete(int code) {
		boolean flag = false;
		
		PreparedStatement pstmt = null;
		
		String SQL = "DELETE FROM bookTBL WHERE code = ?";
		
		try {
			pstmt = con.prepareStatement(SQL);
			
			pstmt.setInt(1, code);
			
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