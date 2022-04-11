<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 이름이 language인 cookie 객체 설정
	Cookie cookie = new Cookie("language", request.getParameter("language"));
	cookie.setMaxAge(60*60*24);
	response.addCookie(cookie);
	
	// 시작 페이지로 돌아가기
	response.sendRedirect("cookie3.jsp");
%>