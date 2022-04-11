<%@page import="web2.dto.MemberDTO"%>
<%@page import="web2.dao.MemberDAO"%>
<%@page import="web2.dao.JdbcUtil"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 모든 JSP는 각각의 request와 response를 가지고 있다 --%>
<%
	// list.jsp에서 넘긴 id 값 가져오기
	int id = Integer.parseInt(request.getParameter("id"));
	
	// DB 작업
	Connection con = JdbcUtil.getConnection();
	MemberDAO dao = new MemberDAO(con);
	MemberDTO dto = dao.getRow(id);
	
	// request 영역에 결과값 담기
	request.setAttribute("dto", dto);
	
	// 페이지 이동
	// sendRedirect() : scope(request, session)에 값을 담지 않았거나, session에 값을 담는 경우
	// forward() : request.setAttribute() 했을 경우 무조건 forward() 방식
	pageContext.forward("read.jsp");
%>