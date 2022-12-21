package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.JBoardDAO;
import model1.JBoardTO;

public class WriteAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println( "WriteAction 호출");
		
		JBoardTO to = new JBoardTO();
		
		to.setSubject( request.getParameter("subject") );
		to.setName( request.getParameter("name") );
		to.setMail( request.getParameter("mail") );
		to.setPassword( request.getParameter("password") );
		to.setContent( request.getParameter("content") );
		to.setWip( request.getRemoteAddr() );
		
		JBoardDAO dao = new JBoardDAO();
		int flag = dao.writeBoard(to);
		
		request.setAttribute("flag", flag);
	}

}
