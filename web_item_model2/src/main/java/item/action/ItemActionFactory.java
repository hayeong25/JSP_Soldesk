package item.action;

// SingleTon 패턴

public class ItemActionFactory {
	private static ItemActionFactory baf;
	
	private ItemActionFactory() {
		
	}
	
	public static ItemActionFactory getInstance() {
		if(baf == null) {
			baf = new ItemActionFactory();
		}
		return baf;
	}
	
	Action action = null;
	public Action action(String command) {
		// Action 생성 시 작업 성공 후 이동할 path 지정
		// SELECT는 .jsp로
		if(command.equals("/list.do")) {
			action = new ItemListAction("/list.jsp");
		}else if(command.equals("/insert.do")) {
			action = new ItemInsertAction("/list.do");
		}else if(command.equals("/update.do")) {
			action = new ItemModifyAction("/list.do");
		}else if(command.equals("/delete.do")) {
			action = new ItemRemoveAction("/list.do");
		}else if(command.equals("/search.do")) {
			action = new ItemSearchAction("/list.jsp");
		}
		
		return action;
	}
}