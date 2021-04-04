<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>default page</h3>
<!-- 192.168.0.5:8090/WebServlet_2/NowServlet 으로 요청하는 것 -->
<a href="${pageContext.request.contextPath}/NowServlet">NowServlet 요청하기(Get)</a><br>

<a href="${pageContext.request.contextPath}/Now.do">Now.do 요청하기(Get)</a><br>

<a href="${pageContext.request.contextPath}/Now.action">Now.action 요청하기(Get)</a><br>

<a href="${pageContext.request.contextPath}/Now.star">Now.star 요청하기(Get)</a><br>

</body>
</html>