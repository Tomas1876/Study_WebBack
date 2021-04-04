package com.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/FrontBoardController")
public class FrontBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FrontBoardController() {
        super();
    }
    
    private void doProcess(HttpServletRequest request, HttpServletResponse response, String method) throws ServletException, IOException{
    	// GET, POST 두 가지 방식에 대해서 동작하는 함수를 만들 것
    	// method를 출력하면 요청이 GET인지 POST인지를 확인할 수 있다
    	
    	System.out.println("클라이언트가 요청한 방식 : " + method);
    	
    	//1.한글처리
    	//2.데이터요청반기(request)
    	//3.요청판단(판단기준 : parameter - command 방식)
    	//3.1 192.168.0.5:8090/WebServlet_1/board?cmd=login&id=kglim&pwd=1004 요청 서버로
    	//서버는 cmd라는 변수가 가지는 값으로 판단한다 login처리하려고 하나?
    	//3.2 192.168.0.5:8090/WebServlet_1/board?cmd=list 
    	//게시판 보여달라는 요청이구나!(판단 로직은 미리 정해둔다)
    	
    	//String command = request.getParameter("cmd");
    	//if(command.equals("login")){로그인서비스처리}
    	//else if(command.equals("list")){게시판 목록 조회}
    	
    	//command방식과 쌍벽을 이루는 방식이 있다 바로 url방식(선호도가 더 높은 방식)
    	//아래의 주소에서 마지막 주소값을 추출해서 이를 보고 판단하는 것
    	
    	//4. 판단하고 처리했으면 그 결과 저장하기(request.setAttribtue, session.setAttribtue, application.setAttribtue
    	//								필요한 scope에 따라!)
    	
    	//5. view 지정
    	// view page : *.jsp
    	//WebContent > board > boardlist.jsp
    	//WebContent > error > error404.jsp
    	//그런데 WebContent 폴더는 보안상 좋지 않다
    	//실 개발에서는 WebContent 하위에 WEB-INF에 view폴더를 생성해서 board나 main이나 adamin을 관리한다
    	
    	//6.view에게 request 객체를 forward해서 사용가능
    	
    	
    	
    	//1.
    	request.setCharacterEncoding("UTF-8");
    	
    	String cmd = request.getParameter("cmd");
    	
    	//2.
    	String viewpage= null;
    	
    	//cmd == null > error
    	//cmd == boardlist > list.jsp
    	//cmd == boardwrite > write.jsp
    	// 이렇게 로직을 정해두고 아래에 조건문을 작성한다
    	if(cmd == null) {
    		viewpage = "/error/error.jsp";
    	} else if(cmd.equals("boradlist")) {
    		
    		//실제 업무처리(서비스)
    		/*
    			DB연결 - select - 객체 담고 - 저장
    			boardDao dao = new boardDao();
    			List<board> boardlist = dao.selectBoardList();
    			request.setAttribute("list", boardlist);
    			forward > view > 전달 (request.getAttribute()) > 가지고 와서 화면 출력
    			출력시에는 EL & JSTL 사용
    			
    		 */
    		viewpage = "/board/boardlist.jsp";
    	} else if(cmd.equals("boardwrite")) {
    		viewpage = "/board/boardwrite.jsp";
    	} else if(cmd.equals("login")) {
    		viewpage = "/WEB-INF/views/login/login.jsp";
    		
    	} else {
    		viewpage = "/error/error.jsp";
    	}
    	
    	//4.
    	
    	//5.
    	RequestDispatcher dis = request.getRequestDispatcher(viewpage);
    	
    	//6.
    	dis.forward(request, response);
    	
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response, "GET");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response, "POST");
	}

}
