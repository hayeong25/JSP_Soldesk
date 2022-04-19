package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import board.dto.BoardDTO;
import board.service.BoardHitUpdateService;
import board.service.BoardViewService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BoardHitUpdateAction implements Action {
	private String path;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// request.getParameter >> bno 가져오기
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		// 조회수 업데이트 Service 호출
		BoardHitUpdateService service = new BoardHitUpdateService();
		service.count(bno);
		
		path += "?bno=" + bno;
		
		// ActionForward
		return new ActionForward(path, true);
	}

}