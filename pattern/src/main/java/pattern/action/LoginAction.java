package pattern.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pattern.service.LoginService;

public class LoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// LoginForm에서 넘어온 값 가져오기 
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		
		// DB 작업 >> Service
		// 결과 값에 따른 페이지 이동
		LoginService service = new LoginService();
		String path = "";
		if(service.login(userid, password)) {
			path = "index.jsp";
		}else {
			path = "loginForm.jsp";
		}
		
		// 각 Action에서 작업이 끝난 후, path & 어떤 방식(forward? sendRedirect?)으로 움직일 것인지 return 해야 함
		return new ActionForward(path, true);
	}

}