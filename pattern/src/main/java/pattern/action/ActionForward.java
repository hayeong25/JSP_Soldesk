package pattern.action;

/*
	Action에서 path와 이동방식(forward? sendRedirect?) 를 한 번에 return 하기 위함
	Java에서는 여러 개의 값을 한 번에 return 할 수 없기 때문에 객체에 담아 return 해야 함
*/

public class ActionForward {
	private String path;
	private boolean redirect; // redirect가 true이면 sendRedirect 방식 false면 forward 방식
	
	public ActionForward() {
		
	}
	
	public ActionForward(String path, boolean redirect) {
		super();
		this.path = path;
		this.redirect = redirect;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() {
		return redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
}