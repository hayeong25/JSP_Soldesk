package item.service;

import static item.dao.JdbcUtil.*;
import java.sql.Connection;
import java.util.List;
import item.dao.ItemDAO;
import item.dto.ItemDTO;

public class ItemSearchService {
	public List<ItemDTO> searchItem(String category, String name) {
		Connection con = getConnection();
		ItemDAO dao = new ItemDAO(con);
		
		List<ItemDTO> list = dao.search(category, name);
		
		close(con);
		
		return list;
	}
}