package board.action;

import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dto.BoardDTO;
import board.service.BoardInsertService;
import board.util.UploadUtil;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BoardInsertAction implements Action {
	private String path;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		UploadUtil util = new UploadUtil();
		HashMap<String, String> dataMap = util.uploadFile(request);
		
		// request.getParameter >> dataMap에서 값 가져오기
		BoardDTO insertDTO = new BoardDTO();
		insertDTO.setName(dataMap.get("name"));
		insertDTO.setTitle(dataMap.get("title"));
		insertDTO.setContent(dataMap.get("content"));
		insertDTO.setPassword(dataMap.get("password"));
		insertDTO.setAttach(dataMap.get("attach"));
		
		// 페이지 나누기 후 추가
		int page = Integer.parseInt(dataMap.get("page"));
		int amount = Integer.parseInt(dataMap.get("amount"));
		String criteria = dataMap.get("criteria");
		String keyword = URLEncoder.encode(dataMap.get("keyword"), "UTF-8");
		
		// DB 작업 >> Service 호출
		BoardInsertService service = new BoardInsertService();
		boolean result = service.insert(insertDTO);
		
		if(!result) {
			path = "/view/qna_board_write.jsp?page=" + page + "&amount=" + amount + "&criteria=" + criteria + "&keyword=" + keyword;
		}else {
			path += "?page=" + page + "&amount=" + amount + "&criteria=" + criteria + "&keyword=" + keyword;
		}
		
		// ActionForward(path, true)
		return new ActionForward(path, true);
	}

}