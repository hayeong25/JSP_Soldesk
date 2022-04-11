<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%--
	form
	get 방식 : url에서 ? 뒤에 변수들이 보이게 함. 한글도 인코딩 안 깨지고 처리 가능
	post 방식 : url에서 보이지 않음. body를 만들어 보내는 방식. 한글 인코딩 깨짐. 가져오기 전에 인코딩 처리 후 전송해야 함
			  request.setCharacterEncoding("UTF-8")
--%>
	<form action="name_request.jsp" method="get">
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