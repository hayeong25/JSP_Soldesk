package board.service;

import static board.dao.JdbcUtil.*;
import java.sql.Connection;
import java.util.List;
import board.dao.BoardDAO;
import board.dto.BoardDTO;
import board.dto.SearchDTO;

public class BoardSearchService {
	public List<BoardDTO> search(SearchDTO searchDTO) {
		Connection con = getConnection();
		BoardDAO dao = new BoardDAO(con);
		List<BoardDTO> list = dao.searchArticle(searchDTO);
		
		close(con);
		
		return list;
	}
}