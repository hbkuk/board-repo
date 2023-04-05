package model2;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.BoardDAO;
import model1.BoardTO;

public class ModifyAction implements BoardAction {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println( "ModifyAction 호출");
		
		BoardTO to = new BoardTO();
		
		to.setSeq( request.getParameter("seq") );
		
		BoardDAO dao = new BoardDAO();
		
		to = dao.boardModify(to);
		
		request.setAttribute( "to", to );
		
		request.setAttribute( "cpage",  request.getParameter("cpage") );
		
		
	}

}
