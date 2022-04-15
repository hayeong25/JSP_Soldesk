package item.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import item.dto.ItemDTO;
import item.service.ItemListService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ItemListAction implements Action {
	private String path;
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// request.getParameter (없으면 skip)
		
		// DB 작업 >> Service 호출
		ItemListService service = new ItemListService();
		List<ItemDTO> list = service.listItem();
		
		request.setAttribute("list", list);
		
		// ActionForward(path, true)
		return new ActionForward(path, false);
	}

}