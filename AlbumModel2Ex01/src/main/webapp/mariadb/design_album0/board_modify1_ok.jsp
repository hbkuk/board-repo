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
<%@ page import = "java.sql.ResultSet" %>

<%@ page import = "com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import = "com.oreilly.servlet.MultipartRequest" %>
<%@ page import = "java.io.File" %>

<%
	// 파일 업로드 시작
	String uploadPath = "C:\\java2\\jsp-workspace\\AlbumModel2Ex01\\src\\main\\webapp\\upload";
	int maxFileSize = 2 * 2000 * 2000;
	String encoding = "utf-8";
	
	MultipartRequest multi = new MultipartRequest(request, uploadPath, maxFileSize, encoding, new DefaultFileRenamePolicy() );
	
	BoardTO to = new BoardTO();
	
	to.setSeq( multi.getParameter("seq") );
	
	to.setSubject( multi.getParameter("subject") );
	to.setWriter( multi.getParameter("writer") );
	to.setPassword( multi.getParameter("password") );
	to.setContent( multi.getParameter("content") );
	to.setMail( "" );
	if(!multi.getParameter( "mail1" ).equals( "" ) && !multi.getParameter( "mail2" ).equals( "" )) {
		to.setMail( multi.getParameter( "mail1" ) + "@" + multi.getParameter( "mail2" ) );
	}
	
	to.setNewFilename( multi.getFilesystemName("upload") );
	to.setFilesize(0);
	if( multi.getFile("upload") != null ) {
		to.setFilesize( multi.getFile( "upload" ).length() );
	}
	
	BoardDAO dao = new BoardDAO();
	
	int flag = dao.boardModify_ok(to);
		
	out.println( "<script type='text/javascript'>" );
	if( flag == 0 ) {
		out.println( "alert( '글수정에 성공했습니다.' );" );
		out.println( "location.href='board_view1.jsp?seq=" + to.getSeq() + "';" );
	} else if( flag == 1 ) {
		out.println( "alert( '비밀번호가 잘못되었습니다.' );" );
		out.println( "history.back();" );
	} else if( flag == 2 ) {
		out.println( "alert( '글수정에 실패했습니다.' );" );
		out.println( "history.back();" );
	}
	out.println( "</script>" );	
		
	
	
	
%>