<%@page import="member.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<h1>${loginDTO.userid} ${loginDTO.name}</h1> <%-- session의 속성값 loginDTO와 이름 같아야 함 --%>
<%
	// session에 담긴 로그인 정보 가져오기
	// MemberDTO loginDTO = (MemberDTO)session.getAttribute("loginDTO");
	
	// if(loginDTO == null) {
%>
<c:if test="${loginDTO == null}">
	<form class="form-signin" name="loginform" action="/login.do" method="post">
	  <div class="form-label-group">
	    <input type="text" id="userid" name="userid" class="form-control" placeholder="id" required autofocus>
	    <label for="userid">아이디</label>
	  </div>
	
	  <div class="form-label-group">
	    <input type="password" id="current_password" name="current_password" class="form-control" placeholder="Password" required>
	    <label for="pass">비밀번호</label>
	  </div>
	
	  <div class="checkbox mb-3">
	    <label>
	      <input type="checkbox" value="remember-me">Remember me
	    </label>
	  </div>
	  <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
	  <p class="mt-5 mb-3 text-muted text-center">&copy; 2019</p>
	</form>
</c:if>
<c:if test="${loginDTO != null}">
	<script>
		let name = '${loginDTO.name}';
	</script>
	<script src="/js/menu.js"></script>
</c:if>
<%-- <% } else{ %>
	let name = '<%=loginDTO.getName()%>';
<% } %> --%>
<%@ include file="../layout/footer.jsp" %>