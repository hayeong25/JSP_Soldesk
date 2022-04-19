<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드</title>
</head>
<body>
	<%-- enctype = encoding type --%>
	<form action="upload.jsp" enctype="multipart/form-data" method="post">
		<div>
			<label> 제목
				<input type="text" name="title" id="title" />
			</label>
		</div>
		<div>
			<label> 내용
				<textarea name="contents" id="contents" cols="30" rows="10"></textarea>
			</label>
		</div>
		<div>
			<label>첨부파일
				<input type="file" name="file1" multiple="multiple" />
			</label>
		</div>
		<div>
			<input type="submit" value="전송" />
		</div>
	</form>
</body>
</html>