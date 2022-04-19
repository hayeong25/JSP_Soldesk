package board.action;

public class BoardActionFactory {
	private static BoardActionFactory baf;
	
	private BoardActionFactory() {
		
	}
	
	public static BoardActionFactory getInstance() {
		if(baf == null) {
			baf = new BoardActionFactory();
		}
		return baf;
	}
	
	Action action = null;
	public Action action(String command) {
		// SELECT는 jsp로
		if(command.equals("/qWrite.do")) {
			action = new BoardInsertAction("/qList.do");
		}else if(command.equals("/qList.do")) {
			action = new BoardListAction("/view/qna_board_list.jsp");
		}else if(command.equals("/qView.do")) {
			action = new BoardViewAction("/view/qna_board_view.jsp");
		}else if(command.equals("/qHitUpdate.do")) {
			action = new BoardHitUpdateAction("/qView.do");
		}else if(command.equals("/qDelete.do")) {
			action = new BoardDeleteAction("/qList.do");
		}else if(command.equals("/qModify.do")) {
			action = new BoardViewAction("/view/qna_board_modify.jsp"); // view와 하는 작업이 동일하기 때문에 Action이 같음
		}else if(command.equals("/qUpdate.do")) {
			action = new BoardModifyAction("/qView.do");
		}
		
		return action;
	}
}