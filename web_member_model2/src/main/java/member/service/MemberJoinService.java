package member.service;

import static member.dao.JdbcUtil.*;
import java.sql.Connection;

import member.dao.MemberDAO;
import member.dto.MemberDTO;

public class MemberJoinService {
	public boolean joinMember(MemberDTO dto) {
		Connection con = getConnection();
		MemberDAO dao = new MemberDAO(con);
		
		boolean result = false;
		
		if(!dao.checkID(dto.getUserid())) {
			if(dao.register(dto)) {
				result = true;
				commit(con);
			}else {
				rollback(con);
			}
		}else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}
}