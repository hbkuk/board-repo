package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.BoardDAO;
import model1.BoardListTO;

public class ListAction implements BoardAction {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		System.out.println( "ListAction 호출");
		
		BoardListTO listTO = new BoardListTO();

		int cpage = 1;
		if( request.getParameter("cpage") != null && !request.getParameter("cpage").equals("") ) {
			listTO.setCpage( Integer.valueOf( request.getParameter("cpage") ) );
		}
		
		
		BoardDAO dao = new BoardDAO();
		
		listTO = dao.boardList(listTO);
		
		request.setAttribute("listTO", listTO);
	}

}
