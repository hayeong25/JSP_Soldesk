package member.action;

import javax.servlet.http.*;
import lombok.*;
import member.dto.MemberDTO;
import member.service.MemberLoginService;

@AllArgsConstructor
public class MemberLoginAction implements Action {
	private String path;
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// request.getParameter (없으면 skip)
		String userid = request.getParameter("userid");
		String password = request.getParameter("current_password");
		
		// DB 작업 >> Service 호출
		MemberLoginService service = new MemberLoginService();
		MemberDTO loginDTO = service.loginMember(userid, password);
		
		HttpSession session = request.getSession();	
		session.setAttribute("loginDTO", loginDTO);
		
		if(loginDTO == null) {
			path = "/view/loginForm.jsp";
		}
		
		return new ActionForward(path, true);
	}

}