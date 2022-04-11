<%@page import="member.dto.LoginDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// set, get은 Object로 들어가기 때문에 타입 설정 필수
	LoginDto loginDto = (LoginDto)session.getAttribute("loginDto");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	 <%--
	 	login.jsp 사용자 입력값을 여기서 출력 가능한가? >> 불가능함
		Action으로 부른 페이지까지만 출력 가능
	 --%>
	<p>request userid : <%=request.getParameter("userid") %></p>
	<br />
	<%--
		<p>session userid : <%=session.getAttribute("userid") %></p>
		<p>session password : <%=session.getAttribute("password") %></p>
	--%>
	<p>userid : <%=loginDto.getUserid() %></p>
	<p>password : <%=loginDto.getPassword() %></p>
	<br />
	<a href="scope.jsp">이동하기</a>
</body>
</html>