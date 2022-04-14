package book.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.service.BookUpdateService;

public class BookUpdateAction implements Action {
	private String path;
	
	public BookUpdateAction(String path) {
		this.path = path;
	}
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// request.getParameter (없으면 skip)
		int code = Integer.parseInt(request.getParameter("code"));
		int price = Integer.parseInt(request.getParameter("price"));
		
		// DB 작업 >> Service 호출
		BookUpdateService service = new BookUpdateService();
		if(!service.updateBook(code, price)) {
			path = "/update.jsp";
		}
		
		// ActionForward 객체 생성 후 return
		return new ActionForward(path, true);
	}
}