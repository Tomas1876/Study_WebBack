<%@page import="kr.or.bit.utils.SingletonHelper"%>
<%@page import="kr.or.bit.utils.ConnectionHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Scanner"%>
<%@page import="java.sql.Connection"%>
<%
Connection conn = null;
conn = ConnectionHelper.getConnection("oracle");
System.out.println(conn); // 객체 정보 출력
//conn.close();

conn = ConnectionHelper.getConnection("oracle", "hr", "1004");
System.out.println(conn);

// 다섯 개의 페이지가 DB에 연결되면 매번 새로운 객체가 된다
// 하나의 객체를 만들어 사용하면 되지 않나? > 공유 > singleton!! 학습용으로 특히 좋다
// 단!!! 싱글톤으로 쓸 때는 close했다간 전부 꺼져서 하면 안된다

Connection conn2 =null;
conn2 = SingletonHelper.getConnection("oracle");

Connection conn3 =null;
conn3 = SingletonHelper.getConnection("oracle");

System.out.println(conn2 == conn3);
System.out.println(conn2);
System.out.println(conn3);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	연결여부 : <%=conn.isClosed() %><br>
	재정의 : <%=conn.toString() %><br>
	productName : <%=conn.getMetaData().getDatabaseProductName() %><br>
	productVersion : <%=conn.getMetaData().getDatabaseProductVersion() %><br>
	
	
</body>
</html>