<%@ page import="javax.servlet.http.*, java.text.*, java.util.*, java.io.*,websquare.*,websquare.system.license.manager.WebSquareLicense.*,websquare.system.license.manager.*,websquare.util.*" contentType="text/html; charset=UTF-8" %>
<%
	final class LicenseInfo extends SimpleSymmetricWebSquareLicense {
		private String customerName;
		private String hostName;
		private String ip;
		private int productID;
		private int cpuNumber;
		public LicenseInfo(String encLicenseString) throws InvalidLicenseException {
			super(encLicenseString);
			try{
				getLicenseInfo(encLicenseString);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		private final void getLicenseInfo(String licenseString) throws InvalidLicenseException {
			try {
				String clearLicense = decipherLicense(licenseString);
				StringTokenizer tok = new StringTokenizer(clearLicense, ":");

				tok.nextToken();
				tok.nextToken();
				customerName = tok.nextToken();
				tok.nextToken();
				hostName = tok.nextToken();
				ip = tok.nextToken();
				productID = Integer.parseInt(tok.nextToken());
				tok.nextToken();
				cpuNumber = Integer.parseInt(tok.nextToken());
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

		public String getHostName() {
			return hostName;
		}

		public String getIP() {
			return ip;
		}

		public int getProductID() {
			return productID;
		}

		public int getCpuNumber() {
			return cpuNumber;
		}

		public String getCustomerName() {
			return customerName;
		}
	}

    String className = HttpUtil.getParameter( request, "className" );
    if( className == null ) {
        className = "";
    }

	LicenseInfo license = null;
	boolean isValid = false;
	String installArea = null;
	String path = null;

	try {
		WebSquareConfig conf = WebSquareConfig.getInstance();
		String allowedIP_str = conf.getStringValue( "//licenseInfoView/allowedIP/@value", "127.0.0.1" );
		String allowedIPs[] = allowedIP_str.split(" ");
		String ip = request.getRemoteAddr();
		int idx;
		for(idx = 0; idx < allowedIPs.length; idx++) {
			String allowedIP = allowedIPs[idx];
			int asterisk = allowedIP.indexOf("*");
			if(asterisk >= 0) {
				allowedIP = allowedIP.substring(0, asterisk);
				if(ip.startsWith(allowedIP))
					break;
			}
			int dash = allowedIP.indexOf("-");
			if(dash > 0) {
				int dot = allowedIP.lastIndexOf(".");
				int from = Integer.parseInt(allowedIP.substring(dot + 1, dash));
				int to = Integer.parseInt(allowedIP.substring(dash + 1, allowedIP.length()));
				allowedIP = allowedIP.substring(0, dot + 1);
				int lastNum = Integer.parseInt(ip.substring(ip.lastIndexOf(".") + 1, ip.length()));
				allowedIP_str = "'" + lastNum + "'";
				if(ip.startsWith(allowedIP) && from <= lastNum && lastNum <= to)
					break;
			}
			if(ip.equals(allowedIP))
				break;
		}

		if (System.getProperty("WEBSQUARE_HOME") == null
				&& System.getProperty("osgi.install.area") != null) {
			System.setProperty(
				"WEBSQUARE_HOME", System.getProperty("osgi.install.area"));
		}

		installArea = System.getProperty("WEBSQUARE_HOME");
		path = installArea + File.separator	+ "license" + File.separator + "license";

		BufferedReader br = null;
		String strLicense = null;
		try {
			br = new BufferedReader(new FileReader(path));

			char[] buffer = new char[256];
			while (true) {
				int readCount = br.read(buffer);
				if (readCount == -1) {
					break;
				}
				strLicense = new String(buffer, 0, readCount);
				break;
			}
		} finally {
			try {
				br.close();
			} catch (Exception ignored) {}
		}

		license = new LicenseInfo(strLicense);

		isValid = license.isValid();
	} catch(Exception e) {
		isValid = false;
	}

    //class check
    String loadclassName = "";
    Class c = null;
	ClassLoader cl = Thread.currentThread().getContextClassLoader();

	try {
		String fileName = "/" + className.replace('.', '/') + ".class";

		if (cl == null) {
			c = Class.forName(className);
		} else {
			try {
				c = cl.loadClass(className);
			} catch (ClassNotFoundException e) {
				c = Class.forName(className);
			}
		}

		java.net.URL classUrl = c.getResource( fileName );
		loadclassName = classUrl.toString();
	} catch ( Exception e ) {
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
function goCheck() {
    var form = document.myform;
    if( form.className.value == "" ) {
        alert( "class 팩키지 이름을 입력 하세요.\nex) websquare.util.DateUtil" );
        form.className.focus();
        return;
    } else if( form.className.value.length > 1000 ) {
        alert( "길이가 너무 큽니다." );
        form.className.focus();
        return;
    }

    form.action = "classcheck.jsp";
    form.method = "post";
    form.submit();
}
</script>

<body>
	<div class="center">
		<table border="0" cellpadding="3" style="width:600px; height:50px;">
			<tr class="h">
				<td><a href="http://www.websquare.co.kr/"><img border="0" src="/websquare/engine/system/image/logo.png" alt="Websquare" /></a></td>
			</tr>
		</table>
		<table id="license_table" style="width:600px;">
            <tr class="header">
                <th height="30" colspan="2" >class 조회</th>
            </tr>
            <form name="myform">
                <tr>
                    <td colspan="2">
                        class명 : <input type="text" name="className" size="70" maxlength="100" value="<%= XMLUtil.XMLEncoder( className ) %>" />
                        <input type="button" name="buttclassname" class="Button" value=" 확인 " onclick="javascript:goCheck();" />
                    </td>
                </tr>
            </form>

			<%if(isValid) {%>
				<tr class="header"><th colspan="2" >class 정보</th></tr>
				<tr>
					<th width="30%" style="width:30%">class : </th>
					<td width="70%" style="width:70%"><%= loadclassName.length() > 0 ? XMLUtil.XMLEncoder( loadclassName ) : XMLUtil.XMLEncoder(loadclassName + "로드된 class 정보가 존재 하지 않습니다.")  %></td>
				</tr>
			<%}%>
			<tr style="font-weight:bold;">
				<td colspan="2">
					<%=isValid ? "유효한 웹스퀘어 라이센스 키를 사용하고 있습니다." : "웹스퀘어 라이센스가 유효하지 않습니다."%>
				</td>
			</tr>
			<tr id="footer">
				<th colspan="2"><%
					Calendar presentDate = Calendar.getInstance();
					out.println(presentDate.get(Calendar.YEAR) + "/" + (presentDate.get(Calendar.MONTH) + 1) + "/" + presentDate.get(Calendar.DAY_OF_MONTH));
				%></th>
			</tr>
		</table>
	</div>
</body>
</html>