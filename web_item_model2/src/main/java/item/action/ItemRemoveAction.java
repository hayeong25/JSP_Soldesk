package item.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.service.ItemRemoveService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ItemRemoveAction implements Action {
	private String path;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// request.getParameter (없으면 skip)
		String name = request.getParameter("name");
		
		// DB 작업 >> Service 호출
		ItemRemoveService service = new ItemRemoveService();
		boolean result = service.removeItem(name);
		
		if(!result) {
			path = "/delete.jsp";
		}
		
		// ActionForward
		return new ActionForward(path, true);
	}

}