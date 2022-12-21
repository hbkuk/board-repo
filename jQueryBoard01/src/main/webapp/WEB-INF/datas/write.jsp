<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
    
<%@ page import = "model1.JBoardTO" %>
<%@ page import = "model1.JBoardDAO" %>

<%@ page import = "java.util.ArrayList" %>

<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="org.json.simple.JSONObject" %>

<%
	int flag = (int) request.getAttribute("flag");
	
	JSONObject obj = new JSONObject();
	
	obj.put( "flag", flag);
	
	out.println( obj);


%>