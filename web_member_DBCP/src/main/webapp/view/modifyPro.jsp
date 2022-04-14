<%@page import="member.dto.MemberDTO"%>
<%@page import="member.dao.MemberDAO"%>
<%@page import="member.dao.JdbcUtil"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// session에서 userid 가져오기
	MemberDTO dto = (MemberDTO)session.getAttribute("loginDTO");
	String userid = dto.getUserid();
	
	// modifyForm.jsp에서 넘긴 값 가져오기
	String current_password = request.getParameter("current_password");
	String new_password = request.getParameter("new_password");
	String confirm_password = request.getParameter("confirm_password");
	
	// DB 작업 - userid와 현재 비밀번호가 일치하면 비밀번호 변경
	Connection con = JdbcUtil.getConnection();
	MemberDAO dao = new MemberDAO(con);
	
	if(dao.isLogin(userid, current_password) == null) {
		out.print("<script>");
		out.print("alert('현재 비밀번호를 확인해주세요.');");
		out.print("history.back();");
		out.print("</script>");
	}else {
		// new_password와 confirm_password가 같은지 확인
		if(new_password.equals(confirm_password)) {
			boolean result = dao.changePassword(new_password, userid);
			
			// 페이지 이동 + session 해제
			if(result) {
				JdbcUtil.commit(con);
				session.invalidate();
				response.sendRedirect("loginForm.jsp");
			}else {
				JdbcUtil.rollback(con);
				response.sendRedirect("modifyForm.jsp");
			}
			JdbcUtil.close(con);
		}else {
			out.print("<script>");
			out.print("alert('비밀번호가 일치하지 않습니다.');");
			out.print("history.back();");
			out.print("</script>");
		}
	}
%>