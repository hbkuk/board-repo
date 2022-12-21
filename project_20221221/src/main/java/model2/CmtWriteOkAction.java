package model2;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.BoardDAO;
import model1.BoardTO;
import model1.CommentTO;

public class CmtWriteOkAction implements BoardAction {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println( "CmtWriteOkAction 호출");
		
		CommentTO to = new CommentTO();
		
		to.setPseq( request.getParameter( "seq" ) );
		to.setWriter( request.getParameter( "cwriter" ) );
		to.setContent( request.getParameter( "ccontent" ) );
		to.setPassword( request.getParameter( "cpassword") );
		
		BoardDAO dao = new BoardDAO();

		int flag = dao.boardCmtWriterOk(to);
		
		request.setAttribute( "flag", flag );
		
		request.setAttribute( "pseq", to.getPseq() );
		request.setAttribute( "cpage", request.getParameter("cpage") );
		
	}

}
