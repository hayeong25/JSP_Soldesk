<%@page import="member.dto.LoginDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	LoginDto loginDto = (LoginDto)session.getAttribute("loginDto");
%>
<%--
	JSP에서 제공하는 내장 객체들 중 page, request, session, application 객체들은
	해당 객체에 유효한 범위 안에서 필요한 객체(데이터)들을 저장하고 읽어들임으로써
	서로 공유할 수 있는 특정한 영역을 가지고 있음
	
	1) Object page : 현재 서비스 되고 있는 JSP 페이지의 scope를 가지고 있음(잘 안 씀)
	2) request : request scope를 가짐. action으로 지정한 페이지까지만 유효범위
	3) session : session scope를 가짐. 브라우저 열고 닫기 전까지 유효범위 ex)로그인, 장바구니 > 서버에 저장됨
	4) application : 서버의 시작과 끝의 유효범위를 가지고 있음 (잘 안 씀)
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--
		<p>session userid : <%=session.getAttribute("userid") %></p>
		<p>session password : <%=session.getAttribute("password") %></p>
	--%>
	<p>userid : <%=loginDto.getUserid() %></p>
	<p>password : <%=loginDto.getPassword() %></p>
</body>
</html>