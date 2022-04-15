<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/header.jsp"%>
	<h1>상품 삭제</h1>
	<p>삭제할 상품의 이름을 입력하세요</p>
	<form action="/delete.do" method="post">
		<div class="form-group row">
			<label for="name" class="col-sm-2 col-form-label">상품명</label>
			<div class="col-sm-8">
				<input type="text" name="name" id="name" class="form-control"/>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-sm-10">
				<button type="submit" class="btn btn-primary">삭제</button>
				<button type="reset" class="btn btn-danger">취소</button>
			</div>
		</div>
	</form>
<%@ include file="/layout/footer.jsp"%>