<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

	int flag = (int) request.getAttribute("flag");
	String pseq = (String) request.getAttribute("pseq");
	String cpage = (String) request.getAttribute("cpage");
	
	// redirection
	out.println( "<script type='text/javascript'>");
	if( flag == 0) {
		out.println("alert( '댓글쓰기에 성공했습니다.' );");
		out.println("location.href='./board_view.do?seq=" + pseq +"&cpage=" + cpage + "';");
	} else {
		out.println("비정상 입력");
		out.println("history.back();");
	}
	out.println( "</script>");
%>