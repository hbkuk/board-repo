<%@page import="model1.BoardDAO"%>
<%@page import="model1.CommentTO"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.NamingException" %>

<%@ page import="javax.sql.DataSource" %>

<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.SQLException" %>
<%@ page import = "java.sql.PreparedStatement" %>

<%@ page import = "com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import = "com.oreilly.servlet.MultipartRequest" %>
<%@ page import = "java.io.File" %>
<%

	
	request.setCharacterEncoding("utf-8");

	String pseq = request.getParameter("pseq");
	
	String c_seq = request.getParameter("c_seq");
	String c_password = request.getParameter("c_password");
	
	CommentTO to = new CommentTO();
	
	to.setPseq(pseq);
	to.setCseq(c_seq);
	to.setPassword(c_password);
	
	BoardDAO dao = new BoardDAO();
	
	int flag = dao.boardCmtDeleteOk(to);
	
	out.println( "<script type='text/javascript'>" );
	if( flag == 0 ) {
		
		out.println( "alert( '댓글 삭제에 성공했습니다.' );" );
		out.println( "location.href='./board_view1.jsp?seq=" + pseq + "';" );
		
	} else if( flag == 1 ) {
		
		out.println( "alert( '비밀번호가 잘못되었습니다.' );" );
		out.println( "history.back();" );
		
	} else {
		
		out.println( "alert( '글 쓰기에 실패했습니다.' );" );
		out.println( "history.back();" );
		
	}
	
	out.println( "</script>");
	
	
		
%>