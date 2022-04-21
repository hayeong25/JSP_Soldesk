package board.action;

import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dto.BoardDTO;
import board.dto.SearchDTO;
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
		dto.setAttach(dataMap.get("attach")); // 파일 첨부를 했다면 값 有, 없으면 NULL
		
		// 페이지 나누기 후 추가
		int page = Integer.parseInt(dataMap.get("page"));
		int amount = Integer.parseInt(dataMap.get("amount"));
		String criteria = dataMap.get("criteria");
		String keyword = URLEncoder.encode(dataMap.get("keyword"), "UTF-8");
		
		// DB 작업 >> Service 호출
		BoardModifyService service = new BoardModifyService();
		boolean result = service.modify(dto);
		
		if(!result) {
			// path = "/qModify.do?bno=" + dto.getBno();
			path = "/qModify.do?bno=" + dto.getBno() + "&page=" + page + "&amount=" + amount + "&criteria=" + criteria + "&keyword=" + keyword;
		}else {
			// path += "?bno=" + dto.getBno();
			path += "?bno=" + dto.getBno() + "&page=" + page + "&amount=" + amount + "&criteria=" + criteria + "&keyword=" + keyword;
		}
		
		// ActionForward
		return new ActionForward(path, true);
	}

}
