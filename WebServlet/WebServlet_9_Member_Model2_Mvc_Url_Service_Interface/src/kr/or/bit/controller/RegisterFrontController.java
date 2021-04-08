package kr.or.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.Mvcregisterdao;
import kr.or.bit.dto.registerdto;
import kr.or.bit.service.LoginOkServiceAction;
import kr.or.bit.service.RegisterOkServiceAction;

/* 
Command  방식
@WebServlet("/web.do")
web.do?cmd=login
web.do?cmd=loginok


URL 방식
주소가 고정되면 안된다
@WebServlet("*.do")
*.do >>  login.do
*.od >>  loginok.do

*/
@WebServlet("*.do")
public class RegisterFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public RegisterFrontController() {
        super();
       
    }
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
    	request.setCharacterEncoding("UTF-8");
    	
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String urlcommand = requestURI.substring(contextPath.length());
    	
    	System.out.println(requestURI);
    	System.out.println(contextPath);
    	System.out.println(urlcommand);
    	
    	//kr.or.bit.service : 서비스 클래스
    	Action action=null;
    	ActionForward forward=null;
    	

    	if(urlcommand.equals("/Register.do")) { 
    		//UI 제공 (서비스 클래스 필요없다)
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/WEB-INF/Register/Register.jsp");
    		
    	}else if(urlcommand.equals("/Registerok.do")) {
    		//UI 제공  + 로직 필요
    		action = new RegisterOkServiceAction();
    		forward = action.execute(request, response);
    		//POINT
    		//execute  > request 객체 주소 , response 객체 주소 전달
    	}else if(urlcommand.equals("/login.do")) {
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/WEB-INF/login/login.jsp");
    	}else if(urlcommand.equals("/loginok.do")) {
    		action = new LoginOkServiceAction();
    		forward = action.execute(request, response);
    	}
    	
    	
    	
    	
    	
    	
    	if(forward != null) {
    		if(forward.isRedirect()) { //true  페이지를 재요청
    			response.sendRedirect(forward.getPath());  //거의 사용 안해요
    		}else { //false
    			
    			//1. UI 전달된 경우
    			//2. UI + 로직 
    			System.out.println("forward : " + forward.getPath());
    			RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
    			dis.forward(request, response);
    		}
    	}
    	
    	
    	
    	
    	/*
    	String viewpage="";
    	
    	if(urlcommand.equals("/Register.do")) { 
    		//화면전달
    		viewpage = "/WEB-INF/Register/Register.jsp";    		
    	}else if(urlcommand.equals("/Registeok.do")) { 
    		//로직처리
    		//추가적인 데이터
    		int id=Integer.parseInt(request.getParameter("id"));
    		String pwd = request.getParameter("pwd");
    		String email = request.getParameter("email");
    		
    		System.out.println("id" + id);
    		
    		//controller -> [service 요청] -> dao 요청 -> 
    		//Mvcregister dto = new Mvcregister();
    		//dto.setId(id);
    		//dto.setPwd(pwd);
    		//dto.setEmail(email);
    		
    		registerdto dto = new registerdto();
    		dto.setId(id);
    		dto.setPwd(pwd);
    		dto.setEmail(email);
    		    		
    		dto.toString();
    		
    		Mvcregisterdao dao = new Mvcregisterdao();
    		int result = dao.writeOk(dto);
    		
    		String resultdata="";
    		if(result > 0) {
    			resultdata = "welcome to bit" + dto.getId() + "님";
    		}else {
    			resultdata = "Insert Fail retry";
    		}
    		
    		
    		//결과 저장하기
    		request.setAttribute("data",resultdata );
    		
    		//뷰지정하기
    		viewpage = "/WEB-INF/Register/Register_welcome.jsp";   
    		
    	}
    	*/
    	//RequestDispatcher dis = request.getRequestDispatcher(viewpage);
    	
    	//dis.forward(request, response);
    	
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGET");
		doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPOST");
		doProcess(request, response);
	}

}
