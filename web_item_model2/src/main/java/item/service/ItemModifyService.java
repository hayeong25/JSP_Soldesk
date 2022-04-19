package item.service;

import static item.dao.JdbcUtil.*;
import java.sql.Connection;

import item.dao.ItemDAO;

public class ItemModifyService {
	public boolean modifyItem(int num, String size, int price) {
		Connection con = getConnection();
		ItemDAO dao = new ItemDAO(con);
		
		boolean result = dao.modify(num, size, price);
		
		if(result) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}
}