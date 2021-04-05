package kr.or.bit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.bit.dto.memo;
import kr.or.bit.utils.SingletonHelper;

/*
	DTO를 만들었다면, 이를 활용할 DAO를 생성한다
	
	DAO의 역할
	1. DB연결
	2. CRUD 함수 생성(하나의 테이블에 대한 작업으로는 보통 전체 조회, 조건조회, 데이터 삽입, 수정, 삭제가 있다)
	 	-위의 작업 + a(검색기능 Like)을 수행할 함수
	 	
	 	2-1. 전체 조회 : select id, email, content from memo
	 	2-2. 조건 조회 : select id, email, content from memo where id=?
	 		이때, id는 PK가 걸려있다는 것을 전제조건으로 한다(그래야 조건문 안 걸고 맵핑해도 오류가 안 난다)
	 	2-3. 삽입 	: insert into memo(id, email, content) values(?,?,?)
	 	2.4 수정     : update memo set email=? , content=? where id=?
		2.5 삭제     : delete from memo where id=?
		2.6 검색 		: where email like '%naver@%'
 */
public class memodao {
	
	Connection conn = null;
	
	public memodao() {
		
		conn = SingletonHelper.getConnection("oracle");
		
		
	}
	
	//CRUD를 함수로 생성한다
	
	// 전체 조회
	public List<memo> getMemoList() throws SQLException{
		
		PreparedStatement pstmt = null;
		// 원래는 이 안에서 예외처리 해야 하지만 다른 방식으로 해본다
		
		String sql = "select id, email, content from memo";
		
		pstmt = conn.prepareStatement(sql); //미리 컴파일 시켜 놓는다
		ResultSet rs = pstmt.executeQuery(); //DB서버에 접근한다
		
		List<memo> memolist = new ArrayList<memo>(); //memo를 담은 배열을 만든다
		
		
		while(rs.next()) {
			memo m = new memo();
			m.setId(rs.getString("id"));
			m.setEmail(rs.getString("email"));
			m.setContent(rs.getString("content"));
			
			memolist.add(m);
		}
		
		SingletonHelper.close(rs);
		SingletonHelper.close(pstmt);
		
		return memolist;
	}
	
	// 조건 조회(where id=? : 데이터 한 건 있다는 것이 보장된다 id컬럼은 UK, PK가 걸려있어야 함)
	public memo getemoListById(String id) {
		
		// select id, email, content from memo where id="
		// memo m = new memo();
		// return m
		
		return null;
	}
	
	// 삽입
	//public int insertMemo(String id, Stirng email, String content) {
	// 위는 틀린 것은 아니지만 객체를 파라미터로 받는 것이 더 바람직하니 아래와 같이 작성한다
	public int insertMemo(memo m) {
	
		int resultrow=0;
		
		PreparedStatement pstmt = null;
		String sql = "insert into memo(id, email, content) values(?,?,?)";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getContent());
			
			resultrow = pstmt.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			SingletonHelper.close(pstmt);
		}
				
		return resultrow;
	}
	
	// 수정
	public int updateMemo(memo m) {
		
		return 0;
	}
	
	
	// 삭제
	// 데이터 전체가 필요한 것이 아니라서 굳이 dto 즉 메모타입으로 받을 필요가 없다
	public int deleteMemo(String id) {
		
		return 0;
	}
	
	// 검색
	public memo idSearchByEmail(String email) {
		return null;
	}

	// 필요하다면 기타 등등
}
