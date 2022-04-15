<%@page import="java.util.List"%>
<%@page import="item.dto.ItemDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/header.jsp"%>
<%
	List<ItemDTO> list = (List<ItemDTO>)request.getAttribute("list");
%>
	<h1>제품 목록 보기</h1>
	<table class="table">
	  <thead>
	    <tr>
	      <th scope="col">번호</th>
	      <th scope="col">카테고리</th>
	      <th scope="col">상품명</th>
	      <th scope="col">사이즈</th>
	      <th scope="col">가격</th>
	      <th scope="col">등록일</th>
	    </tr>
	  </thead>
	  <tbody>
		<%
			for(ItemDTO dto : list) {
		%>
		<tr>
			<td><%=dto.getNum() %></td>
			<td><%=dto.getCategory() %></td>
			<td><%=dto.getName() %></td>
			<td><%=dto.getPsize() %></td>
			<td><%=dto.getPrice() %></td>
			<td><%=dto.getRegisterAt() %></td>
		</tr>
		<%
			}
		%>
	  </tbody>
	</table>
<%@ include file="/layout/footer.jsp"%>