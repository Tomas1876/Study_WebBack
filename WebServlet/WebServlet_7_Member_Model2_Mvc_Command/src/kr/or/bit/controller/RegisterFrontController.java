package kr.or.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.dao.Mvcregisterdao;
import kr.or.bit.dto.registerdto;

/* 
 /aaa.do
 /mvc.do 
 */

//<2> Register.do라는 요청도 이 서블릿 파일이 받는다. 앞이 어쨌든 .do가 오는 모든 주소를 받으니까
@WebServlet("*.do")
public class RegisterFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public RegisterFrontController() {
        super();
       
    }
    
    //<11> REgister.jsp의 form에서 이 주소로 다시 요청을 했다 그런데 지금은 cmd=registerok이다
    // 아래 로직으로 내려가자
    
    //<3> doProcess함수가 실행된다 0407 오늘은 command방식으로!
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
    	// 요청 주소가 어떻게 들어오냐면
    			// 목록보기의 경우 : list.do
    			// 글쓰기의 경우  : write.do
    			// 글쓰기 처리   : writeok.do
    			// 이런식으로 요청이 왔을 때 늘 doProcess 실행
    			
    			// 이 때 어떤 서비스에 대한 요청인지를 판단하는 방법은 두 가지가 있다.
    			// 1. command 방식 : servlet.do?cmd=login&id=kglim&pwd=1004   > cmd > if(cmd.equals("login"))
    	        // 2. url 방식     : login.do?id=kglim&pwd=1004  :  login.do > url 주소로 요청을 판단
    	
    	//<4> 로직에 따라 처리한다
    	// 늘 하는 순서
    	//1. 한글
    	request.setCharacterEncoding("UTF-8");
    	
    	//<5> index.html클라이언트가 요청한 주소의 cmd 값을 command라는 문자열 변수에 담아준다
    	//현재 cmd의 값은 register다
    	
    	//2. 데이터 받기
    	String command = request.getParameter("cmd");
    	
    	//3. 요청 판단해서 서비스 만들기
    	String viewpage="";
    	
    	//<6> if절을 탄다
    	if(command.equals("register")) {  //?cmd=register
    		
    		//<7> viewpage라는 변수에 "/WEB-INF/Register/Register.jsp" 주소가 담긴다
    		
    		//화면전달
    		viewpage = "/WEB-INF/Register/Register.jsp";
    		
    		//<12> registerok 때문에 이번에는 else if절을 탄다
    	}else if(command.equals("registerok")) { 
    		
    		//<13> Register.jsp의 form에서 name으로 지정했던 id, pwd, email을 getParameter함수로 받아와서
    		// 변수에 할당해준다
    		int id=Integer.parseInt(request.getParameter("id"));
    		String pwd = request.getParameter("pwd");
    		String email = request.getParameter("email");
    		
    		System.out.println("id" + id);
    		
    		//controller -> [service 요청] -> dao 요청 -> 
    		//Mvcregister dto = new Mvcregister();
    		//dto.setId(id);
    		//dto.setPwd(pwd);
    		//dto.setEmail(email);
    		
    		//<14> 데이터를 담고 있는 dto객체를 생성해준다
    		registerdto dto = new registerdto();
    		
    		//<15> Register.jsp에서 입력받은 id, pwd, email 값들을 setter함수를 이용해 넣어준다
    		dto.setId(id);
    		dto.setPwd(pwd);
    		dto.setEmail(email);
    		    		
    		dto.toString();
    		
    		//<16> insert함수를 가진 dao객체를 생성한다
    		Mvcregisterdao dao = new Mvcregisterdao();
    		
    		//<17> dto에 setter함수로 넣어준 값들을 insert함수의 파라미터로 넣어준다
    		// 즉 이건 DB에 직접 쿼리문을 입력하는 것과 같은 작업이다
    		// 그리고 int result는 insert가 성공했는지 여부를 반환한다 
    		int result = dao.writeOk(dto);
    		
    		String resultdata="";
    		
    		//<18> 반영이 된 줄 수(writeOk함수의 리턴값)이 있다는 거니까 성공
    		//문제가 없다면 if절을 탈 것이다
    		if(result > 0) {
    			resultdata = "welcome to bit" + dto.getId() + "님";
    		}else {
    			resultdata = "Insert Fail retry";
    		}
    		
    		
    		//<19> resultdata에 담긴 값을 request객체에 data라는 속성으로 저장해준다
    		
    		//결과 저장하기
    		request.setAttribute("data",resultdata);

    		
    		//<20> 값을 저장한 후, 저장한 값을 보낼 페이지의 주소를 지정한다
    		
    		//뷰지정하기
    		viewpage = "/WEB-INF/Register/Register_welcome.jsp";   
    		
    	}
    	
    	
    	//<21> forward 할 수 있도록 RequestDispatcher 객체 사용
    	
    	//<8> 사용자의 요청을 다른 jsp에 전달할 수 있게 해주는 RequestDispatcher 객체 타입의 변수를 만들고
    	// viewpage 즉 "/WEB-INF/Register/Register.jsp"를 담아준다
    	RequestDispatcher dis = request.getRequestDispatcher(viewpage);
    	
    	
    	//<22> "/WEB-INF/Register/Register_welcome.jsp"라는 주소로
    	// 아까 request객체에 담은 값 resultdata를 전송한다
    	
    	//<9> "/WEB-INF/Register/Register.jsp"로 출발
    	dis.forward(request, response);
    	
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
