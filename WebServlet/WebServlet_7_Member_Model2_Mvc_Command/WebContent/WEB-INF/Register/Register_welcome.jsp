<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>가입을 환영합니다</h3>
	
	<!-- <24> RegisterFrontController에서 forward한 resultdata값을 출력한다
	resultdata를 request의 객체에 담을 때 data라는 속성명을 사용했으므로 아래와 같이 작성 -->
	<%=request.getAttribute("data") %>
	
	<!-- <25> 끝 -->
</body>
</html>