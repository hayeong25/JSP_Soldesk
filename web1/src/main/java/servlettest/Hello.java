package servlettest;

// Servlet : 브라우저에서 실행시킬 수 있는 특수한 Class 파일

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Hello
 */
@WebServlet("/Hello") // localhost:포트번호/Hello 라고 url 창에 치면 doGet의 내용 출력됨
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/* get 방식 => doGet */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 보여줄 페이지 설정
		response.setContentType("text/html;charset=UTF-8");
		
		// 화면에 출력하기 (out 메소드가 없어 out 객체를 얻어와야 함)
		PrintWriter out = response.getWriter();
		out.print("Hello");
		out.print("<br>");
		out.print("안녕하세요"); // 한글은 ?????로 처리됨
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/* post 방식 => doPost */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}