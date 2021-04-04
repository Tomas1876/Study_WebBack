<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servlet 요청하기</title>
</head>
<body>
	<h3>servlet 요청</h3>
	<h3>getContextPath</h3>
	<%= request.getContextPath() %><hr>
	
	<a href="<%= request.getContextPath() %>/simple">simple_요청하기(Get)</a>
	<br>
	<a href="<%= request.getContextPath() %>/simple?type=date">날짜_요청하기(Get)</a>
	<br>
	<a href="<%= request.getContextPath() %>/simple?type=abcd">비정상_요청하기(Get)</a>
	<hr>
	
	<h3>FrontServletController 요청</h3>
	<a href="<%= request.getContextPath() %>/action.do?cmd=greeting">요청하기(Get)</a>
	<br>
	
	<hr>
	<h3>FrontBoardController 요청</h3>
	<a href="<%= request.getContextPath() %>/board?cmd=boardlist">게시판 목록보기(Get)</a>
	<br>
	<a href="<%= request.getContextPath() %>/board?cmd=boardwrite">글 쓰기(Get)</a>
	<br>
	<a href="<%= request.getContextPath() %>/board">cmd == null 유도하기(Get)</a>
	<br>
	<a href="<%= request.getContextPath() %>/board?cmd=boarddelete">게시판 삭제하기(Get)</a>
	<br>
	<a href="<%= request.getContextPath() %>/board?cmd=login">로그인 보여주기(Get)</a>
	<br>
	<a href="${pageContext.request.contextPath}/board?cmd=login">EL로 로그인 보여주기(Get)</a>
	<br>
	EL 사용하기 : ${pageContext.request.contextPath}

	
</body>
</html>