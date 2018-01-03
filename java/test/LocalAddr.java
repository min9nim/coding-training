package test;

import java.net.*;

public class LocalAddr {
	public static String getLocalAddr () {

		InetAddress Address;
		try {
			Address = InetAddress.getLocalHost();
			System.out.println("로컬 컴퓨터의 이름 : "+Address.getHostName());
			System.out.println("로컬 컴퓨터의 IP 주소 : "+Address.getHostAddress());
			return Address.getHostAddress();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
			
	}

}
