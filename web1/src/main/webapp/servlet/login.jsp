<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/login" method="post">
		<div>
			<label>아이디</label>
			<input type="text" name="userid" id="userid" />
		</div>
		<div>
			<label>비밃번호</label>
			<input type="password" name="password" id="password" />
		</div>
		<div>
			<input type="submit" value="전송" />
		</div>
	</form>
</body>
</html>