package book.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.dto.BookDTO;
import book.service.BookListService;

public class BookListAction implements Action {
	private String path;
	
	public BookListAction(String path) {
		this.path = path;
	}

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// request.getParameter 작업 (없으면 skip)
		
		// Service 호출
		BookListService service = new BookListService();
		List<BookDTO> list = service.listAll();
		
		request.setAttribute("list", list);
		
		return new ActionForward(path, false);
	}
	
}