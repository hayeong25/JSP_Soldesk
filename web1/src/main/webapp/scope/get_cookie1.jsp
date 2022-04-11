<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	// 브라우저가 기억하는 cookie 가져와 이름이 일치하는 cookie 값 가져오기
	private String getCookieValue(Cookie[] cookies, String name) {
		if(cookies == null) {
			return null;
		}
		for(Cookie c : cookies) {
			if(c.getName().equals(name)) {
				return c.getValue();
			}
		}
		return null;
	}
%>
<%
	Cookie[] cookies = request.getCookies();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>이름 : <%=getCookieValue(cookies, "name") %></h3>
</body>
</html>