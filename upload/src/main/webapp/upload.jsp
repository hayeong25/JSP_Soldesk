<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.UUID"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// request에서 upload 요청이 있는지 우선 확인
	boolean isMultipart = ServletFileUpload.isMultipartContent(request);

	if(isMultipart) {
		// Create a factory for disk-based file items
		// 전송된 파일을 디스크에 저장하기 위한 객체
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Parse the request (파싱)
		List<FileItem> items = upload.parseRequest(request);
		
		// form 데이터인지 file인지 구분
		// Process the uploaded items
		String fieldName = "";
		String fileName = "";
		String value = "";
		Iterator<FileItem> iter = items.iterator();
		while (iter.hasNext()) {
		    FileItem item = iter.next();
		
		    if (item.isFormField()) { // inputtype:text, password, email, ...
				fieldName = item.getFieldName();
		    	value = item.getString("UTF-8");
		    	out.print("<h3>일반 데이터</h3>");
		    	out.print(fieldName  + " : " + value + "<br>");
		    } else {
				fieldName = item.getFieldName();
				fileName = item.getName();
				long size = item.getSize();
				
				out.print("<h3>파일 데이터</h3>");
		    	out.print(fieldName  + " : " + fileName  + " - " + size + "<br>");
		    	
		    	// 서버에 파일 저장
		    	if(!fileName.isEmpty()) {
		    		String path = "c:\\Users\\hayeo\\upload";
		    		
		    		UUID uuid = UUID.randomUUID(); // 동일한 파일명이 저장될 경우 덮어쓰기 안 되게 파일명에 (1) 추가
		    		
		    		File uploadFile = new File(path + "\\" + uuid.toString() + "_" + fileName);
		    		
		    		item.write(uploadFile);
		    		
		    		// 파일명에 특수문자가 존재할 경우 파일명 깨지는 것 방지
		    		String encodeName = URLEncoder.encode(uploadFile.getName(), "UTF-8");
		    		
		    		out.print("<p><a href='download.jsp?fileName=" + encodeName + "'>" + fileName + "</a></p>");
		    	}
		    }
		}
	}
%>