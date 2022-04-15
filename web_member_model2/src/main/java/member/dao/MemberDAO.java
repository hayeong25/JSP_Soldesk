package member.dao;

import static member.dao.JdbcUtil.*;
import java.sql.*;
import lombok.*;
import member.dto.MemberDTO;

/*
	C : 회원가입
	R : 로그인
	U : 비밀번호 변경
	D : 회원 탈퇴
*/

@AllArgsConstructor
public class MemberDAO {
	private Connection con;
	
	/* ---------------- 회원가입 (INSERT INTO membertbl VALUES(?, ?, ?, ?, ?)) ---------------- */
	public boolean register(MemberDTO registerDTO) {
		boolean flag = false;
		
		PreparedStatement pstmt = null;
		
		String SQL = "INSERT INTO membertbl VALUES(?, ?, ?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, registerDTO.getUserid());
			pstmt.setString(2, registerDTO.getPassword());
			pstmt.setString(3, registerDTO.getName());
			pstmt.setString(4, registerDTO.getGender());
			pstmt.setString(5, registerDTO.getEmail());
			
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
	
	/* ---------------- ID 중복 검사 (SELECT userid FROM membertbl WHERE userid = ?) ---------------- */
	public boolean checkID(String userid) {
		boolean flag = false;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String SQL = "SELECT userid FROM membertbl WHERE userid = ?";
		
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
					
			if(rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return flag;
	}
	
	
	/* ---------------- 로그인 (SELECT userid, name FROM membertbl WHERE id = ? AND password = ?) ---------------- */
	public MemberDTO isLogin(String userid, String password) {
		MemberDTO loginDTO = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String SQL = "SELECT userid, name FROM membertbl WHERE userid = ? AND password = ?";
		
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, userid);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				loginDTO = new MemberDTO();
				loginDTO.setUserid(rs.getString(1));
				loginDTO.setName(rs.getString(2));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return loginDTO;
	}
	
	/* ---------------- 비밀번호 변경 (UPDATE membertbl SET password = ? WHERE id = ?) ---------------- */
	public boolean changePassword(String password, String userid) {
		boolean flag = false;
		
		PreparedStatement pstmt = null;
		
		String SQL = "UPDATE membertbl SET password = ? WHERE userid = ?";
		
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, password);
			pstmt.setString(2, userid);
			
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
	
	
	/* ---------------- 회원 탈퇴 (DELETE FROM membertbl WHERE id = ?) ---------------- */
	public boolean leave(String userid, String password) {
		boolean flag = false;
		
		PreparedStatement pstmt = null;
		
		String SQL = "DELETE FROM membertbl WHERE userid = ? AND password = ?";
		
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, userid);
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