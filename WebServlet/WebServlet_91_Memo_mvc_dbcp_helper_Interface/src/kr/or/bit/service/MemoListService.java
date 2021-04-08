	package kr.or.bit.service;
	
	import java.sql.SQLException;
	import java.util.List;
	
	import javax.servlet.RequestDispatcher;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	
	import kr.or.bit.action.Action;
	import kr.or.bit.action.ActionForward;
	import kr.or.bit.dao.memodao;
	import kr.or.bit.dto.memo;
	
	public class MemoListService implements Action {
	
		@Override
		public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
			
			ActionForward forward = new ActionForward();
	
			System.out.println("[ 목록보기 ]");
	    	
	    	//요청받고 서비스를 수행 ....  servlet
	    	//전체 조회 요청
	    	
	    	//서비스 (DB 작업) : DAO
	    	memodao dao = new memodao();
	    	
	    	try {
				System.out.println("트라이블럭까진 오니");
	    		List<memo> memolist = dao.getMemoList();
	    		
	    		//화면에 출력해서 Client 전달
	    		//View 사용 (JSP)
	    		
	    		//데이터 저장
	    		request.setAttribute("memolist", memolist);
	    		
	    		//view 페이지 설정
	    		forward.setRedirect(false);
	    		forward.setPath("/WEB-INF/jsp/memolist.jsp");
	    		
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return forward;
		}
	
	}
