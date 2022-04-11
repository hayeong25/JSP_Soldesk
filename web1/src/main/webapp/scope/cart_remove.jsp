<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 장바구니 삭제
	// session.removeAttribute("product");
	
	session.removeAttribute("productList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>장바구니 삭제</h3>
	<p><a href="cart.jsp">상품 리스트로 돌아가기</a></p>
</body>
</html>