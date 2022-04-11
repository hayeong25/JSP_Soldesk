<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 모든 session 제거
	session.invalidate();

	// 원래 페이지로 돌아가기
	response.sendRedirect("sessionTest1.jsp");
%>