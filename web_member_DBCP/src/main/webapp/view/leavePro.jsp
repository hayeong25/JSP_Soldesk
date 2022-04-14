<%@page import="member.dao.MemberDAO"%>
<%@page import="java.sql.Connection"%>
<%@page import="member.dao.JdbcUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// leaveForm에서 보낸 값 가져오기 (userid, password)
	String userid = request.getParameter("userid");
	String password = request.getParameter("current_password");
	
	// DB 작업
	Connection con = JdbcUtil.getConnection();
	MemberDAO dao = new MemberDAO(con);
	boolean result = dao.leave(userid, password);
	
	// 페이지 이동
	if(result) {
		JdbcUtil.commit(con);
		session.invalidate();
		response.sendRedirect("/index.jsp");
	}else {
		JdbcUtil.rollback(con);
		out.print("<script>");
		out.print("alert('비밀번호를 확인해주세요.');");
		out.print("history.back();");
		out.print("</script>");
	}
	JdbcUtil.close(con);
%>