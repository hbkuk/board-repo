<%@page import="id.idDAO"%>
<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="org.json.simple.JSONObject" %>

<%
	request.setCharacterEncoding("utf-8");

	String id = request.getParameter("id");

	idDAO dao = new idDAO();
	
	int flag = dao.hasId( id );
	
	JSONObject obj = new JSONObject();
	
	obj.put( "flag", flag);
	
	out.println( obj );
	
%> 
