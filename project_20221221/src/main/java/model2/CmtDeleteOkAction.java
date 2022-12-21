package model2;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.BoardDAO;
import model1.BoardTO;
import model1.CommentTO;

public class CmtDeleteOkAction implements BoardAction {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println( "CmtDeleteOkAction 호출");
		
		CommentTO to = new CommentTO();
		
		to.setPseq( request.getParameter("pseq") );
		
		to.setCseq( request.getParameter("c_seq") );
		
		to.setPassword( request.getParameter("c_password") );
		
		BoardDAO dao = new BoardDAO();
		
		int flag = dao.boardCmtDeleteOk(to);
		
		request.setAttribute( "flag", flag );
		
		request.setAttribute( "pseq", to.getPseq() );
		
		request.setAttribute( "cpage", request.getParameter("cpage") );
		
	}

}
