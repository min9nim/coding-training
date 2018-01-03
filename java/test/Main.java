package test;

import java.net.*;


public class Main {

	public static void main(String[] args) {
		
		//LocalAddr.getLocalAddr();
		
		InetAddress addr;
		try {
			addr = Util.getLocalAddress();
			String local_ip = addr.getHostAddress();
			System.out.println("not loopback addr : " + local_ip);
			
			
			String contextPath = "";

			if(contextPath.equals("")){

				contextPath = "http://" + local_ip + ":8080";

			}
			
			System.out.println(contextPath);
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}