<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%	
    	String id = request.getParameter("userid");
    	request.setAttribute("name", "korea");
    	session.setAttribute("user", "bit");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 
EL(출력 표현식) : JSP 페이지에서 사용되는 스크립트 언어(JSP외에서는 사용할 수 없다)
			   화면에 출력하는 역할
			   스파게티 코드에 대한 문제점을 해결하기 위해 등장
			   (스파게티 코드는 유지 보수도 어렵고 가독성도 떨어진다)
			   
HTML과도 잘 어울리고 서버에 출력도 할 수 있는 언어 script
> EL & JSTL 둘 다 JSP페이지에서만 사용할 수 있는 한정적인 언어 

EL은 아파치 톰캣의 자원이기 때문에 별도의 참조 없이 사용할 수 있다

EL이 가지고 있는 객체
1. param
2. paramValues
3. requestScope
4. sessionScope
5. applicationScope

 -->

<h3>JSP</h3>
<b><%=id %></b><br>
<b><%=request.getAttribute("name") %></b><br>
<b><%=session.getAttribute("user") %></b><br>
<b><%=request.getParameter("userid") %></b><br>

<hr>
<h3>EL은 출력을 담당하는 표현식</h3>
기존 방식 : <%= 1+5 %><br>
EL 방식 : ${100+50}<br>
EL 방식 : ${"1"+1}<br> <!-- 문자형 숫자(자동 형변환) 숫자 + 숫자 -->
EL 방식 : ${1==1}<br>
EL 방식 : ${false}<br>
EL 방식 : ${!false }<br>
EL 방식 : ${empty x }<br> <!--  x라는 변수의 값이 비어있는가? 라는 식 -->


</body>
</html>