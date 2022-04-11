<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		// session 영역에 값 담기
		session.setAttribute("name", "Session Test");
		session.setAttribute("age", "26");
	
		// 원래 페이지로 돌아가기
		response.sendRedirect("sessionTest1.jsp");
	%>
</body>
</html>