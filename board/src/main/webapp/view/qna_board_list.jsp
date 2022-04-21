<%@page import="board.dto.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>
<section class="content">
	<div class="box box-primary">
		<div class="box-header">
			<h3 class="box-title">List Board</h3>
		</div>
		<div class="row">
		
			<!-- 글쓰기 버튼 -->
			<div class="col-md-4">
				<button type="button" class="btn btn-success">글쓰기</button>
			</div>
			
			<!-- 검색 -->
			<div class="col-md-4 offset-md-4">
				<form action="/qList.do" method="get" id="search">
					<input type="hidden" name="page" value="${pageDTO.searchDTO.page }" />
					<input type="hidden" name="amount" value="${pageDTO.searchDTO.amount }" />
					<select name="criteria">
						<option value="n" <c:out value="${pageDTO.searchDTO.criteria == null ? 'selected' : ''}"></c:out>>------</option>
						<option value="title" <c:out value="${pageDTO.searchDTO.criteria == 'title' ? 'selected' : ''}"></c:out>>title</option>
						<option value="content" <c:out value="${pageDTO.searchDTO.criteria == 'content' ? 'selected' : ''}"></c:out>>content</option>
						<option value="name" <c:out value="${pageDTO.searchDTO.criteria == 'name' ? 'selected' : ''}"></c:out>>name</option>
					</select>
					<input type="text" name="keyword" value="${pageDTO.searchDTO.keyword}"/>
					<input type="button" value="검색" class="btn btn-primary"/>
				</form>
			</div>
		</div>
		<br>
		
		<!-- 게시물 목록 -->
		<table class="table table-bordered">
			<tr>
				<th class='text-center' style='width:100px'>번호</th>
				<th class='text-center'>제목</th>
				<th class='text-center'>작성자</th>
				<th class='text-center'>날짜</th>
				<th class='text-center' style='width:100px'>조회수</th>
			</tr>
			<c:forEach var="dto" items='${list}'>
			<tr>
				<td class='text-center'>${dto.bno}</td>
				<td>
					<c:if test="${dto.reLev != 0}">
						<c:forEach begin="0" end="${dto.reLev*1}">
							&nbsp;
						</c:forEach>
					</c:if>
					<a href="${dto.bno}" class="move">${dto.title}</a>
				</td>
				<td class='text-center'>${dto.name}</td>
				<td class='text-center'>${dto.regDate}</td>
				<td class='text-center'><span class="badge badge-pill badge-primary">${dto.readCount}</span></td>
			</tr>
			</c:forEach>
		</table>
		
		<!--하단의 페이지 나누기 부분-->
		<div class="container">
			<div class="row  justify-content-md-center">
				<nav aria-label="Page navigation example">
				  <ul class="pagination">
				  	<c:if test="${pageDTO.prev}">
						<li class="page-item"><a href="${pageDTO.searchDTO.page-10}" class="page-link">이전</a></li>
					</c:if>
					<c:forEach begin="${pageDTO.startPage}" end="${pageDTO.endPage}" var="index">
						<li class="page-item ${pageDTO.searchDTO.page == index ? 'active' : ''}">
							<a href="${index}" class="page-link">${index}</a>
						</li>
					</c:forEach>
					<c:if test="${pageDTO.next}">
						<li class="page-item"><a href="${pageDTO.searchDTO.page+10}" class="page-link">다음</a></li>
					</c:if>
				  </ul>
				</nav>					
			</div>
		</div>
		
		<div style="height:20px"></div>
	</div>	
</section>

<%-- 페이지 나누기 정보 form : 사용자가 요청한 페이지 번호, 한 페이지에 보여줄 게시물 개수 --%>
<form action="" id="actionForm">
	<input type="hidden" name="page" value="${pageDTO.searchDTO.page }" />
	<input type="hidden" name="amount" value="${pageDTO.searchDTO.amount }" />
	<input type="hidden" name="criteria" value="${pageDTO.searchDTO.criteria }" />
	<input type="hidden" name="keyword" value="${pageDTO.searchDTO.keyword }" />
</form>

<script>
	let totalPage = ${pageDTO.totalPage};
</script>

<script src="/js/list.js"></script>
<%@include file="../include/footer.jsp"%>
