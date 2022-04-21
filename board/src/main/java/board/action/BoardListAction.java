package board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dto.BoardDTO;
import board.dto.PageDTO;
import board.dto.SearchDTO;
import board.service.BoardListService;
import board.service.BoardTotalRowsService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BoardListAction implements Action {
	private String path;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 페이지 나누기
		int page = Integer.parseInt(request.getParameter("page")); // 현재 페이지
		int amount = 10; // 사용자가 게시물 몇 개씩 보길 원하는지
		String criteria = request.getParameter("criteria"); // 검색 조건
		String keyword = request.getParameter("keyword"); // 검색어
		
		SearchDTO searchDTO = new SearchDTO(criteria, keyword, page, amount);

		// DB 작업 >> Service 호출
		BoardListService service = new BoardListService();
		List<BoardDTO> list = service.list(searchDTO);
		
		// 전체 게시물 수
		BoardTotalRowsService rows = new BoardTotalRowsService();
		int totalRows = rows.total(criteria, keyword);
		
		PageDTO pageDTO = new PageDTO(totalRows, searchDTO);
		
		request.setAttribute("list", list);
		request.setAttribute("pageDTO", pageDTO);
		
		// ActionForward
		return new ActionForward(path, false);
	}

}