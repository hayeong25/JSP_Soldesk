package board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dto.BoardDTO;
import board.dto.SearchDTO;
import board.service.BoardSearchService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BoardSearchAction implements Action {
	private String path;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// request.getParameter
//		SearchDTO dto = new SearchDTO();
//		dto.setCriteria(request.getParameter("criteria"));
//		dto.setKeyword(request.getParameter("keyword"));
//		
//		// DB 작업 >> Service 호출
//		BoardSearchService service = new BoardSearchService();
//		List<BoardDTO> list = service.search(dto);
//		
//		if(list == null) {
//			path = "/qList.do";
//		}
//		
//		request.setAttribute("list", list);
//		
//		// ActionForward
//		return new ActionForward(path, false);
		
		return null;
	}

}
