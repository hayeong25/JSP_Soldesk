<%@page import="member.dto.MemberDTO"%>
<%@page import="member.dao.MemberDAO"%>
<%@page import="member.dao.JdbcUtil"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	// joinForm에서 넘어온 값 가져오기
	String userid = request.getParameter("userid");
	String password = request.getParameter("password");
	String confirm_password = request.getParameter("confirm_password");
	String name = request.getParameter("name");
	String gender = request.getParameter("gender");
	String email = request.getParameter("email");
	
	// DB 작업
	Connection con = JdbcUtil.getConnection();
	MemberDAO dao = new MemberDAO(con);
	
	if(!password.equals(confirm_password)) {
		out.print("<script>");
		out.print("alert('비밀번호가 일치하지 않습니다.');");
		out.print("history.back();");
		out.print("</script>");
	}else {
		MemberDTO registerDTO = new MemberDTO(userid, password, name, gender, email);
		
		boolean result = dao.register(registerDTO);
		
		// 페이지 이동
		if(result) {
			JdbcUtil.commit(con);
			out.print("<script>");
			out.print("alert('회원가입 성공');");
			out.print("location.href='loginForm.jsp'");
			out.print("</script>");
		}else {
			JdbcUtil.rollback(con);
			out.print("<script>");
			out.print("alert('다시 확인해주세요.');");
			out.print("location.href='loginForm.jsp'");
			out.print("</script>");
		}	
		JdbcUtil.close(con);
	}
%>