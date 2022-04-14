<%@page import="member.dto.MemberDTO"%>
<%@page import="member.dao.MemberDAO"%>
<%@page import="member.dao.JdbcUtil"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// loginForm에서 넘긴 값 가져오기
	String userid = request.getParameter("userid");
	String password = request.getParameter("current_password");
	
	// DB 작업
	Connection con = JdbcUtil.getConnection();
	MemberDAO dao = new MemberDAO(con);
	MemberDTO loginDTO = dao.isLogin(userid, password);
	
	// 페이지 이동
	JdbcUtil.close(con);
	if(loginDTO != null) {
		session.setAttribute("loginDTO", loginDTO);
		response.sendRedirect("/index.jsp");
	}else {
		out.print("<script>");
		out.print("alert('아이디 또는 비밀번호를 확인해주세요.');");
		out.print("<script>");
		response.sendRedirect("loginForm.jsp");
	}
%>