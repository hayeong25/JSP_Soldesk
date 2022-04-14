package book.service;

import java.sql.Connection;
import book.dao.BookDAO;
import static book.dao.JdbcUtil.*;

public class BookDeleteService {
	public boolean deleteBook(int code) {
		// DB 작업
		Connection con = getConnection();
		BookDAO dao = new BookDAO(con);
		
		boolean result = dao.delete(code);
		
		if(result) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}
}