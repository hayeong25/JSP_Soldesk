package member.service;

import java.sql.Connection;

import member.dao.MemberDAO;

import static member.dao.JdbcUtil.*;

public class MemberModifyService {
	public boolean modifyMember(String userid, String new_password) {
		Connection con = getConnection();
		MemberDAO dao = new MemberDAO(con);
		
		boolean result = dao.changePassword(new_password, userid);
		
		if(result) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}
}