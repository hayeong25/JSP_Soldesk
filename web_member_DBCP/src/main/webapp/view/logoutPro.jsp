<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 로그아웃 => session 해제
	session.removeAttribute("loginDTO");
	
	// loginForm.jsp로 페이지 이동
	response.sendRedirect("loginForm.jsp");
%>