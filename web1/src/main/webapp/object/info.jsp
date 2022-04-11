<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>개인정보</h3>
	<%--
		form 태그 안에서 선택되는 부분은 value가 가장 중요
		input과 같이 입력받는 태그는 입력값이 value가 되므로 value 지정할 필요 없음
	 --%>
	<form action="info_request.jsp" method="post">
		<div>
			<label>아이디</label>
			<input type="text" name="userid" id="userid" />
		</div>
		<div>
			<label>비밀번호</label>
			<input type="password" name="password" id="password" />
		</div>
		<div>
			<label>직업</label>
			<select name="job" id="job">
				<option value="무직">무직</option>
				<option value="학생">학생</option>
				<option value="직장인">직장인</option>
			</select>
		</div>
		<div>
			<label>관심분야</label>
			<input type="checkbox" name="favorite" value="정치" />정치
			<input type="checkbox" name="favorite" value="사회" />사회
			<input type="checkbox" name="favorite" value="컴퓨터" />컴퓨터
		</div>
		<div>
			<label>성별</label>
			<input type="radio" name="gender" value="남자"/>남자
			<input type="radio" name="gender" value="여자"/>여자
		</div>
		<div>
			<button type="submit">보내기</button>
		</div>
	</form>
</body>
</html>