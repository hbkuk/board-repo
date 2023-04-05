<%@page import="model1.BoardDAO"%>
<%@page import="model1.BoardTO"%>
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

	String seq = request.getParameter("seq");
	String password = request.getParameter("password");
	
	BoardTO to = new BoardTO();
	
	to.setSeq(seq);
	to.setPassword(password);
	
	BoardDAO dao = new BoardDAO();
	
	int flag = dao.boardDeleteOk(to);
	
	out.println( "<script type='text/javascript'>" );
	if( flag == 0 ) {
		
		out.println( "alert( '글삭제에 성공했습니다.' );" );
		out.println( "location.href='./board_list1.jsp?seq=" + to.getSeq()+ "';" );
		
	} else if( flag == 1 ) {
		
		out.println( "alert( '비밀번호가 잘못되었습니다.' );" );
		out.println( "history.back();" );
		
	} else {
		
		out.println( "alert( '글 쓰기에 실패했습니다.' );" );
		out.println( "history.back();" );
		
	}
	
	out.println( "</script>");
	
	
		
%>