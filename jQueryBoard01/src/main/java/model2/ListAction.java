package model2;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.JBoardDAO;
import model1.JBoardTO;

public class ListAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println( "ListAction 호출");
		
		JBoardDAO dao = new JBoardDAO();
		ArrayList<JBoardTO> boardLists = dao.listBoard();
		
		request.setAttribute("boardLists", boardLists);
	}

}
