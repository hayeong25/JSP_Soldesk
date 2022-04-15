package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;
import member.service.MemberCheckIdService;

@AllArgsConstructor
public class MemberCheckIdAction implements Action {
	private String path;
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// request.getParameter (없으면 skip)
		String userid = request.getParameter("userid");
		
		// DB 작업 >> Service
		MemberCheckIdService service = new MemberCheckIdService();

		String result = "";
		if(service.checkID(userid)) {
			result = "true";
		}else {
			result = "false";
		}
		
		request.setAttribute("dupId", result);
		
		// ActionForward
		return new ActionForward(result, false);
	}

}