<%@page import="model1.BoardDAO"%>
<%@page import="model1.BoardTO"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.NamingException" %>

<%@ page import="javax.sql.DataSource" %>

<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.SQLException" %>
<%@ page import = "java.sql.PreparedStatement" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	
	String uploadPath = "C:\\java2\\jsp-workspace\\AlbumModel2Ex01\\src\\main\\webapp\\upload";
	int maxFileSize = 2 * 2000 * 2000;
	String encoding = "utf-8";
	
	BoardTO to = new BoardTO();
	
	MultipartRequest multi = new MultipartRequest(request, uploadPath, maxFileSize, encoding, new DefaultFileRenamePolicy() );
	
	to.setWriter( multi.getParameter( "writer" ) );
	to.setSubject( multi.getParameter( "subject" ) );
	to.setPassword( multi.getParameter( "password") );
	to.setContent( multi.getParameter( "content") );	
	to.setFilename( multi.getFilesystemName("upload") );
	to.setFilesize( multi.getFile( "upload" ).length() );
	to.setMail( multi.getParameter( "mail1") + "@" + multi.getParameter( "mail2") );
	to.setWip( request.getRemoteAddr() );
	
	BoardDAO dao = new BoardDAO();
	
	int flag = dao.boardWrite_ok( to );
	
	// redirection
	out.println( "<script type= 'text/javascript'>");
	if( flag == 0) {
		out.println( "alert('글쓰기에 성공했습니다.');");
		out.println( "location.href='./board_list1.jsp';");
	} else {
		out.println( "alert('글쓰기에 실패했습니다.');");
		out.println( "history.back();");
	}
	out.println( "</script>");
		

%>