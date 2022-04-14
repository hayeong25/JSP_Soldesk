package book.service;

import static book.dao.JdbcUtil.*;
import java.sql.Connection;
import java.util.*;
import book.dao.BookDAO;
import book.dto.BookDTO;

public class BookListService {
	public List<BookDTO> listAll() {
		Connection con = getConnection();
		BookDAO dao = new BookDAO(con);
		
		List<BookDTO> list = dao.getList();
		
		close(con);
		
		return list;
	}
}