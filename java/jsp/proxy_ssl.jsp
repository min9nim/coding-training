<%@page contentType="application/json;charset=UTF-8"
	session="false"
    import="java.net.*,java.io.*,websquare.util.*"
%><%


String url    =  "https://partners.kepco.co.kr/dis/serviceEndpoint/pfwjson";

String responseMessage =  "";

InputStream is   =  null;
InputStreamReader isr =  null;
BufferedReader br  =  null;

StringBuffer sb = new StringBuffer();

ServletInputStream in = request.getInputStream();
java.io.BufferedInputStream bin = new java.io.BufferedInputStream( in );
String args = StreamUtil.getString( bin, "utf-8" );


StringBuffer sqlStr = new StringBuffer();
sqlStr.append(args);

//이 부분이 https부분입니다.***************************
//System.setProperty ( "java.protocol.handler.pkgs","com.sun.net.ssl.internal.www.protocol");
//com.sun.net.ssl.internal.ssl.Provider provider = new com.sun.net.ssl.internal.ssl.Provider();
//java.security.Security.addProvider(provider);
//*****************************************************

OutputStreamWriter wr = null;

try{
    URL httpsUrl  =  new URL(url);
    URLConnection conn =  httpsUrl.openConnection();


    conn.setUseCaches(false);
    conn.setConnectTimeout(40000);
    conn.setDoOutput(true);//post 방식 설정
    //conn.connect();
    
	wr = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
	wr.write(sqlStr.toString());
	wr.flush();    
    
    
    responseMessage = conn.getHeaderField(0);
    System.out.println(responseMessage); // HTTP/1.1 200 OK 형식의 http 헤더 결과 코드가 출력됩니다.

    is = conn.getInputStream();

    isr = new InputStreamReader(is, "utf-8");
    br = new BufferedReader(isr);

    String line = null;
    while((line=br.readLine()) != null){
        sb.append(line);
    }
    
    System.out.println(sb.toString());
    out.println(sb.toString());
    


}catch(Exception e){
    e.printStackTrace();
}


%>
