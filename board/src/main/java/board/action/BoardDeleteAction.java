package board.action;

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
		
		// DB 작업 >> Service 호출
		BoardDeleteService service = new BoardDeleteService();
		boolean result = service.delete(bno, password);
		
		if(!result) {
			path = "/view/qna_board_pwdCheck.jsp?bno=" + bno;
		}
		
		// ActionForward
		return new ActionForward(path, true);
	}

}
