package book.service;

import java.sql.Connection;
import book.dao.BookDAO;
import static book.dao.JdbcUtil.*;

public class BookUpdateService {
	public boolean updateBook(int code, int price) {
		// DB 작업
		Connection con = getConnection();
		BookDAO dao = new BookDAO(con);
		
		boolean flag = dao.update(price, code);
		
		if(flag) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		
		return flag;
	}
}