package com.bit;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//192.168.0.5:8090/WebServlet_1/action.do 요청이 오면 이 자바파일이 실행되게 하겠다
//아래는 xml파일에 적으면 코드량이 더 많다 이게 더 효율적이라 점점 이 방식을 택하고 있다
@WebServlet(description = "여기는 설명을 하는 것입니다",
			urlPatterns = { "/action.do"})
public class FrontServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FrontServletController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("GET");
		
		//192.168.0.5:8090/WebServlet_1/action.do?com=greeting 으로 요청 될 것
		// 아래 순서는 외우자
		
		//1.
		request.setCharacterEncoding("UTF-8");
		
		//2.
		String cmd = request.getParameter("cmd");

		//3.
		String msg="";
		if(cmd.equals("greeting")) {
			Message m = new Message();
			msg = m.getMessage(cmd);
		}
		
		//4.
		request.setAttribute("msg", msg);
		
		//5.
		RequestDispatcher dis = request.getRequestDispatcher("/greeting.jsp");
		
		//6.
		dis.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
