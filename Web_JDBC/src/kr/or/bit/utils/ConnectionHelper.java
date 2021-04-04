package kr.or.bit.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/*

	*리팩토링 : 반복적인 코드 제거 작업*
	
	자주 사용하는 함수는 static으로 만들어서 overloading 한다(다형성)
	

 */
public class ConnectionHelper {
	
	public static Connection getConnection(String dsn) { //어떤 DB 를 연결해도 사용할 수 있었으면!
		
		Connection conn = null;
		try {
			if(dsn.equals("oracle")) {
				Class.forName("oracle.jdbc.OracleDriver");  //jdk1.6 이상 자동 로딩 ...참조
				conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.5:1521:xe","bituser","1004");
			} else if( dsn.equals("mysql")) {
				 Class.forName("com.mysql.cj.jdbc.Driver");
				 conn = DriverManager.getConnection("jdbc:mysql://192.168.0.218:3306/sampledb?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=true","bituser","1004");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return conn;
	}
	
	public static Connection getConnection(String dsn, String id, String pwd) { //어떤 DB 를 연결해도 사용할 수 있었으면!
		
		Connection conn = null;
		try {
			if(dsn.equals("oracle")) {
				Class.forName("oracle.jdbc.OracleDriver");  //jdk1.6 이상 자동 로딩 ...참조
				conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.5:1521:xe",id,pwd);
			} else if( dsn.equals("mysql")) {
				 Class.forName("com.mysql.cj.jdbc.Driver");
				 conn = DriverManager.getConnection("jdbc:mysql://192.168.0.218:3306/sampledb?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=true","bituser","1004");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return conn;
	}

}
