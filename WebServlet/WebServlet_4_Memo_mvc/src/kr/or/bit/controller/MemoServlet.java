package kr.or.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.dao.memodao;
import kr.or.bit.dto.memo;


@WebServlet("/MemoServlet")
public class MemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MemoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//localhost:8090/WebServlet_4_Memo_mvc/MemoServlet으로 요청이 들어오면
    	//deGet 혹은 doPost가 실행될텐데 그럼 무조건 doProcess가 실행된다
    	
    	
    	// servlet 하나만 가지고도 작업할 수 있다(Front Servlet)
    	// 현재 실습은 요청당 servlet 하나씩 만들며 하고 있는 것
    	
    	//insert
    	//1. 한글처리
    	//2. 데이터 받기
    	//3. 비즈니스(로직 처리)
    	//4. 결과 만들기
    	
    	//1. 
    	request.setCharacterEncoding("UTF-8");
    	
    	//2.
    	String id = request.getParameter("id");
    	String email = request.getParameter("email");
    	String content = request.getParameter("content");
    	
    	// 별도의 UI 가지지 않고(== 별도의 jsp를 가지지 않는다)
    	// 성공하면 목록보기/ 실패하면 재입력
    	response.setContentType("text/html;charset=UTF-8"); //view단 페이지가 할 선언부를 만드는 것
    	PrintWriter out = response.getWriter();
    	
    	
    	try {
    		
    		memodao dao = new memodao();
    		
    		/* 
    		memo m = new memo(id, email,content);
    		dao.insertMemo(m);
    		이걸 줄인 것이 아래 코드다 m이라는 변수는 앞으로 더 쓸 일이 없으니까
    		*/
    		
    		int row = dao.insertMemo(new memo(id, email,content));
    		
    		if(row > 0) {
    			// 이렇게 안 하고 jsp로 빼도 되지만 코드량이 많지 않고 다양한 방법으로 해보는 것이 좋다
    			out.print("<script>");
    			
    				out.print("alert('등록성공');");
    				out.print("location.href='MemoList'");
    				//localhost:8090/WebServlet_4_Memo_mvc/MemoList 라고 요청하는 것
    			
    			out.print("</script>");
    		}
    		
    	}catch (Exception e) {
			// TODO: handle exception
    		
    		out.print("<script>");
			
				out.print("alert('등록실패');");
				out.print("location.href='memo.html'");
		
			out.print("</script>");
		}
    	
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
