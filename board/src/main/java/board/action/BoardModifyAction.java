package board.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dto.BoardDTO;
import board.service.BoardModifyService;
import board.util.UploadUtil;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BoardModifyAction implements Action {
	private String path;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UploadUtil util = new UploadUtil();
		HashMap<String, String> dataMap = util.uploadFile(request);
		
		// request.getParameter
		BoardDTO dto = new BoardDTO();
		dto.setBno(Integer.parseInt(dataMap.get("bno")));
		dto.setTitle(dataMap.get("title"));
		dto.setContent(dataMap.get("content"));
		dto.setPassword(dataMap.get("password"));
		dto.setAttach(dataMap.get("attach"));
		
		// DB 작업 >> Service 호출
		BoardModifyService service = new BoardModifyService();
		boolean result = service.modify(dto);
		
		if(!result) {
			path = "/qModify.do?bno=" + dto.getBno();
		}else {
			path += "?bno=" + dto.getBno();
		}
		
		// ActionForward
		return new ActionForward(path, true);
	}

}
