<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%
	request.setCharacterEncoding("UTF-8");
%><%@ page import="java.io.*
				, java.util.*
				,java.lang.*
				"%><%
	String filePath = request.getParameter("filePath");
	String fileName = request.getParameter("fileName");
	String orgFilename = request.getParameter("orgFilename");

	/* --------------------------- log ----------------------------- */
	System.out.println(" filePath : " + filePath);
	System.out.println(" fileName : " + fileName);
	System.out.println(" orgFilename : " + orgFilename);

	/* ------------------------------------------------------------- */

	if (fileName == null)
		fileName = "";
	if (filePath == null)
		filePath = "";
	
	try {
		File file = new File(filePath + fileName);
		byte b[] = new byte[(int) file.length()];
		fileName = new String(fileName.getBytes("euc-kr"), "8859_1");
		response.setHeader("Content-Disposition", "attachment;filename=" + orgFilename);
		
		if (file.isFile()) {
			BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));
			BufferedOutputStream outs = new BufferedOutputStream(response.getOutputStream());
			int read = 0;
			while ((read = fin.read(b)) != -1) {
				outs.write(b, 0, read);
			}
			outs.close();
			fin.close();
		} else {
			System.out.println("File does not exist!");

		}
	} catch (IOException e) {
		System.out.println("Does not file !");
	}
%>