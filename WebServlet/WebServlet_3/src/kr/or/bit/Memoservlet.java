package kr.or.bit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//localhost:8090/WebServlet_3/MemoServlet
@WebServlet("/MemoServlet")
public class Memoservlet extends HttpServlet {
 private static final long serialVersionUID = 1L;
       
   
    public Memoservlet() {
        super();
       
    }

 
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 doProcess(request, response);
 }

 
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 doProcess(request, response);
 }
 private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  //1.한글처리
  //2.데이터 받기
  //3.DB연결
  //4.Insert 실행
  //5.응답구성
	 
	 
	 
  request.setCharacterEncoding("UTF-8");
  response.setContentType("text/html;charset=UTF-8"); //클라언트에게 전달한 페이지의 정보 구성
  // 위 코드는 UI를 구성하는 것이 text와 html이라는 것을 클라이언트에게 알려주는 역할을 한다
  
  PrintWriter out = response.getWriter();
  
  String id = request.getParameter("id");
  String email = request.getParameter("email");
  String memo = request.getParameter("memo");
  //out.print(id + "," + email + "," + memo);
  
  
  try{
   Class.forName("oracle.jdbc.OracleDriver");
   Connection conn =null;
   PreparedStatement pstmt = null;
   conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","bituser","1004");
   
   //out.print("DB연결 : " +conn.isClosed());
   String sql="insert into memo(id,email,content) values(?,?,?)";
   
   pstmt = conn.prepareStatement(sql);
   pstmt.setString(1, id);
   pstmt.setString(2, email);
   pstmt.setString(3, memo);
   
   int n = pstmt.executeUpdate();
   
   if(n>0){
	    out.print("<script>");
     	out.print("alert('등록성공..');");
     	
     	//html에서는 /를 사용하지 않는다
     	out.print("location.href='MemoList';"); 
     	//주소창에 입력 enter (F5) 요청
     	//localhost:8090/WebServlet_3/MemoList
    out.print("</script>");
    
    
   }else{ //사실 별 의미 없다...else를 탄다는 건 예외가 발생하지 않았다는 거니까
	    out.print("<script>");
	    out.print("alert('등록실패..');");
	    out.print("location.href='memo.html';");
	    out.print("</script>");
   }
   
   if(pstmt != null) pstmt.close();
   if(conn != null)  conn.close();
  }catch(Exception e){
   out.print("<b> 오류 :" +  e.getMessage()  + "</b>");
   
  }
  
  
     
  
  
 }
 
}
