package com;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	web.xml 설정
	<url-pattern>/simple</url-pattern>
	localhost:8090/WebServlet/simple 요청이 오면
	public class Simplecontroller 자바 컴파일 실행
	
	서블릿은 자바로 만든 웹 서비스 파일이다
	서블릿의 조건!
	1. 자바파일이 반드시 extends HttpServlet 이어야 함
		> 상속하면 자바파일에서 웹 요청(request)객체, 응답(response)객체를 사용할 수 있게 된다
		  서블릿은 url에서 바로 요청이 불가능하다. 요청을 하면 mapping이 일어나고, 요청 주소를 생성한다 두 가지 방법이 있는데
		  1-1. xml에서 요청주소 생성하는 방법
		  1-2. annotation하는 방법(@WebServlet)
	
	
	서블릿은 이벤트 기반으로 움직인다
	== 특정 사건, 소위 말하는 이벤트가 발생하면 자동으로 호출되는 함수를 가지고 있다는 것(js의 onload 같은 것)
	바로
	1. protected void doGet() - localhost:8090/WebServlet/simple이 GET방식으로 요청했을 때 자동호출()
								> post 방식이 아닌 모든 방식은 get방식이다
		예를 들어 <form method="get"... 혹은 <a href="/somple?num=1000...이런 식으로 
	2. protected void doPost() - 요청방식이 POST일 경우 자동 호춞
		 <form method="post"... 이런 식일 때
		 
	위 두 함수의 동일한 역할은 데이터를 받는 것이다
	즉 request라는 객체와 response라는 객체를 사용하는 것!
	그래서 파라미터로 request와 reponse객체의 주소값을 받는다 그러면 함수 안에서 이 객체들을 사용할 수 있게 된다
	 
	
 */


//@WebServlet("/SimpleController") 충돌 나니까 없애거나 주석
public class SimpleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SimpleController() {
        super();
        System.out.println("Simplecontroller 생성자 함수 호출");

    }
    			// 클라이언트의 요청이 get방식일 때 실행되는 코드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		System.out.println("클라이언트 요청 : Get 방식");
		
		//jsp페이지에서 작업한 내용을 그대로 하면 됨~~
		
		//1. 한글처리(request객체 필)
		request.setCharacterEncoding("UTF-8");
		
		//2. 데이터 받기(클라이언트의 요청 의도를 파악해야 함)
		String type= request.getParameter("type");
		
		//3. 로직(요청에 따른 업무 수행 통칭 "service")
		Object resultobj = null;
		if(type==null || type.equals("greeting")) {
			resultobj = "hello world";
		} else if( type.equals("date")) {
			resultobj = new Date();
		} else {
			resultobj = "invalid String type";
		}
		
		//4. 요청 완료에 따른 결과 저장
		// MVC 패턴 방식(화면을 jsp로 만들 것 즉 servlet에서 만든 객체 정보를 jsp에 전달해야 한다)
		// 결과를 저장해서 만약 모든 페이지에서 사용할 거면 session 변수로
		// 특정 페이지에서 사용할거면(include나 forward가 걸려있는 페이지) request 변수
		// 물론 session.setAttribute를 써도 되지만 session의 scope은 모든 페이지기 때문에
		// 굳이 그렇게 더 넓은 scope을 가진 것을 사용할 필요는 없다
		request.setAttribute("result", resultobj);
		
		//5. 저장한 결과를 jsp에 전달해서 화면 UI 구성할 것
		//여기서는 forward 방식을 이용해서 jsp에게 전달할 것
		//forward의 장점은 클라이언트가 요청한 주소는 바뀌지 않고 그 버퍼에 forward 된 페이지의 정보를 담아서
		//서비스할 수 있다는 점이다
		
		//이건 view 페이지를 정의하는 함수
		RequestDispatcher dis = request.getRequestDispatcher("/simpleview.jsp");
		
		//.6 이제 위에서 정의한 페이지를 forward 시킨다
		// 즉 이 페이지의 버퍼를 /simpleview.jsp가 채우게 할 것이며
		// 이 서블릿이 가진 request와 response 객체의 주소를 /simpleview.jsp가 사용할 수 있게 하겠다는 뜻
		dis.forward(request, response);
		
		
		
	}

				// 클라이언트의 요청이 post방식일 때 실행되는 코드
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		System.out.println("클라이언트 요청 : Post 방식");
	}

}
