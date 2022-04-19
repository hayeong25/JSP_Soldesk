package board.service;

import static board.dao.JdbcUtil.*;
import java.sql.Connection;
import java.util.List;

import board.dao.BoardDAO;
import board.dto.BoardDTO;

public class BoardListService {
	public List<BoardDTO> list() {
		Connection con = getConnection();
		BoardDAO dao = new BoardDAO(con);
		List<BoardDTO> list = dao.getList();
		
		close(con);
		
		return list;
	}
}