package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.AllArgsConstructor;
import member.service.MemberLeaveService;

@AllArgsConstructor
public class MemberLeaveAction implements Action {
	private String path;
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// request.getParameter (없으면 skip)
		String userid = request.getParameter("userid");
		String current_password = request.getParameter("current_password");
		
		// DB 작업 >> Service 호출
		MemberLeaveService service = new MemberLeaveService();
		boolean result = service.leaveMember(userid, current_password);
		
		if(!result) {
			path = "/view/leaveForm.jsp";
		}else {
			HttpSession session = request.getSession();
			session.invalidate();
		}
		
		return new ActionForward(path, true);
	}

}