<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// session이 있는지 확인
	/*
	String name = "";
	if(session.getAttribute("name") != null) {
		name = (String)session.getAttribute("name");
	}else {
		name = "no session";
	}
	*/
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- <h1>현재 session 값 : <%=name %></h1> --%>
	<%
		// session 모두 가져오기
		Enumeration<String> sessions = session.getAttributeNames();
	
		String name = "";
		while(sessions.hasMoreElements()) {
			name = sessions.nextElement();
			if(name != null) {
				out.print("<h1>session 확인 : " + name + "</h1>");
			}
		}
	%>
	<hr />
	<h1>Session Test</h1>
	<button type="button" onclick="location.href='session_set.jsp'">session 값 저장</button>
	<button type="button" onclick="location.href='session_delete.jsp'">session 값 삭제</button>
	<button type="button" onclick="location.href='session_invalidate.jsp'">session 값 초기화</button>
</body>
</html>