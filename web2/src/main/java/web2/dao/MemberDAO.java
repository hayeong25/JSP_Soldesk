package web2.dao;

import static web2.dao.JdbcUtil.*;
import java.sql.*;
import java.util.*;
import web2.dto.MemberDTO;

/*
	DAO : DB 관련 작업(CRUD)을 모아놓은 클래스
*/

public class MemberDAO {
	private Connection con;

	public MemberDAO(Connection con) {
		super();
		this.con = con;
	}
	
	/* ---------------- 전체 조회 (SELECT * FROM member; => List<MemberDTO>) ---------------- */
	public List<MemberDTO> getList() {
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String SQL = "SELECT * FROM member";
		
		try {
			pstmt = con.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getInt("id"));
				dto.setName(rs.getString("name"));
				dto.setAddr(rs.getString("addr"));
				dto.setEmail(rs.getString("email"));
				dto.setAge(rs.getInt("age"));
				
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
	
	/* ---------------- 조회 (SELECT * FROM member WHERE id = ?;) ---------------- */
	public MemberDTO getRow(int id) {
		MemberDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String SQL = "SELECT * FROM member WHERE id = ?";
		
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // select 결과값이 하나뿐이기 때문에 if문. 여럿이면 while
				dto = new MemberDTO();
				dto.setId(id);
				dto.setName(rs.getString("name"));
				dto.setAddr(rs.getString("addr"));
				dto.setEmail(rs.getString("email"));
				dto.setAge(rs.getInt("age"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return dto;
	}
	
	/* ---------------- member 추가 (INSERT INTO member VALUES(id, name, addr, email, age);  ---------------- */
	public boolean insert(MemberDTO insertDTO) {
		PreparedStatement pstmt = null;
		
		boolean flag = false;
		
		String SQL = "INSERT INTO member(id, name, addr, email, age) VALUES(member_seq.nextval, ?, ?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(SQL);
			
			pstmt.setString(1, insertDTO.getName());
			pstmt.setString(2, insertDTO.getAddr());
			pstmt.setString(3, insertDTO.getEmail());
			pstmt.setInt(4, insertDTO.getAge());
			
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