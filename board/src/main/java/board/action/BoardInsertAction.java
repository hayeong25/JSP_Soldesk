package board.action;

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
		
		// DB 작업 >> Service 호출
		BoardInsertService service = new BoardInsertService();
		boolean result = service.insert(insertDTO);
		
		if(!result) {
			path = "/view/qna_board_write.jsp";
		}
		
		// ActionForward(path, true)
		return new ActionForward(path, true);
	}

}