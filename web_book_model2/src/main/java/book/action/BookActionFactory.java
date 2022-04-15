package book.action;

// SingleTon 패턴

public class BookActionFactory {
	private static BookActionFactory baf;
	
	private BookActionFactory() {
		
	}
	
	public static BookActionFactory getInstance() {
		if(baf == null) {
			baf = new BookActionFactory();
		}
		return baf;
	}
	
	Action action = null;
	public Action action(String command) {
		// Action 생성 시 작업 성공 후 이동할 path 지정
		if(command.equals("/list.do")) {
			action = new BookListAction("/list.jsp");
		}else if(command.equals("/insert.do")) {
			action = new BookInsertAction("/list.do"); // list.jsp는 getAttribute부터 시작하기 때문에 setAttribute가 있는 list.do로 가야 함
		}else if(command.equals("/update.do")) {
			action = new BookUpdateAction("/list.do");
		}else if(command.equals("/search.do")) {
			action = new BookSearchAction("/list.jsp"); // list.do로 가게 되면 Controller로 가서 BookListAction으로 가 전체 목록 보여주기가 실행되므로 list.jsp로 가야 함
		}else if(command.equals("/delete.do")) {
			action = new BookDeleteAction("/list.do");
		}
		
		return action;
	}
}