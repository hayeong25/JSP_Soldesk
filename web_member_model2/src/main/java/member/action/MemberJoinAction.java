package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;
import member.dto.MemberDTO;
import member.service.MemberJoinService;

@AllArgsConstructor
public class MemberJoinAction implements Action {
	private String path;
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// request.getParameter (없으면 skip)
		MemberDTO dto = new MemberDTO();
		dto.setUserid(request.getParameter("userid"));
		dto.setPassword(request.getParameter("password"));
		dto.setName(request.getParameter("name"));
		dto.setGender(request.getParameter("gender"));
		dto.setEmail(request.getParameter("email"));
		String confirm_password = request.getParameter("confirm_password");
		
		// DB 작업 >> Service 호출
		MemberJoinService service = new MemberJoinService();
		
		if(confirm_password.equals(dto.getPassword())) {
			if(!service.joinMember(dto)) {
				path = "/view/joinForm.jsp";
			}
		}else {
			path = "/view/joinForm.jsp";
		}
		
		// ActionForward(path, true)
		return new ActionForward(path, true);
	}

}