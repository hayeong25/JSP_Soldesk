<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	jsp 내장객체
	1) HttpServletRequest request : 사용자의 요청을 가져올 수 있음 (request.getParameter())
	2) HttpServletResponse response : 사용자에게 응답할 때 사용 (response.sendRedirect())
	3) javax.sevlet.jsp.PageContext pageContext : 다른 내장 객체에 접근 가능한 객체
	4) HttpSession session : http(s) 연결 방식의 stateless(계속 로그인 해야 하는 단점)를 보완하기 위해 등장. 원래 브라우저를 닫는 순간 session이 종료됨
	5) ServletContext application : 서버 정보와 서버 자원에 대한 정보 및 해당 어플리케이션의 이벤트 로그를 다루는 객체
	6) JspWriter out : 자바 변수를 화면 출력시 사용
	7) Object page : 현재 서비스 되고 있는 JSP
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	for(int i = 1; i < 101; i++) {
		out.print(i + " ");
	}
%>
</body>
</html>