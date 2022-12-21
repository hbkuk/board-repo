package model2;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.BoardDAO;
import model1.BoardTO;

public class ViewAction implements BoardAction {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println( "ViewAction 호출");
		
		BoardTO to = new BoardTO();	

		to.setSeq( request.getParameter("seq") );
		
		BoardDAO dao = new BoardDAO();
		
		to = dao.boardView(to);
		
		request.setAttribute("cpage", request.getParameter("cpage"));
		
		request.setAttribute("to", to);
		
	}

}
