<%@page import="model1.CommentTO"%>
<%@page import="model1.BoardDAO"%>
<%@page import="model1.BoardTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.NamingException" %>

<%@ page import="javax.sql.DataSource" %>

<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.SQLException" %>
<%@ page import = "java.sql.PreparedStatement" %>

<%

	request.setCharacterEncoding("utf-8");

	CommentTO to = new CommentTO();
	
	to.setPseq( request.getParameter( "seq" ) );
	to.setWriter( request.getParameter( "cwriter" ) );
	to.setContent( request.getParameter( "ccontent" ) );
	to.setPassword( request.getParameter( "cpassword") );
	
	BoardDAO dao = new BoardDAO();

	int flag = dao.boardCmtWriterOk(to);
	
	// redirection
	out.println( "<script type='text/javascript'>");
	if( flag == 0) {
		out.println("alert( '댓글쓰기에 성공했습니다.' );");
		out.println("location.href='./board_view1.jsp?seq=" + to.getPseq() +"';");
	} else {
		out.println("비정상 입력");
		out.println("history.back();");
	}
	out.println( "</script>");
%>