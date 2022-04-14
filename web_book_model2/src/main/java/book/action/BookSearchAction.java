package book.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import book.dto.BookDTO;
import book.service.BookSearchService;

public class BookSearchAction implements Action {
	private String path;
	
	public BookSearchAction(String path) {
		this.path = path;
	}
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// request.getParameter (없으면 skip)
		String criteria = request.getParameter("criteria");
		String keyword = request.getParameter("keyword");
		
		// DB 작업 >> Service 호출
		BookSearchService service = new BookSearchService();
		List<BookDTO> list = service.searchBook(criteria, keyword);
		
		request.setAttribute("list", list);
		
		// ActionForward
		return new ActionForward(path, true);
	}

}