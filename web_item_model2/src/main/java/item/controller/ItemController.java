package item.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import item.action.*;

@WebServlet("*.do")
public class ItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// requestURI 확인 및 추출
		// URI : 통합 자원 식별자(Uniform Resource Identifier)
		String command = request.getRequestURI();
		
		// BookActionFactory를 이용해 Action 생성
		ItemActionFactory baf = ItemActionFactory.getInstance();
		Action action = baf.action(command);
		
		// 생성된 Action에게 요청 넘기기
		ActionForward af = null;
		try {
			af = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// af에는 path와 이동방식이 들어 있음
		if(af.isRedirect()) { // Action에서 ActionForward(path, true)로 넘긴 경우
			response.sendRedirect(af.getPath());
		}else { // Action에서 ActionForward(path, false)로 넘겨 forward로 처리해야 하는 경우
			// RequestDispatcher : 클라이언트로부터 요청 받은 정보를 서버의 다른 자원(HTML, JSP, Servlet 등)에 보내는 역할을 하는 인터페이스
			RequestDispatcher rd = request.getRequestDispatcher(af.getPath());
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
