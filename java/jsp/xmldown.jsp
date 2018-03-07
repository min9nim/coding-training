<%@page contentType="application/xml;charset=UTF-8"
	session="false"
    import="java.net.*,java.io.*,websquare.util.*"
%><%!

/* 컨셉
 1. w2xPath 로 넘어오는 경로의 파일이 로컬에 있으면 해당 파일을 내려주고
 2. 없으면 서버에서 내려받아서 파일로 저장한 다음 그 파일을 다시 읽어서 내려준다

*/

// context root 의 물리적 경로 
public static final String context = "/Users/songmingu/Documents/workspace_kepler_32/kepco/WebContent";

// 서버 도메인
public static final String domain = "https://partners.kepco.co.kr";


public String readFile(String w2xPath){
	String filepath = context + w2xPath;
	File file = new File(filepath);
	BufferedReader br = null;
	StringBuffer sb = new StringBuffer();
	try {
		br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line+"\n");
		}
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			if (br != null)
				br.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	return sb.toString();
}

public void writeFile(String w2xPath, String content) throws IOException {
	makeDir(w2xPath);
	String filepath = context + w2xPath;
	
	File file = new File(filepath);
    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF8"));
    
    writer.write(content);
    writer.close();
    
    System.out.println("파일쓰기 성공 : " + w2xPath);
}

public void makeDir(String w2xPath){
	String[] parts = w2xPath.split("/");
	String dir = context;
	for(int i=1; i<parts.length-1; i++){
		dir += File.separator + parts[i];
		File targetDir = new File(dir);
		if(!targetDir.exists()) {    //디렉토리 없으면 생성.
		  	targetDir.mkdirs();
		  	System.out.println("make dir : " + dir);
		}else{
			//System.out.println("exist dir : " + dir);
		}
	}
}

public String getRemoteFile(String w2xPath){
	String url = domain + w2xPath;
	String resMsg =  "";
	InputStream is   =  null;
	InputStreamReader isr =  null;
	BufferedReader br  =  null;
	StringBuffer sb = new StringBuffer();
	
	OutputStreamWriter wr = null;
	try{
	    URL httpsUrl  =  new URL(url);
	    URLConnection conn =  httpsUrl.openConnection();
	
	    conn.setUseCaches(false);
	    conn.setConnectTimeout(40000);
	    conn.setDoOutput(false);//get 방식 설정
	    conn.connect();
	    
	    resMsg = conn.getHeaderField(0);
	    System.out.println(resMsg); // HTTP/1.1 200 OK 형식의 http 헤더 결과 코드가 출력
	
	    is = conn.getInputStream();
	
	    isr = new InputStreamReader(is, "utf-8");
	    br = new BufferedReader(isr);
	
	    String line = null;
	    while((line=br.readLine()) != null){
	        sb.append(line+"\n");
	    }
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
		return sb.toString();
	}
}


%><%
String w2xPath = request.getParameter("w2xPath");

String res = null;
String filepath = context + w2xPath;

File file = new File(filepath);

if(file.isFile()){
	System.out.println("로컬에서 가져옴 : " + w2xPath);
	res = readFile(w2xPath);
}else{
	System.out.println("네트웍에서 가져옴 : " + w2xPath);
	res = getRemoteFile(w2xPath);
	writeFile(w2xPath, res);
}
out.println(res);

%>
