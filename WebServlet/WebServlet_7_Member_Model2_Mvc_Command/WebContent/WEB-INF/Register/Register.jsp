<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!--<10> RegisterFrontController에서 forward로 넘어왔으므로 브라우저 주소창 변화 없이 이 jsp파일의 내용이 버퍼에 담겨 화면에 출력된다
	이 form은 submit버튼을 누르는 순간 Register.do?cmd=registerok라는 주소로 요청을 보낸다 -->
	<form action="<%=request.getContextPath() %>/Register.do?cmd=registerok" method="post">
	
		ID: <input type="text" name="id" placeholder="id"><br>
		PWD: <input type="password" name="pwd" placeholder="pwd"><br>
		EMAIL: <input type="text" name="email" placeholder="email"><br>
		
		<input type="submit" value="회원가입">
	
	</form>
</body>
</html>