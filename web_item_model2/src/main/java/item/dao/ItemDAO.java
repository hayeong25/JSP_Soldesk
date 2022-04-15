package item.dao;

import static item.dao.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import item.dto.ItemDTO;

public class ItemDAO {
	private Connection con;

	public ItemDAO(Connection con) {
		super();
		this.con = con;
	}
	
	/* ------------------- 상품 목록 (SELECT * FROM item) ------------------- */
	public List<ItemDTO> getList() {
		List<ItemDTO> list = new ArrayList<ItemDTO>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String SQL = "SELECT * FROM item ORDER BY num";
		
		try {
			pstmt = con.prepareStatement(SQL);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ItemDTO dto = new ItemDTO();
				dto.setNum(rs.getInt("num"));
				dto.setCategory(rs.getString("category"));
				dto.setName(rs.getString("name"));
				dto.setPsize(rs.getString("psize"));
				dto.setPrice(rs.getInt("price"));
				dto.setRegisterAt(rs.getDate("register_at"));
				
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
	
	/* ------------------- 상품 검색 (SELECT * FROM item WHERE name LIKE %?%)  ------------------- */
	public List<ItemDTO> search(String criteria, String keyword) {
		List<ItemDTO> list = new ArrayList<ItemDTO>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String SQL = "SELECT * FROM item WHERE name LIKE %?%";
		
		try {
			pstmt = con.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	/* ------------------- 상품 등록 (INSERT INTO item VALUES(?, ?, ?, ?, ?, ?)  ------------------- */
	public boolean insert(ItemDTO insertDTO) {
		boolean flag = false;
		
		PreparedStatement pstmt = null;
		
		String SQL = "INSERT INTO item VALUES(item_seq.nextval, ?, ?, ?, ?, ?, SYSDATE)";
		
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, insertDTO.getCategory());
			pstmt.setString(2, insertDTO.getName());
			pstmt.setString(3, insertDTO.getContent());
			pstmt.setString(4, insertDTO.getPsize());
			pstmt.setInt(5, insertDTO.getPrice());
			
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
	
	/* ------------------- 상품 수정 (UPDATE item SET price = ? WHERE name = ?)  ------------------- */
	public boolean modify(String name, int price) {
		boolean flag = false;
		
		PreparedStatement pstmt = null;
		
		String SQL = "UPDATE item SET price = ? WHERE name = ?";
		
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, price);
			pstmt.setString(2, name);
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/* ------------------- 상품 삭제 (DELETE FROM item WHERE name = ?)  ------------------- */
	public boolean remove(String name) {
		boolean flag = false;
		
		PreparedStatement pstmt = null;
		
		String SQL = "DELETE FROM item WHERE name = ?";
		
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, name);
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
}