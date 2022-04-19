package board.service;

import java.sql.Connection;
import board.dao.BoardDAO;
import static board.dao.JdbcUtil.*;

public class BoardHitUpdateService {
	public boolean count(int bno) {
		Connection con = getConnection();
		BoardDAO dao = new BoardDAO(con);
		boolean result = dao.countArticle(bno);
		
		if(result) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}
}