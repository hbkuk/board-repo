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

	String mail = request.getParameter("mail");
	
	String CetificationNumber = request.getParameter("CetificationNumber");
	
	EmailTO to = new EmailTO();
	
	to.setCetificationNumber(CetificationNumber);
	to.setMail(mail);
	
	EmailDAO dao = new EmailDAO();
	
	int flag = dao.EmailConfirmOK(to);
	
	JSONObject obj = new JSONObject();
	
	obj.put( "flag", flag);
	
	out.println( obj );
	
%> 
