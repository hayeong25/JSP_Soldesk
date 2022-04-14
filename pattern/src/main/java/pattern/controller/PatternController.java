package pattern.controller;

/*
	"~~~.do" >>
	Servlet(PatternController) - 전체적인 작업을 조율하는 곳 >>
	requestURI 받아와서 ActionFactory로 >>
	INSERT/SELECT/LOGIN 등 선택한 Action >>
	PatternController 내의 action.execute로 인해 해당 Action의 execute 메소드 실행 >>
	Action에서 Service 호출 >>
	Service에서 DB 작업을 위해 DAO 호출 >>
	DAO에서 DB 연산 후 결과 값 Service로 반환 >>
	 
*/

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pattern.action.Action;
import pattern.action.ActionFactory;

/**
 * Servlet implementation class PatternController
 */
@WebServlet("*.do")
public class PatternController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String command = request.getRequestURI();
		System.out.println("requestURI : " + command);
		
		// ActionFactory 객체 생성
		ActionFactory af = ActionFactory.getInstance();
		Action action = af.action(command);
		
		try {
			action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
