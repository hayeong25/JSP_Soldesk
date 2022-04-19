package board.util;

import java.io.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadUtil {
	public HashMap<String, String> uploadFile(HttpServletRequest request) {
		// enctype = multipart/form-data로 넘어온 값들을 HashMap으로 담아 처리
		HashMap<String, String> dataMap = new HashMap<String, String>();
		
		// request에서 upload 요청이 있는지 우선 확인
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if(isMultipart) {
			// Create a factory for disk-based file items
			// 전송된 파일을 디스크에 저장하기 위한 객체
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			// Parse the request (파싱)
			List<FileItem> items = null;
			try {
				items = upload.parseRequest(request);
			} catch (FileUploadException e1) {
				e1.printStackTrace();
			}
			
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
			    	try {
						value = item.getString("UTF-8"); // 사용자의 입력값이 value로 들어옴
						dataMap.put(fieldName, value); // 필드명(컬럼), value
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
			    } else {
					fieldName = item.getFieldName();
					fileName = item.getName();
								    	
			    	// 서버에 파일 저장
			    	if((!fileName.isEmpty()) && (item.getSize() > 0)) {
			    		String path = "c:\\Users\\hayeo\\upload";
			    		
			    		UUID uuid = UUID.randomUUID(); // 동일한 파일명이 저장될 경우 덮어쓰기 안 되게 파일명에 (1) 추가
			    		
			    		File uploadFile = new File(path + "\\" + uuid.toString() + "_" + fileName);
			    		
			    		dataMap.put(fieldName, uploadFile.getName());
			    		
			    		try {
							item.write(uploadFile);
						} catch (Exception e) {
							e.printStackTrace();
						}		    		
			    	}
			    }
			}
		}
		
		return dataMap;
	}
}