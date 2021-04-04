<%@page import="java.util.Scanner"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
    	DML(insert, update, delete)
    	JDBC API를 통해서 작업
    	특징
    	1. 결과집합이 없다(select가 아니니까 당연)
    	2. 반영결과가 있다(반영된 행의 수를 return)
    	
    	update emp set sal = 0을 실행했을 때 emp sal의 데이터수는 14이므로 제래도 반영됐다면 14가 return
    	update emp set al=100 where empno=1111 사번이 1111인 사원은 없으므로 0 return
    			
    	java코드의 조건처리에 이용한다(성공와 실패를 판단한다 return값이 0이라면 실패라는 것을 알 수 있으니까)
    	
    	KET POINT
    	1. Oracle DML(developer, cmd, Toll)하면 기본 옵션으로 commit 혹은 rollback이 있다
    	2. 그런데 JDBC API를 사용해서 작업한다면 DML의 기본옵션은 무조건 autocommit이다
    	3. java코드에서 delete from emp > 실행 > 자동으로 commit 일어나 무조건 실행됨
    	4. 필요에 따라서 commit과 rollback을 java코드에서 제어할 수 있다
    		그러나 default는 autocommit!
    		
    	<시작>	
    	A계좌에서 인출(update)
    	B계좌에 입금(update)
    	<종료>
		-하나의 논리적인 단위로 묶는다 이것이 transaction! 이건 성공 아니면 실패뿐
		
		autocommit 옵션 > false 전환하고
		java code에서 명시적으로 commit(), rollback()을 구현해야 한다

	//ResultSet(x) 은 DML에서 필요없다
  
  -------------------------------------------------------
  create table dmlemp
  as
    select * from emp;
    
  select * from dmlemp;

  alter table dmlemp
  add constraint pk_dmlemp_empno primary key(empno);
  --------------------------------------------------------
  
*/

	Connection conn = null;
  	Statement stmt = null;
  	//ResultSet (x) >> DML
  	
  	try{
  		Class.forName("oracle.jdbc.OracleDriver");
  		conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.5:1521:xe","bituser","1004");
		System.out.println("연결 여부 : false :" + conn.isClosed());
		
		stmt = conn.createStatement();
		/**/
		//INSERT
		int empno=0;
		String ename="";
		int deptno=0;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("사번 입력");
		empno = Integer.parseInt(sc.nextLine());
		
		System.out.println("이름 입력");
		ename = sc.nextLine();
		
		System.out.println("부서번호 입력");
		deptno = Integer.parseInt(sc.nextLine());
		
		//insert into emp(empno,ename,deptno) values(2000,'홍길동',30)
		//조선시대나 .... 현대 (parameter  설정 ...) >> values(?,?,?)
		String sql="insert into dmlemp(empno,ename,deptno) ";
		sql+= " values(" +empno+",'" + ename + "'," + deptno+ ")";
  		 
		int resultrow = stmt.executeUpdate(sql);
		
		
		/*
		//UDATE
		int deptno = 20;
		String sql = "update dmlemp set sal=0 where deptno=" + deptno;
		int resultrow = stmt.executeUpdate(sql);
		
		
		//DELETE
		int deptno = 20;
		String sql="delete from dmlemp where deptno=" + deptno;
		int resultrow = stmt.executeUpdate(sql);
		*/
		
		if(resultrow > 0){
			System.out.println("반영된 행의 수 : " + resultrow);
		}else{
			//POINT
			//문제가 생긴것이 아니고(예외가 발생된 것이 아니라)
			//반영된 행이 없다
			System.out.println("반영된 행이 없다 ...");
		}
	   	
  	}catch(Exception e){
  		   System.out.println(e.getMessage());
  	}finally{
 		if(stmt != null)try {stmt.close();}catch (Exception e) {}
		if(conn != null)try {conn.close();}catch (Exception e) {}
  	}
  	
  	// 사실 insert, update, delete는 쿼리문만 바뀌지 실행하는 과정은 동일하다
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>