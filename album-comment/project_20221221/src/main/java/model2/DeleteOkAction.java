package model2;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.BoardDAO;
import model1.BoardTO;

public class DeleteOkAction implements BoardAction {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println( "DeleteOkAction 호출");
		
		String cpage = request.getParameter("cpage");
		
		BoardTO to = new BoardTO();
		
		to.setSeq(request.getParameter("seq"));
		to.setPassword(request.getParameter("password"));
		
		System.out.println( to.getPassword() );
		
		BoardDAO dao = new BoardDAO();
		
		int flag = dao.boardDeleteOk(to);
		
		request.setAttribute( "flag", flag );
		request.setAttribute( "seq", to.getSeq() );
		request.setAttribute( "cpage", cpage );
		
	}

}
