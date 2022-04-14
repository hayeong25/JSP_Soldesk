package book.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.dto.BookDTO;
import book.service.BookInsertService;

public class BookInsertAction implements Action {
	private String path;

	public BookInsertAction(String path) {
		this.path = path;
	}

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// request.getParameter (없으면 skip)
		BookDTO dto = new BookDTO();
		dto.setCode(Integer.parseInt(request.getParameter("code")));
		dto.setTitle(request.getParameter("title"));
		dto.setWriter(request.getParameter("writer"));
		dto.setPrice(Integer.parseInt(request.getParameter("price")));
		
		// Service 호출
		BookInsertService service = new BookInsertService();
		if(!service.insertBook(dto)) {
			path = "/insert.jsp";
		}
		
		// ActionForward 객체 생성 후 return
		
		return new ActionForward(path, true);
	}
}