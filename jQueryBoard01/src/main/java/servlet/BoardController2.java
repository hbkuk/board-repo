package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model2.BoardAction;
import model2.DeleteAction;
import model2.HateAction;
import model2.ListAction;
import model2.LoveAction;
import model2.ModifyAction;
import model2.ReplyAction;
import model2.WriteAction;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("*.json")
public class BoardController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setCharacterEncoding( "utf-8" );
			
			String path = request.getRequestURI().replaceAll( request.getContextPath(), "" );
			
			String url = "/WEB-INF/datas/error1.jsp";
			BoardAction boardAction = null;
			
			if( path.equals( "/list.json" ) ) {
				boardAction = new ListAction();
				boardAction.execute( request, response );
				
				url = "/WEB-INF/datas/list.jsp";
				
			} else if( path.equals( "/write.json" ) ) {
				boardAction = new WriteAction();
				boardAction.execute( request, response );
				
				url = "/WEB-INF/datas/write.jsp"; 
				
			} else if( path.equals( "/modify.json" ) ) {
				boardAction = new ModifyAction();
				boardAction.execute( request, response );
				
				url = "/WEB-INF/datas/modify.jsp";
				
			} else if( path.equals( "/delete.json" ) ) {
				boardAction = new DeleteAction();
				boardAction.execute( request, response );
				
				url = "/WEB-INF/datas/delete.jsp";
				
			} else if( path.equals( "/love.json" ) ) {
				boardAction = new LoveAction();
				boardAction.execute( request, response );
				
				url = "/WEB-INF/datas/love.jsp";
				
			} else if( path.equals( "/hate.json" ) ) {
				boardAction = new HateAction();
				boardAction.execute( request, response );
				
				url = "/WEB-INF/datas/hate.jsp";
				
			} else if( path.equals( "/reply.json" ) ) {
				boardAction = new ReplyAction();
				boardAction.execute( request, response );
		
				url = "/WEB-INF/datas/reply.jsp";
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher( url );
			dispatcher.forward( request, response );
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
