<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tomcat Connection Pool 사용하기</title>
</head>
<body>
<%
	Connection conn= null;
	
	//JNDI
	Context context = new InitialContext();
	//현재 프로젝에서 특정 이름을 가진 녀석을 검색(이름 기반 검색)
	//InitialContext 클래스, javax.naming.Context인터페이스를 이용해 커넥션풀 생성
	//InitialContext 클래스는 Context 인터페이스를 상속받았으므로 오버라이딩해서 Context객체 생성
	
	DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle"); 
	//java:comp/env/  +  jdbc/oracle   이름  - 이건 정해진 약속이다
	// 커넥션 풀을 관리하는 javax.sql.DataSource 객체 생성
	
	//DB 관련 설정은 context.xml파일에 작성해둔다
	
	//POOL 안에서  connection 가지고 오기
	conn = ds.getConnection();
	
	out.print("db 연결여부 : " + conn.isClosed() + "<br>");
	
	//POINT 반드시 반환 해야한다
	conn.close(); //반환 (POOL)
	
	out.print("db 연결여부 : " + conn.isClosed() + "<br>");
%>
</body>
</html>