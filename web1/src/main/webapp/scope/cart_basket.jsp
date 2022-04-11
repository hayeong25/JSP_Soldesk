<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// session 가져오기
	// String product = (String)session.getAttribute("product");

	ArrayList<String> productList = (ArrayList<String>)session.getAttribute("productList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 보기 페이지</title>
</head>
<body>
	<h3>장바구니 보기</h3>
	<h4><%=productList %></h4>
	<p><a href="cart.jsp">상품 선택 페이지로 돌아가기</a></p>
	<p><a href="cart_remove.jsp">장바구니 비우기</a></p>
</body>
</html>