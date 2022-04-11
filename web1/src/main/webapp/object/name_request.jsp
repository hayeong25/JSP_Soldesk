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
		1) HttpServletRequest request : 사용자의 요청을 가져올 수 있음
		2) HttpServletResponse response : 사용자에게 응답할 때 사용
	--%>
<%
	// request.getParameter() : form 안에 있는 정보 가져오기 ex)?num=1&age=10
	request.setCharacterEncoding("UTF-8");
	String name = request.getParameter("username"); // 사용자가 input에 입력하는 name="username"
%>
<h3><%=name %></h3>
</body>
</html>