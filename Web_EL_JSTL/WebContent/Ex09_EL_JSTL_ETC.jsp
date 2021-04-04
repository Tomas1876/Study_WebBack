<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>JSTL out 출력 객체(EL을 더 많이 사용)</h3>
	<c:out value="<p>문단태그입니다</p>" />
	<hr>
	&lt;p&gt; 이 태그는 문단태그입니당
	
	<p></p> 태그는....설명
	
	<hr>
	
	<h3>JSTL 예외처리</h3>
	<!-- 
		c:catch 블럭 안에서 예외가 발생하면 예외객체가 생성되고 
		그 예외 객체에 대한 정보를 msg라는 변수가 받는다
	 -->
	<c:catch var="msg">
	name: <%= request.getParameter("name") %>
	<%
		if(request.getParameter("name").equals("hong")){
			out.print("당신의 이름은 : " + request.getParameter("name"));
		}
	%>
	</c:catch>
	<c:if test="${msg != null}">
		<h3>예외발생</h3>
		오류메시지 ${msg}
	</c:if>
</body>
</html>