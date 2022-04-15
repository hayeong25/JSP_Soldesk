package member.action;

// SingleTon 패턴

public class MemberActionFactory {
	private static MemberActionFactory baf;
	
	private MemberActionFactory() {
		
	}
	
	public static MemberActionFactory getInstance() {
		if(baf == null) {
			baf = new MemberActionFactory();
		}
		return baf;
	}
	
	Action action = null;
	public Action action(String command) {
		// Action 생성 시 작업 성공 후 이동할 path 지정
		if(command.equals("/login.do")) {
			action = new MemberLoginAction("/view/loginForm.jsp");
		}else if(command.equals("/logout.do")) {
			action = new MemberLogoutAction("/view/loginForm.jsp");
		}else if(command.equals("/join.do")) {
			action = new MemberJoinAction("/view/loginForm.jsp");
		}else if(command.equals("/modify.do")) {
			action = new MemberModifyAction("/view/loginForm.jsp");
		}else if(command.equals("/leave.do")) {
			action = new MemberLeaveAction("/index.jsp");
		}else if(command.equals("/checkId.do")) {
			action = new MemberCheckIdAction("/view/checkID.jsp");
		}
		
		return action;
	}
}