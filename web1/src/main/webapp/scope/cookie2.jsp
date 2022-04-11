<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Cookie c = new Cookie("name", "곰탱이");
	c.setMaxAge(600); // 600초 >> 브라우저를 닫아도 cookie(session)가 600초 동안 유효
	response.addCookie(c);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="get_cookie2.jsp">cookie 값 확인</a>
</body>
</html>