package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.JBoardDAO;
import model1.JBoardTO;

public class LoveAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		System.out.println( "LoveAction 호출");
		
		JBoardTO to = new JBoardTO();
		
		to.setSeq( request.getParameter("seq") );
		
		JBoardDAO dao = new JBoardDAO();
		int flag = dao.loveBoard(to);
		
		request.setAttribute("flag", flag);
	}

}
