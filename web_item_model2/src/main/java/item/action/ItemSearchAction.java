package item.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.dto.ItemDTO;
import item.service.ItemSearchService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ItemSearchAction implements Action {
	private String path;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// request.getParameter
		String category = request.getParameter("category");
		String name = request.getParameter("name");
		
		// DB 작업 >> Service 호출
		ItemSearchService service = new ItemSearchService();
		List<ItemDTO> list = service.searchItem(category, name);
		
		if(list == null) {
			path = "/search.jsp";
		}
		
		request.setAttribute("list", list);
		
		// ActionForward
		return new ActionForward(path, false);
	}

}