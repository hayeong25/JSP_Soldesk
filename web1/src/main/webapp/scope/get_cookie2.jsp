<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// cookie 값 가져오기
	String cookies = request.getHeader("Cookie");

	String name = "";
	String value = "";

	if(cookies != null) {
		Cookie cookie[] = request.getCookies(); // getCookies는 Cookie 배열로 return
		
		for(int i = 0; i < cookie.length; i++) {
			if(cookie[i].getName().equals("name")) {
				name = cookie[i].getName();
				value = cookie[i].getValue();
			}
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Cookie Name : <%=name %></h3>
	<h3>Cookie Value : <%=value %></h3>
</body>
</html>