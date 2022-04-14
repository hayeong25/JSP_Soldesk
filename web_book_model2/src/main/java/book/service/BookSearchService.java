package book.service;

import static book.dao.JdbcUtil.*;
import java.sql.Connection;
import java.util.List;
import book.dao.BookDAO;
import book.dto.BookDTO;

public class BookSearchService {
	public List<BookDTO> searchBook(String criteria, String keyword) {
		// DB 작업
		Connection con = getConnection();
		BookDAO dao = new BookDAO(con);
		
		List<BookDTO> list = dao.searchList(criteria, keyword);
		
		if(list != null) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return list;
	}
}