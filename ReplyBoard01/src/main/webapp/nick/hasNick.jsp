<%@page import="nick.nickDAO"%>
<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="org.json.simple.JSONObject" %>

<%
	request.setCharacterEncoding("utf-8");

	String nick = request.getParameter("nick");

	nickDAO dao = new nickDAO();
	
	int flag = dao.hasNick( nick );
	
	JSONObject obj = new JSONObject();
	
	obj.put( "flag", flag);
	
	out.println( obj );
	
%> 
