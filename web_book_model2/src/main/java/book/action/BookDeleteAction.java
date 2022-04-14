package book.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.service.BookDeleteService;

public class BookDeleteAction implements Action {
	private String path;
	
	public BookDeleteAction(String path) {
		this.path = path;
	}

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// request.getParameter (없으면 skip)
		int code = Integer.parseInt(request.getParameter("code"));
		
		// DB 작업 >> Service 호출
		BookDeleteService service = new BookDeleteService();
		if(!service.deleteBook(code)) {
			path = "/delete.jsp";
		}
		
		// ActionForward
		return new ActionForward(path, true);
	}

}