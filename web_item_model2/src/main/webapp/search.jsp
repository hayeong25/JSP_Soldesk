<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/header.jsp"%>
	<h1>상품 검색</h1>
	<form action="/search.do" method="post">
		<div class="form-row">
			<div class="form-group col-md-6">
				<select name="criteria" id="criteria" class="form-control">
					<option value="name">상품명</option>
					<option value="content">상품정보</option>
				</select>
			</div>
			<div class="form-group col-md-6">
				<input type="text" name="keyword" id="keyword" class="form-control"/>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-sm-10">
				<button type="submit" class="btn btn-primary">검색</button>
				<button type="reset" class="btn btn-danger">취소</button>
			</div>
		</div>
	</form>
<%@ include file="/layout/footer.jsp"%>