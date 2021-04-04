package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NowServlet
 */
@WebServlet(
		description = "서블릿은 클래스입니다", 
		urlPatterns = { 
				"/NowServlet", 
				"/Now.action", 
				"/Now.do", 
				"/Now.star"
		}, 
		initParams = { 
				@WebInitParam(name = "id", value = "bit", description = "id초기값설정"), 
				@WebInitParam(name = "jdbcDriver", value = "oracle.jdbc.OracleDriver", description = "오라클 드라이버 제공")
		})
public class NowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public NowServlet() {
        super();
        System.out.println("생성자 함구 객체 생성시 한 번 호출");
    }


	public void init(ServletConfig config) throws ServletException {
		//생성자와 비슷 init은 초기화 함수(자동호출함수)
		//호출시점 : NowServlet 클래스 파일에 대한 [최초요청시 한 번만 실행]
		//재실행은 개발자가 코드를 수정하거나 서버를 재시작했을 때
		
		//it.co.kr 서버를 오픈했다고 햇을 때 WAS(서블릿은 NowServlet.java)
		//홍길동이 첫 접속자  -> it.co.kr/NowServlet 서버요청
		//NowServlet 컴파일 > class 실행 > 생성자 호출 > init 자동 호출 > doGET or doPOST 자동호출
		
		// 김유신 접속 >  it.co.kr/NowServlet 서버요청 >  class 실행 >  doGET or doPOST 자동호출
		// 생성자도 init도 x
		
		//init은 클래스 내의 서로 다른 함수가 사용하는 공통자원을 메모리에 load하거나 초기화하는 일을 한다
		//Class.forName과 비슷하다고 이해해도 무방
		
		//db연결은 한 번만 하면 된다 init이 이 내용을 가지고 있다면!
		String drivername = config.getInitParameter("jdbcDriver");
		System.out.println("최초요청시 한 번만 실행  : 드라이버 로딩 : " + drivername);
		
		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGET() CALL");
		
		//servlet만 있던 시절...
		//servlet -> UI -> JSP(개발) UI작업
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<html>");
			out.print("<head><title>Hello</title></head>");
				out.print("<body>");
					out.print("현재 날짜 : " + new Date() + "<br>");
				out.print("</body>");
		out.print("</html>");
		//개고생!^^
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPOST() CALL");
	}

}
