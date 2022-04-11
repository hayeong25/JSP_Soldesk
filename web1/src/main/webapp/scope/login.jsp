<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	// request 영역 객체에 특정 값 저장
	// Parameter로 가져올 수 없는 속성값이므로 가져올 때는 getAttribute로 가져와야 함
	request.setAttribute("age", 20);
%>
	<form action="login_request.jsp" method="post">
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