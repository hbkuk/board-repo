package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.BoardDAO;
import model1.BoardTO;
import model1.BoardViewTO;

public class ViewAction implements BoardAction {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		System.out.println( "ViewAction 호출");
		
		String seq = request.getParameter("seq");

		BoardTO to = new BoardTO();
		
		to.setSeq(seq);
		
		BoardViewTO viewTo = new BoardViewTO();
		
		viewTo.setTo(to);
		
		BoardDAO dao = new BoardDAO();
		
		viewTo = dao.boardView(viewTo);
		
		request.setAttribute("viewTo", viewTo);
	}

}
