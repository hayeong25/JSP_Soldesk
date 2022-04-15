package item.service;

import static item.dao.JdbcUtil.*;
import java.sql.Connection;

import item.dao.ItemDAO;

public class ItemModifyService {
	public boolean modifyItem(String name, int price) {
		Connection con = getConnection();
		ItemDAO dao = new ItemDAO(con);
		
		boolean result = dao.modify(name, price);
		
		if(result) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}
}