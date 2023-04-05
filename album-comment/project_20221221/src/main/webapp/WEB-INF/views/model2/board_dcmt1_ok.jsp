<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	int flag = (int) request.getAttribute("flag");
	String pseq = (String) request.getAttribute("pseq");
	String cpage = (String) request.getAttribute("cpage");
	
	out.println( "<script type='text/javascript'>" );
	if( flag == 0 ) {
		
		out.println( "alert( '댓글 삭제에 성공했습니다.' );" );
		out.println( "location.href='./board_view.do?seq=" + pseq + "&cpage=" + cpage + "';" );
		
	} else if( flag == 1 ) {
		
		out.println( "alert( '비밀번호가 잘못되었습니다.' );" );
		out.println( "history.back();" );
		
	} else {
		
		out.println( "alert( '글 쓰기에 실패했습니다.' );" );
		out.println( "history.back();" );
		
	}
	
	out.println( "</script>");
	
	
		
%>