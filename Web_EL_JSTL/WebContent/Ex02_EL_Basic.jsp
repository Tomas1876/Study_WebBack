<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	//EL 스크립트 언어는 서버쪽 자원에 대한 접근이 가능하다 == <%=을 사용하지 않아도 되는 것
	//EL 사용한다고 해서 JAVA  객체 API 를 지원하지 않는다
	
	Date today = new Date();
	request.setAttribute("day", today);
	session.setAttribute("day2", today);

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>EL 화면 출력(객체를 통해서 서버 자원 접근 가능)</h3>
	EL : ${requestScope.day}<br>
	EL : ${day}<br>
	EL : ${sessionScope.day2}<br>
</body>
</html>

