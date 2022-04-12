<%@page import="web2.dao.MemberDAO"%>
<%@page import="web2.dao.JdbcUtil"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// form에서 넘긴 값 가져오기(id, addr)
	request.setCharacterEncoding("UTF-8");
	int id = Integer.parseInt(request.getParameter("id"));
	String addr = request.getParameter("addr");
	
	// DB 작업
	Connection con = JdbcUtil.getConnection();
	MemberDAO dao = new MemberDAO(con);
	boolean result = dao.update(id, addr);
	
	// 페이지 이동
	if(result) {
		response.sendRedirect("list.jsp");
		JdbcUtil.commit(con);
	}else {
		response.sendRedirect("modifyProcess.jsp");
		JdbcUtil.rollback(con);
	}
	JdbcUtil.close(con);
%>