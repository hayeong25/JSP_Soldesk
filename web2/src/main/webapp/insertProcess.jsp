<%@page import="web2.dto.MemberDTO"%>
<%@page import="web2.dao.MemberDAO"%>
<%@page import="web2.dao.JdbcUtil"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 사용자에게는 보이지 않는 페이지 (개발자만 볼 수 있는 페이지) --%>
<%
	// form이 post 방식이기 때문에 한글 인코딩 깨지므로 사전 처리
	request.setCharacterEncoding("UTF-8");

	// form에서 입력한 값 가져오기
	String name = request.getParameter("name");
	String addr = request.getParameter("addr");
	String email = request.getParameter("email");
	int age = Integer.parseInt(request.getParameter("age"));
	
	MemberDTO insertDTO = new MemberDTO(name, addr, email, age);
	
	// DB 작업
	Connection con = JdbcUtil.getConnection();
	MemberDAO dao = new MemberDAO(con);
	
	String path = "";
	if(dao.insert(insertDTO)) {
		// 페이지 이동 => 전체 리스트 보여주기
		path = "list.jsp";
		// commit
		JdbcUtil.commit(con);
	}else {
		// 페이지 이동 => 실패가 발생한 페이지 보여주기
		path = "insert.jsp";
		// rollback
		JdbcUtil.rollback(con);
	}
	response.sendRedirect(path);
%>