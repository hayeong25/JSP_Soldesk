<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
</head>
<body>
	<h3>상품 리스트</h3>
	<form action="cart_add.jsp" method="get">
		<input type="radio" name="product" value="BMW"/>BMW
		<input type="radio" name="product" value="SM5"/>SM5
		<input type="radio" name="product" value="Audi"/>Audi
		<input type="submit" value="저장" />
	</form>
</body>
</html>