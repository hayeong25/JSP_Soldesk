package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.AllArgsConstructor;
import member.dto.MemberDTO;
import member.service.MemberLoginService;
import member.service.MemberModifyService;

@AllArgsConstructor
public class MemberModifyAction implements Action {
	private String path;
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// request.getParameter (없으면 skip)
		HttpSession session = request.getSession();
		MemberDTO loginDTO = (MemberDTO)session.getAttribute("loginDTO");
		String userid = loginDTO.getUserid();
		String current_password = request.getParameter("current_password");
		String new_password = request.getParameter("new_password");
		String confirm_password = request.getParameter("confirm_password");
		
		// DB 작업 >> Service 호출
		MemberLoginService loginService = new MemberLoginService();
		if(loginService.loginMember(userid, current_password) != null) { // 현재 비밀번호가 일치 하면
			MemberModifyService service = new MemberModifyService();
			
			if(new_password.equals(confirm_password)) { // 비밀번호 같은지 확인
				boolean result = service.modifyMember(userid, confirm_password);
				if(!result) { // 비밀번호 변경 실패 시
					path = "/view/modifyForm.jsp";
				}else { // 비밀번호 변경 성공 후 session 날리고 다시 로그인
					session.invalidate();					
				}
			}
		}else { // 현재 비밀번호가 일치하지 않는 경우
			path = "/view/modifyForm.jsp";
		}
		
		// ActionForward(path, true) return
		return new ActionForward(path, true);
	}
}