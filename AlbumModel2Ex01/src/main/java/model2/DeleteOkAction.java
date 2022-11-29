package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.BoardDAO;
import model1.BoardTO;

public class DeleteOkAction implements BoardAction {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		System.out.println( "DeleteOkAction 호출");
		
		BoardTO to = new BoardTO();
		
		to.setSeq( request.getParameter("seq") );
		to.setSubject( request.getParameter("subject") );
		to.setWriter( request.getParameter("writer") );
		to.setPassword( request.getParameter("password") );
		
		BoardDAO dao = new BoardDAO();
		
		int flag = dao.boardDelete_ok(to);
		
		request.setAttribute("flag", flag);
	}

}
