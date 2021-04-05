package kr.or.bit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.dao.memodao;
import kr.or.bit.dto.memo;


@WebServlet("/MemoList")
public class MemoList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MemoList() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("목록 보기");
		
		// 요청 받고 서비스를 수행하는 것을 서블릿으로 다 처리해본다
		// 전체 데이터를 조회하는 요청 > DB를 서비스 한다
		// > DB작업을 하기 위한 DAO를 만들어두었따 == 여기서 DAO 객체를 생성한다
		memodao dao = new memodao();
		
		try {
			
			List<memo> memolist = dao.getMemoList();
			
			// 이제 화면에 출력해서 클라이언트에게 전달해야 한다
			// MVC모델이니까 View 사용(JSP)
			
			//데이터 저장
			request.setAttribute("memolist", memolist); //java파일 소유의 request객체에 저장했다			
			
			//view 페이지 설정
			RequestDispatcher dis = request.getRequestDispatcher("/memolist.jsp");
			
			//view  forward
			dis.forward(request, response);
			
			//forward한 페이지에서 객체를 출력
			
		}catch (Exception e) {

			e.printStackTrace();
			
		}
		
		
		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
