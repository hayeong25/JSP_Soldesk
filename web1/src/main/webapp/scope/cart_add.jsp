<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 장바구니에 담기(session 이용) - 하나의 상품만 추가
	// session.setAttribute("product", request.getParameter("product"));

	// 사용자 선택 값 가져오기
	String product = request.getParameter("product");

	// 장바구니 session이 있는지 확인한 후 productList에 추가하기
	ArrayList<String> productList = (ArrayList<String>)session.getAttribute("productList");
	
	if(productList == null) {
		productList = new ArrayList<String>();
		productList.add(product);
		session.setAttribute("productList", productList);
	}else {
		productList.add(product);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 추가</title>
</head>
<body>
	<h3>제품 추가</h3>
	<p><a href="cart_basket.jsp">장바구니 보기</a></p>
</body>
</html>