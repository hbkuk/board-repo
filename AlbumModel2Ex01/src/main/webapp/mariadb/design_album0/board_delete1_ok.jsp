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


<%
	// 파일 업로드 시작
	
	BoardTO to = new BoardTO();
	
	to.setSeq( request.getParameter("seq") );
	to.setSubject( request.getParameter("subject") );
	to.setWriter( request.getParameter("writer") );
	to.setPassword( request.getParameter("password") );
	
	BoardDAO dao = new BoardDAO();
	
	int flag = dao.boardDelete_ok(to);
		
	out.println( "<script type='text/javascript'>" );
	if( flag == 0 ) {
		out.println( "alert( '글삭제에 성공했습니다.' );" );
		out.println( "location.href='board_list1.jsp';" );
	} else if( flag == 1 ) {
		out.println( "alert( '비밀번호가 잘못되었습니다.' );" );
		out.println( "history.back();" );
	} else if( flag == 2 ) {
		out.println( "alert( '글삭제에 실패했습니다.' );" );
		out.println( "history.back();" );
	}
	out.println( "</script>" );	
		
	
	
	
%>