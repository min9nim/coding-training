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
				, websquare.WebSquareConfig,java.io.*
				, java.lang.*"
%><%!

// location to store file uploaded
private static final String UPLOAD_DIRECTORY = "WebContent/techSpt/sd/attatch/up2";

// upload settings
private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

public boolean isEng(String str) {
	return java.util.regex.Pattern.matches("^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?~` ]+$", str);
	
	/*
	
	if(str.matches("[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝]*")){ 
	    System.out.println("특수문자있음");
	  }else{
	     System.out.println("특수문자없음");
	  }
	*/
	/*
    char chr;

    for (int i = 0; i < str.length(); i++) {
        chr = str.charAt(i); // 입력받은 텍스트에서 문자 하나하나 가져와서 체크
        if (Character.isLetterOrDigit(chr)){
        	// 영문자나 숫자면 OK
        }else{
           return false;   // 영문자도 아니고 숫자도 아님!
        }
    }
    return true;
    */
}


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
//String uploadPath = "/Users/songmingu/Documents/workspace_kepler_32/gw/WebContent/techSpt/sd/attatch/up2";
String baseDir = WebSquareConfig.getInstance().getStringValue("//upload/baseDir/@value", "");
String folderName = WebSquareConfig.getInstance().getStringValue("//upload/folderName/@value", "");
String uploadPath = baseDir + File.separator + folderName;
System.out.println("@@!!! uploadPath = " + uploadPath);



// creates the directory if it does not exist
File uploadDir = new File(uploadPath);
if (!uploadDir.exists()) {
	uploadDir.mkdir();
}


try {
	List<FileItem> formItems = upload.parseRequest(request);
	
	String savedFilename = "";
	String filename = "";
	String filesize = "";
	String deniedFile = "";
	String deniedCode = "";
	String callbackFunction = request.getParameter("callbackFunction");
	
	if (formItems != null && formItems.size() > 0) {
		
		for (FileItem item : formItems) {
			if (item.isFormField()) {
				String tmp = item.getString();
				if(tmp.indexOf("fakepath") > -1){
					// 단일 업로드에서 호출하는 경우
					tmp = tmp.substring(tmp.indexOf("fakepath")+9);	
				}
				String lu = new String(tmp.getBytes("8859_1"), "UTF-8");
				filename = lu;
				
				String fieldName = item.getFieldName();
				fieldName = new String(fieldName.getBytes("8859_1"), "UTF-8");
			}else{
				//String filename = new File(item.getName()).getName();
				// => 여기에서 파일명을 확인하면 한글이 깨짐
				
				System.out.println("@@ filename = " + filename);

				
				String ua = request.getHeader( "User-Agent" );
				System.out.println("User-Agent = " + ua);
				boolean isMacChrome = ( ua != null
										&& ua.indexOf( "Mac" ) > -1
										&& ua.indexOf("Chrome") > -1 
						);
				
				boolean isMacFirefox = ( ua != null
						&& ua.indexOf( "Mac" ) > -1
						&& ua.indexOf("Firefox") > -1 
						);				
								
				if(isEng(filename)){
					System.out.println("영어 파일명 : " + filename);
				}else{
					System.out.println("한글 파일명 : " + filename);
				}
				
				if((isMacChrome || isMacFirefox) && !isEng(filename)){
					// Mac 크롬에서 한글파일명 파일을 업로드할 경우 한글이 깨지는 문제가 있어 허용하지 않음
					deniedFile = filename;
					deniedCode = "105";
					break;
				}


				SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ("yyyyMMddHHmmssSSS", Locale.KOREA );
				String newdate = mSimpleDateFormat.format ( new Date() );
				savedFilename = filename + "_" + newdate;

				String filePath = uploadPath + File.separator + savedFilename;
				
				System.out.println("@@22 filePath = " + filePath);
				
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
	stb1.append("<storedFileList>"+savedFilename+"</storedFileList>");
	stb1.append("<localfileList>"+filename+"</localfileList>");
	stb1.append("<fileSizeList>"+filesize+"</fileSizeList>");
	stb1.append("<maxUploadSize></maxUploadSize>");
	stb1.append("<deniedList>" + deniedFile + "</deniedList>");
	stb1.append("<deniedCodeList>" + deniedCode + "</deniedCodeList>");
	stb1.append("</ret>\");}</script>");	
	
	out.println(stb1.toString());
	
} catch (Exception e) {
	//request.setAttribute("message", "There was an error: " + e.getMessage());
	StringBuilder stb1 = new StringBuilder();
	
	stb1.append("<script>window.onload = doInit;function doInit() {");
	stb1.append("parent.alert('파일업로드시 오류가 발생했습니다');}</script>");	
	
	out.println(stb1.toString());	
}
%>