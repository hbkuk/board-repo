<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page import = "model1.JBoardTO" %>

<%@ page import = "java.util.ArrayList" %>

<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="org.json.simple.JSONObject" %>

<%
	ArrayList<JBoardTO> boardLists = (ArrayList<JBoardTO>)request.getAttribute("boardLists");

	JSONArray jsonArray = new JSONArray();
	
	for( JBoardTO to : boardLists ) {
		JSONObject obj = new JSONObject();
		
		obj.put( "seq", to.getSeq() );
		obj.put( "grpl", to.getGrpl() );
		obj.put( "subject", to.getSubject() );
		obj.put( "name", to.getName() );
		obj.put( "mail", to.getMail() );
		obj.put( "content", to.getContent() );
		obj.put( "hit", to.getHit() );
		obj.put( "love", to.getLove() );
		obj.put( "hate", to.getHate() );
		obj.put( "wdate", to.getWdate() );
		obj.put( "wgap", to.getWgap() );
		
		jsonArray.add( obj );
	}
	
	JSONObject result = new JSONObject();
	result.put( "data", jsonArray );
	
	out.println( result );

%>