<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>   

<%
	//CORS policy: No 'Access-Control-Allow-Origin'
	//서버가 내는 모든 요청을 허락하겠다는 뜻 
	response.addHeader("Access-Control-Allow-Origin", "*");
%>  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
		table {
	
		    font-family: arial, sans-serif;
		    border-collapse: collapse; /* 붕괴하다 , 무너지다 */
		    width: 100%;
		}
		
		th {
		    border: 1px solid #dddddd;
		    text-align: center;
		    padding: 8px;
		}
		td{
		    border: 1px solid #dddddd;
			text-align: left;
			padding: 8px;
		}
		tr:nth-child(even) {  /* even 짝수     odd 홀수 */
		    background-color: #dddddd;
		}
	</style>
	
</head>
<body>
<div align=center>
<hr color=green width=400>
<h2> Line Memo List </h2>

<hr color=green width=400>
<table> 
	<tr>
	 	<th>Writer</th> 
	 	<th>MemoContent</th>
	 	<th>Email</th>
	 </tr>
	<c:set var="memolist" value="${requestScope.memolist}"></c:set>
	<c:forEach var="memo"  items="${memolist}">
		<tr>
			<td>${memo.id}</td>
			<td>${memo.email}</td>
			<td>${memo.content}</td>
		</tr>
	</c:forEach>
	
</table>
</div>
<a href='memo.html'>글쓰기</a>

</body>
</html>



