package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import board.dto.BoardDTO;
import board.dto.SearchDTO;
import board.service.BoardHitUpdateService;
import board.service.BoardViewService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BoardViewAction implements Action {
	private String path;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// request.getParameter >> bno 가져오기
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		// 페이지 나누기 후 추가되는 부분
		int page = Integer.parseInt(request.getParameter("page"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		String criteria = request.getParameter("criteria");
		String keyword = request.getParameter("keyword");
		
		SearchDTO searchDTO = new SearchDTO(criteria, keyword, page, amount);
		
		// 새로고침 하면 자동으로 조회수 1 더 올라가기 때문에 HitUpdateAction 추가해 조회수 증가 제한
		
		// DB 작업 >> Service 호출
		BoardViewService service = new BoardViewService();
		BoardDTO dto = service.view(bno);
		
		request.setAttribute("dto", dto);
		request.setAttribute("searchDTO", searchDTO);
		
		// ActionForward
		return new ActionForward(path, false);
	}

}