package board.action;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardDeleteService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BoardDeleteAction implements Action {
	private String path;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// request.getParameter
		int bno = Integer.parseInt(request.getParameter("bno"));
		String password = request.getParameter("password");
		String page = request.getParameter("page");
		String amount = request.getParameter("amount");
		String criteria = request.getParameter("criteria");
		String keyword = URLEncoder.encode(request.getParameter("keyword"), "UTF-8");
		
		// DB 작업 >> Service 호출
		BoardDeleteService service = new BoardDeleteService();
		boolean result = service.delete(bno, password);
		
		if(!result) {
			path = "/view/qna_board_pwdCheck.jsp?bno=" + bno + "&page=" + page + "&amount=" + amount + "&criteria=" + criteria + "&keyword=" + keyword;
		}else {
			path += "?page=" + page + "&amount=" + amount + "&criteria=" + criteria + "&keyword=" + keyword;
		}
		
		// ActionForward
		return new ActionForward(path, true);
	}

}
