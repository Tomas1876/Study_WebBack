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
import kr.or.bit.service.MemoAddService;
import kr.or.bit.service.MemoIdService;
import kr.or.bit.service.MemoListService;


@WebServlet("*.do")
public class FrontMemoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FrontMemoController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	
    	request.setCharacterEncoding("UTF-8");
    	
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String urlcommand = requestURI.substring(contextPath.length());
    	
    	System.out.println("requestURI : " + requestURI);
    	System.out.println("contextPath : " + contextPath);
    	System.out.println("urlcommand : " + urlcommand);
    	
    	Action action = null;
    	ActionForward forward = null;
    	
    	if(urlcommand.equals("/MemoList.do")) {
    		
    		//목록 보여주기
    		action = new MemoListService();
    		forward = action.execute(request, response);
    		
    	} else if(urlcommand.equals("/MemoAdd.do")) {
    		
    		//추가
    		action = new MemoAddService();
    		forward = action.execute(request, response);
    		
    	} else if(urlcommand.equals("/MemoId.do")) {
    		action = new MemoIdService();
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
    	
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doProcess(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request, response);
		
	}

}
