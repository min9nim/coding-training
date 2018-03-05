<%@page session="false" import="java.net.*,java.io.*"
%><%
// http://snipplr.com/view/45552/proxyjsp/
try {
	//String reqUrl = request.getQueryString(); //OR:  request.getParameter("url");
	//String reqUrl = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=3114067000";
	//String reqUrl = "http://localhost:8080/w5test/server/temp.jsp";
	String reqUrl = "http://minq.tistory.com/645";

	URL url = new URL(reqUrl);
	HttpURLConnection con = (HttpURLConnection)url.openConnection();
	con.setDoOutput(true);
	con.setRequestMethod(request.getMethod());
	int clength = request.getContentLength();
	if(clength > 0) {
		con.setDoInput(true);
		byte[] idata = new byte[clength];
		request.getInputStream().read(idata, 0, clength);
		con.getOutputStream().write(idata, 0, clength);
	}
	response.setContentType(con.getContentType());

	BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
	String line;
	while ((line = rd.readLine()) != null) {
		out.println(line);
	}
	rd.close();

} catch(Exception e) {
	//System.out.println(e.getMessage());
	e.printStackTrace();
	throw e;
	//response.setStatus(500);
}
%>
