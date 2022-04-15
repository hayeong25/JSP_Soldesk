package item.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import item.dto.ItemDTO;
import item.service.ItemInsertService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ItemInsertAction implements Action {
	private String path;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// request.getParameter (없으면 skip)
		ItemDTO insertDTO = new ItemDTO();
		insertDTO.setCategory(request.getParameter("category"));
		insertDTO.setName(request.getParameter("name"));
		insertDTO.setContent(request.getParameter("content"));
		insertDTO.setPsize(request.getParameter("psize"));
		insertDTO.setPrice(Integer.parseInt(request.getParameter("price")));
		
		// DB 작업 >> Service 호출
		ItemInsertService service = new ItemInsertService();
		boolean result = service.insertItem(insertDTO);
		
		if(!result) {
			path = "/insert.jsp";
		}
		
		return new ActionForward(path, true);
	}

}
