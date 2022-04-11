<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%> <!-- errorPage : 에러가 났을 경우 어디에서 처리할 것인지 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	int a = 1, b = 0;
%>
<p>a와 b의 사칙연산</p>
<p>a + b = <%=a+b %></p>
<p>a - b = <%=a-b %></p>
<p>a * b = <%=a*b %></p>
<p>a / b = <%=a/b %></p> <!-- 0으로 나누면 Arithmetic Exception 발생 -->
<!-- 500 error : 서버 프로그램 에러 (코딩을 잘못했다는 뜻) -->
<!-- jsptest2.jsp를 실행시키지만 에러가 발생하면서 error.jsp의 내용이 출력된다 -->
</body>
</html>