<%@page import="book.dao.BookDAO"%>
<%@page import="book.dao.JdbcUtil"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// update.jsp에서 넘긴 값 가져오기
	int code = Integer.parseInt(request.getParameter("code"));
	int price = Integer.parseInt(request.getParameter("price"));

	// DB 작업
	Connection con = JdbcUtil.getConnection();
	BookDAO dao = new BookDAO(con);
	boolean result = dao.update(price, code);
	
	// 페이지 이동
	if(result) {
		response.sendRedirect("listPro.jsp");
		JdbcUtil.commit(con);
	}else {
		response.sendRedirect("update.jsp");
		JdbcUtil.rollback(con);
	}
	JdbcUtil.close(con);
%>