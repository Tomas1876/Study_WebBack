package kr.or.bit.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.memodao;

public class MemoIdService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		
		try {
			
	    	request.setCharacterEncoding("UTF-8");
	    	response.setContentType("text/html;charset=UTF-8");
	    	PrintWriter out = response.getWriter();
	    	
	    	String id = request.getParameter("id");
	    	memodao dao = new memodao();
	    	String ischeck = dao.isCheckById(id);
	    	
	    	//"false" or  "true"
	    	//KEY POINT
	    	out.print(ischeck);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return forward;
	}

}
