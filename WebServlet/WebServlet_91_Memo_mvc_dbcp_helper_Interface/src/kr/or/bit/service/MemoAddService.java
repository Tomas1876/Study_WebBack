package kr.or.bit.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.memodao;
import kr.or.bit.dto.memo;

public class MemoAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward forward = new ActionForward();
		

		try {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();

			String id = request.getParameter("id");
			String email = request.getParameter("email");
			String content = request.getParameter("content");

			memodao dao = new memodao();

			// memo m = new memo(id,email,content);
			// dao.insertMemo(m);
			int row = dao.insertMemo(new memo(id, email, content));

			System.out.println("반영된 행의 수 : " + row);

			if (row > 0) {
				out.print("<script>");
				out.print("alert('등록성공');");
				out.print("location.href='MemoList.do';");

				out.print("</script>");
			} else {
				//
			}

		} catch (Exception e) {
			
			try {
				PrintWriter out = response.getWriter();
				
				out.print("<script>");
				out.print("alert('등록실패');");
				out.print("location.href='memo.html';");
				out.print("</script>");
				
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			

			System.out.println(e.getMessage());
		}

		return forward;
	}

}
