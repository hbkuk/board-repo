<%@page import="mail.EmailDAO"%>
<%@page import="mail.EmailTO"%>
<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="mail.MyAuthenticator"%>
<%@ page import="mail.MailSender"%>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="org.json.simple.JSONObject" %>

<%
	request.setCharacterEncoding("utf-8");

	// 인증 번호
	int LottoNumberArray[] = new int[6];
	//난수 객체 생성
	Random r1 = new Random();
	
	for( int i = 0; i < 6; i++ ) {	
	    LottoNumberArray[i] = (r1.nextInt( 9 ) + 1);
	 }
	
	String CetificationNumber = "";
	
	for( int Number : LottoNumberArray ) {
		CetificationNumber += Number;
	}	

	//받을 사람 이메일
	String email = request.getParameter("email");
	
	// 받을 사람 이름
	String nick = request.getParameter("nick");
	
	EmailTO to = new EmailTO();
	
	to.setCetificationNumber(CetificationNumber);
	to.setMail(email);
	
	EmailDAO dao = new EmailDAO();
	
	int flag = dao.EmailSubmit(to);
	
	if( flag == 0) {
		
		// 보낼 제목
		String subject = "[NOW?] " + nick +"님의 회원가입 인증관련 ";
		
		// 보낼 내용
		String content = "<html><head><meta charset='utf-8'/></head><body style='color:blue'><font color='blue'>환영합니다. 인증번호는 <p>" + CetificationNumber +"</p> 입니다.</font></body></html>";
		
		MailSender mailSender = new MailSender();
		
		mailSender.sendMail(to.getMail(), nick, subject, content);
		
	}
	
	JSONObject obj = new JSONObject();
	
	obj.put( "flag", flag);
	
	out.println( obj);
	
%> 
