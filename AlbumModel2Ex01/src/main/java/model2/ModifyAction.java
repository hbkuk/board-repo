package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.BoardDAO;
import model1.BoardTO;

public class ModifyAction implements BoardAction {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		System.out.println( "ModifyAction 호출");
		
		//request.setCharacterEncoding("utf-8");

		String seq = request.getParameter("seq");

		BoardTO to = new BoardTO();
		
		to.setSeq(seq);
		
		BoardDAO dao = new BoardDAO();
		
		to = dao.boardModify(to);
		
		request.setAttribute("to", to);
		
		//System.out.println( to.getSeq());
	}

}
