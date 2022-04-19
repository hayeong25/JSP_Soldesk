package item.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.service.ItemModifyService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ItemModifyAction implements Action {
	private String path;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// request.getParameter
		int num = Integer.parseInt(request.getParameter("num"));
		String size = request.getParameter("psize");
		int price = Integer.parseInt(request.getParameter("price"));
		
		// DB 작업 >> Service 호출
		ItemModifyService service = new ItemModifyService();
		boolean result = service.modifyItem(num, size, price);
		
		if(!result) {
			path = "/update.jsp";
		}
		
		return new ActionForward(path, true);
	}

}