package book.service;

import java.sql.Connection;
import static book.dao.JdbcUtil.*;
import book.dao.BookDAO;
import book.dto.BookDTO;

public class BookInsertService {
	public boolean insertBook(BookDTO insertDTO) {
		// DB 작업
		Connection con = getConnection();
		BookDAO dao = new BookDAO(con);
		
		boolean flag = dao.insert(insertDTO);
		
		if(flag) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return flag;
	}
}