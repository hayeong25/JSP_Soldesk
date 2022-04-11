package servlettest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InfoServlet
 */
@WebServlet("/info")
public class InfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// UTF-8 설정
		request.setCharacterEncoding("UTF-8");
		
		// form에서 넘긴 값 가져오기
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		String job = request.getParameter("job");
		String favorite[] = request.getParameterValues("favorite");
		String gender = request.getParameter("gender");
		
		// 보여주는 페이지
		response.setContentType("text/html;charset=UTF-8");
		
		// 화면 출력
		PrintWriter out = response.getWriter();
		out.print("<p>userid : " + userid + "</p>");
		out.print("<p>password : " + password + "</p>");
		out.print("<p>job : " + job + "</p>");
		out.print("<p>favorite : " + Arrays.toString(favorite) + "</p>");
		out.print("<p>gender : " + gender + "</p>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
