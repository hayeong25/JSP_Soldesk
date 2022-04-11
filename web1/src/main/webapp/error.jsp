<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>에러가 발생했습니다. 페이지를 복구하고 있습니다...</p>
<%=exception.getMessage() %> <!-- isErrorPage="true" : 에러가 나면 이곳으로 와 exception.getMessage() 내장객체 호출되도록 설정 -->
</body>
</html>