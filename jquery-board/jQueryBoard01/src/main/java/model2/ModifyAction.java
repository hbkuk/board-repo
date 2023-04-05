package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.JBoardDAO;
import model1.JBoardTO;

public class ModifyAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		System.out.println( "ModifyAction 호출");
		
		JBoardTO to = new JBoardTO();
		
		to.setSubject( request.getParameter("subject") );
		to.setMail( request.getParameter("mail") );
		to.setContent( request.getParameter("content") );
		to.setSeq( request.getParameter("seq") );
		to.setPassword( request.getParameter("password") );
		
		JBoardDAO dao = new JBoardDAO();
		int flag = dao.modifyBoard(to);
		
		request.setAttribute("flag", flag);
	}

}
