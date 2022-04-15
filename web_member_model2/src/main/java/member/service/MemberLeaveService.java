package member.service;

import java.sql.Connection;

import member.dao.MemberDAO;

import static member.dao.JdbcUtil.*;

public class MemberLeaveService {
	public boolean leaveMember(String userid, String password) {
		Connection con = getConnection();
		MemberDAO dao = new MemberDAO(con);
		
		boolean result = dao.leave(userid, password);
		
		if(result) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}
}