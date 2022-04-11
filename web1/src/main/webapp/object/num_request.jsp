<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>num : <%=request.getParameter("num") %></h3>
	<h3>page : <%=request.getParameter("page") %></h3>
	<h3>클라이언트 IP 주소 : <%=request.getRemoteAddr() %></h3>
	<h3>프로토콜 : <%=request.getProtocol() %></h3> <!--  HTTP / 버전 -->
	<h3>요청 메소드 : <%=request.getMethod() %></h3> <!-- get 방식인지 post 방식으로 넘어온 것인지 -->
	<h3><a href="response.jsp">이곳으로 이동</a></h3>
</body>
</html>