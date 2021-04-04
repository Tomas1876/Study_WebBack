<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>greeting</title>
</head>
<body>
	<h3>View Page</h3>
	UI코드가 막 있겠지 여기엔
	<hr>
	데이터나 뿌립시다 허허
	<br>
	${requestScope.msg}<br>
	<%= request.getAttribute("msg") %>
</body>
</html>