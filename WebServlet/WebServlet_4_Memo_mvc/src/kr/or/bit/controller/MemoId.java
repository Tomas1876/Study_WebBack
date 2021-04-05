package kr.or.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.dao.memodao;


@WebServlet("/MemoId")
public class MemoId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MemoId() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//비동기 요청하는 곳
		//client에게 id 사용 가능 여부를(==테이블에 이미 중복값이 존재하는지 여부)를 확인하도록 요청
		// 1. Text(html, text, script, css, json)
		// 2. xml
				
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	String id = request.getParameter("id");
    	memodao dao = new memodao();
    	String ischeck = dao.isCheckById(id);
    	
    	//여기서부터 중요!
    			//이제 담은 값을 클라이언트에게 바로 전달하는 것!!!!
    	out.print(ischeck);
		

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);

	}

}
