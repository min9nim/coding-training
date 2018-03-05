<%@ page import="javax.servlet.http.*, java.text.*, java.util.*, java.io.*, websquare.*, websquare.util.*" contentType="text/html; charset=UTF-8" %>
<%
    Locale locale = Locale.getDefault();
    String defaultEnc = new java.io.OutputStreamWriter(System.out).getEncoding();

    //WAS Encoding 현황
    String sEncString = "웹스퀘어(Websquare) 테스트";
    String sSetStr = HttpUtil.getParameter( request, "setStr" );
    String sTestStr = HttpUtil.getParameter( request, "testStr" );
    String setArr[] = null;
    if( sSetStr == null ) {
        sSetStr = "EUC-KR, UTF-8, KSC5601, ISO-8859-1";
    } else {
        if( sSetStr.length() > 1000 ) {
            sSetStr = "EUC-KR, UTF-8, KSC5601, ISO-8859-1";
        }
        setArr = sSetStr.split( "," );
    }

    if( sTestStr == null ) {
        sTestStr = "";
        //sTestStr = sEncString;
        
    } else {
        if( sTestStr.length() > 1000 ) {
            sTestStr = "";
        }
    }

%>
<html>
<head>
<title>WebSquare</title>
<style type="text/css">
    body { font-family:맑은 고딕,verdana,dotum; }
    .center {text-align: center;}
    .center table { margin-left: auto; margin-right: auto; text-align: left;}

    h1 {font-size:20px; font-weight:bold;}
    #license_table td {
        color:#950000;
    }
    #license_table td, #license_table th {
        padding:5px;
        font-size:12px;
        border-bottom: dotted 1px #333;
    }
    #license_table {
        border-collapse:collapse;
        background:#EFF4FB url(/websquare/engine/system/image/teaser.gif) repeat-x;
        border:1px solid #686868;
    }
    .header {
        background:#333 url(/websquare/engine/system/image/llsh.gif) repeat-x;
        color:#fff;
        font-weight:bold;
        font-size:14px;
    }
    #footer th {
        background:#333 url(/websquare/engine/system/image/llsh.gif) repeat-x;
        text-align:right;
        height:24px;
        padding-right:10px;
        color:#fff;
        font-size:10px;
    }

    input.Button { 
        text-align: center; 
        padding-top: 1pt; 
        padding-left: 0pt; 
        margin-left: 0px; 
        height: 14pt; 
        border-top: 2px solid #B8B7E6; 
        border-left: 2px solid #B8B7E6; 
        border-bottom: 2px solid #8584B3; 
        border-right: 2px solid #8584B3; 
        background-color: #CBC9F7; 
        color: #2E4500; margin-right:2px;
    }

</style>
</head>
<script type="text/javascript" language="javascript">
function goEncoding() {
    var form = document.myform;
    if( form.setStr.value == "" ) {
        alert( "문자셋을 입력 하세요\n구분자는 , 입니다." );
        form.setStr.focus();
        return;
    } else if( form.testStr.value == "" ) {
        alert( "테스트 문자열을 입력 하세요." );
        form.testStr.focus();
        return;
    } else if( form.setStr.value.length > 1000 ) {
        alert( "길이가 너무 큽니다." );
        form.setStr.focus();
        return;
    } else if( form.testStr.value.length > 1000 ) {
        alert( "길이가 너무 큽니다." );
        form.testStr.focus();
        return;
    }

    form.action = "encoding.jsp";
    form.method = "post";
    form.submit();
}
</script>

<body>
<div class="center">
    <table border="0" cellpadding="3" style="width:600px; height:50px;">
        <tr class="h">
            <td>
                <a href="http://www.websquare.co.kr/"><img border="0" src="/websquare/engine/system/image/logo.png" alt="Websquare" /></a>
            </td>
        </tr>
    </table>
	<table id="license_table" style="width:600px;">
        <tr class="header">
            <th height="30" colspan="2" >Character Set 입력</th>
        </tr>
        <form name="myform">
            <tr>
                <td colspan="2">
                    문자셋 : <input type="text" name="setStr" size="40" maxlength="100" value="<%= XMLUtil.XMLEncoder( sSetStr )%>" />
                    테스트문자열:<input type="text" name="testStr" size="15" maxlength="50" value="웹스퀘어(Websquare) 테스트" />
                    <input type="button" name="buttcharset" class="Button" value=" 확인 " onclick="javascript:goEncoding();" />
                </td>
            </tr>
        </form>

        <tr class="header">
            <th height="30" colspan="2" >VM Encoding 설정</th>
        </tr>
        <tr>
            <th width="30%" style="width:30%">file.encoding</th>
            <td width="70%" style="width:70%"><%= System.getProperty("file.encoding") %></td>
        </tr>
        <tr>
            <th>file.client.encoding</th>
            <td><%= System.getProperty("file.client.encoding") %></td>
        </tr>
        <tr>
            <th>client.encoding.override</th>
            <td><%= System.getProperty("client.encoding.override") %></td>
        </tr>

        <tr>
            <th>user.language</th>
            <td><%= System.getProperty("user.language") %></td>
        </tr>
        <tr>
            <th>user.region</th>
            <td><%= System.getProperty("user.region") %></td>
        </tr>
        <tr class="header" >
            <th colspan="2" height="30" >Java Locale 정보</th>
        </tr>
        <tr>
            <th>Locale</th>
            <td><%= locale %></td>
        </tr>
        <tr>
            <th>DisplayLanguage</th>
            <td><%= locale.getDisplayLanguage() %></td>
        </tr>
        <tr>
            <th>DisplayCountry</th>
            <td><%= locale.getDisplayCountry() %>, <%= locale.getDisplayLanguage(Locale.US) %>, <%= locale.getDisplayCountry(Locale.US) %></td>
        </tr>
        <tr>
            <th>default encoding</th>
            <td><%= defaultEnc %></td>
        </tr>

        <tr class="header" >
            <th colspan="2" height="30" >WAS Encoding 현황</th>
        </tr>
        <tr>
            <th>테스트 문자열</th>
            <td><%= XMLUtil.XMLEncoder( sEncString )%></td>
        </tr>
        <%
            String retString;
            if( setArr != null ) {
                for (int i = 0;i < setArr.length;i++) {
                    for (int j = 0;j < setArr.length;j++) {
                        try {
                            retString = new String(sEncString.getBytes(setArr[i].trim()), setArr[j].trim());
		%>
							<tr>
								<th>
									<%= XMLUtil.XMLEncoder( setArr[i] + " -> " + setArr[j] ) %>
								</th>
					            <td><%=  retString %></td>
							</tr>
        <%
                        } catch( Exception e ) {
		%>
							<tr>
					            <th>
					            	<%= XMLUtil.XMLEncoder( setArr[i] + " -> " + setArr[j] ) %>
					            </th>
								<td>지원 하지 않는 인코딩 입니다.</td>
							</tr>
		<%
                        }
                    }
                }
            }
        %>

        <tr class="header" >
            <th colspan="2" height="30" >Servlet/JSP(POST) Encoding 현황</th>
        </tr>
        <%
            if( setArr != null ) {
                for (int i = 0;i < setArr.length;i++) {
                    for (int j = 0;j < setArr.length;j++) {
                        try {
                            retString = new String(sTestStr.getBytes(setArr[i].trim()), setArr[j].trim());
		%>
							<tr>
								<th>
									<%= XMLUtil.XMLEncoder( setArr[i] + " -> " + setArr[j] ) %> 
								</th>
								<td><%= XMLUtil.XMLEncoder( retString ) %></td>
							</tr>
		<%
                        } catch( Exception e ) {
		%>
	 		<tr>
				<th>
					<%= XMLUtil.XMLEncoder( setArr[i] + " -> " + setArr[j] ) %>
				</th>
				<td colspan='2'>지원 하지 않는 인코딩 입니다.</td>
			</tr>
		<%
                        }
                    }
                }
            }
        %>

        <tr id="footer">
            <th colspan="2">
            <%
                java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date();
                String datestr = df.format(date);
                out.println(  XMLUtil.XMLEncoder( datestr ) );
            %>
            </th>
        </tr>
    </table>
</div>
</body>
</html>