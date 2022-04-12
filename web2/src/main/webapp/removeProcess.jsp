<%@page import="web2.dao.MemberDAO"%>
<%@page import="web2.dao.JdbcUtil"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 사용자는 볼 수 없는 페이지 --%>
<%
	// form에서 보낸 값 가져오기
	int id = Integer.parseInt(request.getParameter("id")); // 문자를 숫자로 바꿀 수 없는 경우 NumberFormatException 발생
	// int id = 5; >> read.jsp의 dto로 값이 넘어갈 수 없기 때문에 NullPointerException 발생
	
	// DB 작업
	Connection con = JdbcUtil.getConnection();
	MemberDAO dao = new MemberDAO(con);
	boolean result = dao.delete(id);
	
	// 페이지 이동
	if(result) {
		JdbcUtil.commit(con);
		response.sendRedirect("list.jsp");
	}else {
		JdbcUtil.rollback(con);
		response.sendRedirect("readProcess.jsp?id=" + id);
	}
	JdbcUtil.close(con);
	
%>