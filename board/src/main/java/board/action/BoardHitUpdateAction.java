package board.action;

import java.net.URLEncoder;

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
		
		// 페이지 나누기 후 추가되는 부분
		String page = request.getParameter("page");
		String amount = request.getParameter("amount");
		String criteria = request.getParameter("criteria");
		String keyword = URLEncoder.encode(request.getParameter("keyword"), "UTF-8");
		
		// 조회수 업데이트 Service 호출
		BoardHitUpdateService service = new BoardHitUpdateService();
		service.count(bno);
		
		// 페이지 나누기 후 path 다시 설정
		// path += "?bno=" + bno;
		path += "?bno=" + bno + "&page=" + page + "&amount=" + amount + "&criteria=" + criteria + "&keyword=" + keyword;
		
		// ActionForward
		return new ActionForward(path, true);
	}

}