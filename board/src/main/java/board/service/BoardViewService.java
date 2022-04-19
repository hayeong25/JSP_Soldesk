package board.service;

import java.sql.Connection;
import static board.dao.JdbcUtil.*;

import board.dao.BoardDAO;
import board.dto.BoardDTO;

public class BoardViewService {
	public BoardDTO view(int bno) {
		Connection con = getConnection();
		BoardDAO dao = new BoardDAO(con);
		BoardDTO dto = dao.viewArticle(bno);
		
		close(con);
		
		return dto;
	}
}
