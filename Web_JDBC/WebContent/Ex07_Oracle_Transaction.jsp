<%@page import="java.sql.Connection"%>
<%@page import="kr.or.bit.utils.SingletonHelper"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    
	    /*
	    create table trans_A(
	      num number,
	      name varchar2(20)
	    );
	
	    create table trans_B(
	       num number constraint pk_trans_B_num primary key,
	       name varchar2(20)
	    );
	
	    JDBC >> default(dml) >> autocommit
	
	    trans_A , trans_B 하나의 논리적인 단위 (transaction 처리)
	
	    JDBC >> autocommit >> false >> 개발자는 반드시 >> commit , rollback
	    */
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%

	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt2 = null;

	conn= SingletonHelper.getConnection("oracle");
	
	String sql = "insert into trans_A(num, name) values(100,'A')";
	String sql2 = "insert into trans_B(num, name) values(100,'B')";
	
	try{
		
		conn.setAutoCommit(false);
		 // 이러면 자동 커밋이 꺼진다
		 // 개발자가 명시적으로 commit이나 rollback을 해야 함
		 
		 //begin
		 pstmt = conn.prepareStatement(sql);
		 pstmt.executeQuery();
		 
		 pstmt2 = conn.prepareStatement(sql2);
		 pstmt2.executeQuery();
		 
		 //end
		 //개발자가 
		 conn.commit(); 
		 //이라고 해야 실반영
		
	} catch(Exception e){
		
		//rollback은 여기서!
		System.out.println(e.getMessage());
		conn.rollback(); //2개의 쿼리 취소
		
	}finally{
		SingletonHelper.close(pstmt);
		SingletonHelper.close(pstmt2);
	}
%>

</body>
</html>