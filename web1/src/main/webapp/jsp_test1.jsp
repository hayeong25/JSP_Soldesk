<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
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
	브라우저에 Java 값 출력 방법
	1) out.print()
	2) %=
	
 --%>
<%
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
	out.print(sdf.format(date));
%>
<%=sdf.format(date) %>
</body>
</html>