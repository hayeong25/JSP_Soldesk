<%@page import="book.dto.BookDTO"%>
<%@page import="book.dao.JdbcUtil"%>
<%@page import="book.dao.BookDAO"%>
<%@ page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 인코딩
	request.setCharacterEncoding("UTF-8");

	// insert.jsp에서 넘어오는 값 가져오기 (BookDTO에 담기)
	BookDTO dto = new BookDTO();
	dto.setCode(Integer.parseInt(request.getParameter("code")));
	dto.setTitle(request.getParameter("title"));
	dto.setWriter(request.getParameter("writer"));
	dto.setPrice(Integer.parseInt(request.getParameter("price")));
	
	// DB 작업
	Connection con = JdbcUtil.getConnection();
	BookDAO dao = new BookDAO(con);
	boolean result = dao.insert(dto);
	
	// 페이지 이동
	if(result) {
		response.sendRedirect("listPro.jsp");
		JdbcUtil.commit(con);
	}else {
		response.sendRedirect("insert.jsp");
		JdbcUtil.rollback(con);
	}
	JdbcUtil.close(con);
%>