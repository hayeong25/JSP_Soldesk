package board.action;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dto.BoardDTO;
import board.service.BoardReplyService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BoardReplyAction implements Action {
	private String path;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// qna_board_reply.jsp에서 넘긴 값 가져오기
		BoardDTO dto = new BoardDTO();
		dto.setName(request.getParameter("name"));
		dto.setTitle(request.getParameter("title"));
		dto.setContent(request.getParameter("content"));
		dto.setPassword(request.getParameter("password"));
		
		// 원본글의 bno, re_ref, re_lev, re_seq 가져오기
		int bno = Integer.parseInt(request.getParameter("bno"));
		dto.setReRef(Integer.parseInt(request.getParameter("re_ref")));
		dto.setReLev(Integer.parseInt(request.getParameter("re_lev")));
		dto.setReSeq(Integer.parseInt(request.getParameter("re_seq")));
		
		// 페이지 나누기 후 추가되는 부분
		String page = request.getParameter("page");
		String amount = request.getParameter("amount");
		String criteria = request.getParameter("criteria");
		String keyword = URLEncoder.encode(request.getParameter("keyword"), "UTF-8");
		
		// DB 작업 >> Service 호출
		BoardReplyService service = new BoardReplyService();
		boolean result = service.reply(dto);
		
		if(!result) {
			path = "/qReplyView.do?bno=" + bno + "&page=" + page + "&amount=" + amount + "&criteria=" + criteria + "&keyword=" + keyword;;
		}else {
			path += "?page=" + page + "&amount=" + amount + "&criteria=" + criteria + "&keyword=" + keyword;
		}
		
		// ActionForward
		return new ActionForward(path, true);
	}

}