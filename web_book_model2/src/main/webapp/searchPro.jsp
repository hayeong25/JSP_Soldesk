<%@page import="book.dto.BookDTO"%>
<%@page import="java.util.List"%>
<%@page import="book.dao.BookDAO"%>
<%@page import="book.dao.JdbcUtil"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	// search.jsp에서 넘긴 값 가져오기
	String criteria = request.getParameter("criteria");
	String keyword = request.getParameter("keyword");
	
	// DB 작업
	Connection con = JdbcUtil.getConnection();
	BookDAO dao = new BookDAO(con);
	List<BookDTO> list = dao.searchList(criteria, keyword);
	
	// 페이지 이동
	request.setAttribute("list", list);
	pageContext.forward("list.jsp");
	JdbcUtil.close(con);
%>