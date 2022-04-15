package member.service;

import static member.dao.JdbcUtil.*;
import java.sql.Connection;
import member.dao.MemberDAO;
import member.dto.MemberDTO;

public class MemberLoginService {
	public MemberDTO loginMember(String userid, String password) {
		Connection con = getConnection();
		MemberDAO dao = new MemberDAO(con);
		MemberDTO loginDTO = dao.isLogin(userid, password);
		
		// dao,isLogin은 SELECT *  FROM ~
		// SELECT 문은 commit이나 rollback 필요 없으므로 connection만 닫으면 됨
		
		close(con);
		
		return loginDTO;
	}
}