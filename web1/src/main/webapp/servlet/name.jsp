<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- Servlet 구현 (URL mapping /name) --%>
	<form action="/name" method="get">
		<div>
			<label>이름</label>
			<input type="text" name="username" id="username" required="required" />
		</div>
		<div>
			<button type="submit">보내기</button>
		</div>
	</form>
</body>
</html>