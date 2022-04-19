<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// download
	
	// 사용자가 다운로드 요청한 파일명 가져오기
	String fileName = request.getParameter("fileName");

	// 서버 폴더에 가서 해당 파일 가져오기
	FileInputStream fis = new FileInputStream("c:\\Users\\hayeo\\upload\\" + fileName);
	BufferedInputStream bis = new BufferedInputStream(fis);
	
	// java.lang.IllegalStateException: 이 응답을 위해 getOutputStream()이 이미 호출되었습니다.
	// 현재 사용중인 스트림 비우기 + 현재 JSP 페이지에 대한 정보를 저장
	out.clear();
	out = pageContext.pushBody();
	
	// 브라우저에 파일 붙여서 보내기
	// octet-stream : 모든 종류의 이진 데이터
	response.setContentType("application/octet-stream"); // MIME
	
	// 파일명에 공백이 있으면 인코딩 시 + 로 변경되기 때문에 공백으로 처리(%20)
	fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
	
	// 875c72c1-4fe9-4ef8-af32-ba9ebf60afc_파일명인 
	String name = fileName.substring(fileName.indexOf("_") + 1);
	
	// Content-Disposition: inline => 웹 페이지 안에서 보여주기
	// Content-Disposition: attachment => 로컬에 다운로드 하거나 저장
	response.setHeader("Content-Disposition", "attachment;filename=" + name); // key, value 형태
	
	BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
	
	int numRead = 0;
	byte[] b = new byte[4096];
	while((numRead = bis.read(b, 0, b.length)) != -1) {
		bos.write(b, 0, numRead);
	}
	bos.flush();
	bos.close();
	bis.close();
%>