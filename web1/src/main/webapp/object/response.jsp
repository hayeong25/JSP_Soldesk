<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--
		jsp 내장객체
		1) HttpServletRequest request : 사용자의 요청을 가져올 수 있음 (request.getParameter())
		2) HttpServletResponse response : 사용자에게 응답할 때 사용 (response.sendRedirect())
	--%>
	<h1>Response</h1>
	<%
		response.sendRedirect("num.jsp");
	%>
</body>
</html>