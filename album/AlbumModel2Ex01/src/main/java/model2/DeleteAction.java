package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.BoardDAO;
import model1.BoardTO;

public class DeleteAction implements BoardAction {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		System.out.println( "DeleteAction 호출");
		
		BoardTO to = new BoardTO();
		
		String seq = request.getParameter( "seq" );
		
		to.setSeq(seq);
		
		BoardDAO dao = new BoardDAO();
		
		to = dao.boardDelete(to);
		
		request.setAttribute("to", to);
	}

}
