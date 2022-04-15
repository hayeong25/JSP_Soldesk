package item.service;

import static item.dao.JdbcUtil.*;
import java.sql.Connection;
import java.util.List;

import item.dao.ItemDAO;
import item.dto.ItemDTO;

public class ItemListService {
	public List<ItemDTO> listItem() {
		Connection con = getConnection();
		ItemDAO dao = new ItemDAO(con);
		
		List<ItemDTO> list = dao.getList();
		
		// SELECT 문은 commit이나 rollback 필요 없으므로 connection만 닫으면 됨
		
		close(con);
		
		return list;
	}
}