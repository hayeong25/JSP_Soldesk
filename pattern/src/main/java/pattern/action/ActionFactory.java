package pattern.action;

/*
	SingleTon
	- default 생성자 : private, 다른 생성자는 제공 안 함
	- getInstance() 메소드 생성
*/

public class ActionFactory {
	// SingleTon 객체 생성
	private static ActionFactory af;
	
	private ActionFactory() {}
	
	public static ActionFactory getInstance() {
		if(af == null) {
			af = new ActionFactory();
		}
		return af;
	}
	
	Action action = null;
	public Action action(String command) {
		if(command.equals("/select.do")) {
			action = new SelectAction();
		}else if(command.equals("/insert.do")) {
			action = new InsertAction();
		}else if(command.equals("/login.do")) {
			action = new LoginAction();
		}
		
		// 생성한 action return
		return action;
	}
}