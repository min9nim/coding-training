<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@ page import="java.io.File
				, java.io.IOException
				, java.io.PrintWriter
				, java.util.List
				, java.text.SimpleDateFormat
				, java.util.Date
				, java.util.Locale
				, javax.servlet.ServletException
				, javax.servlet.http.HttpServlet
				, javax.servlet.http.HttpServletRequest
				, javax.servlet.http.HttpServletResponse
				, org.apache.commons.fileupload.FileItem
				, org.apache.commons.fileupload.disk.DiskFileItemFactory
				, org.apache.commons.fileupload.servlet.ServletFileUpload
				, java.lang.*"
%><%!

// location to store file uploaded
private static final String UPLOAD_DIRECTORY = "upload";

// upload settings
private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB


%><%
response.setHeader("Access-Control-Allow-Origin", "*");
response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
response.setHeader("Access-Control-Allow-Headers", "content-type,submissionid");

request.setCharacterEncoding("EUC-KR");

System.out.println("upload.jsp start==============");



// configures upload settings
DiskFileItemFactory factory = new DiskFileItemFactory();
// sets memory threshold - beyond which files are stored in disk
factory.setSizeThreshold(MEMORY_THRESHOLD);
// sets temporary location to store files
factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

ServletFileUpload upload = new ServletFileUpload(factory);

// sets maximum size of upload file
upload.setFileSizeMax(MAX_FILE_SIZE);

// sets maximum size of request (include file + form data)
upload.setSizeMax(MAX_REQUEST_SIZE);

// constructs the directory path to store upload file
// this path is relative to application's directory
//String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
//System.out.println("getServletContext().getRealPath('') = " + getServletContext().getRealPath(""));
String uploadPath = "/Users/songmingu/Documents/workspace_kepler_32/gw/WebContent/techSpt/sd/attatch/up";


// creates the directory if it does not exist
File uploadDir = new File(uploadPath);
if (!uploadDir.exists()) {
	uploadDir.mkdir();
}



try {
	List<FileItem> formItems = upload.parseRequest(request);
	
	
	String savedfilename = "";
	String filename = "";
	String filesize = "";
	String callbackFunction = request.getParameter("callbackFunction");
	
	if (formItems != null && formItems.size() > 0) {
		
		for (FileItem item : formItems) {
			if (item.isFormField()) {
				String tmp = item.getString();
				tmp = tmp.substring(tmp.indexOf("fakepath")+9);
				String lu = new String(tmp.getBytes("8859_1"), "UTF-8");
				filename = lu;
				
				String fieldName = item.getFieldName();
				fieldName = new String(fieldName.getBytes("8859_1"), "UTF-8");
			}else{
				//String filename = new File(item.getName()).getName();
				String tmp = item.getName();
				//String uu = new String(tmp.getBytes("utf-8"), "utf-8");
				//String ee = new String(tmp.getBytes("euc-kr"), "euc-kr");
				//String ue = new String(tmp.getBytes("utf-8"), "euc-kr");
				//String eu = new String(tmp.getBytes("euc-kr"), "utf-8");
				//String lu = new String(tmp.getBytes("8859_1"), "UTF-8");
				//String le = new String(tmp.getBytes("8859_1"), "euc-kr");
				//System.out.println("## uu = " + uu);
				//System.out.println("## ee = " + ee);
				//System.out.println("## ue = " + ue);
				//System.out.println("## eu = " + eu);
				//System.out.println("## lu = " + lu);
				//System.out.println("## le = " + le);
				// => tmp 에서는 안깨지는 한글파일명을 가져오는게 안됨;;
				
				
				System.out.println("@@ filename = " + filename);

				SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ("yyyyMMddHHmmssSSS", Locale.KOREA );
				String newdate = mSimpleDateFormat.format ( new Date() );
				savedfilename = filename + "_" + newdate;

				String filePath = uploadPath + File.separator + savedfilename;
				
				File storeFile = new File(filePath);
				item.write(storeFile);
				filesize = String.valueOf(storeFile.length());
			}
		}
	}
	

	StringBuilder stb1 = new StringBuilder();
	
	stb1.append("<script>window.onload = doInit;function doInit() {");
	stb1.append("parent."+callbackFunction+"(\"<ret>");
	stb1.append("<key>"+uploadPath+"</key>");
	stb1.append("<storedFileList>"+savedfilename+"</storedFileList>");
	stb1.append("<localfileList>"+filename+"</localfileList>");
	stb1.append("<fileSizeList>"+filesize+"</fileSizeList>");
	stb1.append("<maxUploadSize></maxUploadSize>");
	stb1.append("<deniedList></deniedList>");
	stb1.append("<deniedCodeList></deniedCodeList>");
	stb1.append("</ret>\");}</script>");	
	
	out.println(stb1.toString());
	
} catch (Exception ex) {
	request.setAttribute("message", "There was an error: " + ex.getMessage());
}

%>