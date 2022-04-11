<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 특정 session 삭제
	session.removeAttribute("name");

	// 원래 페이지로 돌아가기
	response.sendRedirect("sessionTest1.jsp");
%>