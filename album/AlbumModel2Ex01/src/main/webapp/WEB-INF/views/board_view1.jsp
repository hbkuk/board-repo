<%@page import="model1.BoardViewTO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	


<%
	
	BoardViewTO viewTo = (BoardViewTO)request.getAttribute("viewTo");

	String seq = viewTo.getTo().getSeq();
	String subject = viewTo.getTo().getSubject();
	String wdate = viewTo.getTo().getWdate();
	String writer = viewTo.getTo().getWriter();
	String hit = viewTo.getTo().getHit();
	String wip = viewTo.getTo().getWip();
	String content = viewTo.getTo().getContent();
	String filename = viewTo.getTo().getFilename();
	
	String nSeq = viewTo.getnSeq();
	String nSubject = viewTo.getnSubject();
	
	String bSeq = viewTo.getbSeq();
	String bSubject = viewTo.getbSubject();
	
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./css/board_view.css">
</head>

<body>
<!-- 상단 디자인 -->
<div class="contents1"> 
	<div class="con_title"> 
		<p style="margin: 0px; text-align: right">
			<img style="vertical-align: middle" alt="" src="./images/home_icon.gif" /> &gt; 커뮤니티 &gt; <strong>여행지리뷰</strong>
		</p>
	</div>

	<div class="contents_sub">	
	<!--게시판-->
		<div class="board_view">
			<table>
			<tr>
				<th width="10%">제목</th>
				<td width="60%"><%=subject %>(<%=wip %>)</td>
				<th width="10%">등록일</th>
				<td width="20%"><%=wdate %></td>
			</tr>
			<tr>
				<th>글쓴이</th>
				<td><%=writer %></td>
				<th>조회</th>
				<td><%=hit %></td>
			</tr>
			<tr>
				<td colspan="4" height="200" valign="top" style="padding:20px; line-height:160%">
					<div id="bbs_file_wrap">
						<div>
							<img src='./upload/<%=filename %>' width="900" onerror="" /><br />
						</div>
					</div>
					<%=content %>
				</td>
			</tr>			
			</table>
		</div>
		<div class="btn_area">
			<div class="align_left">			
				<input type="button" value="목록" class="btn_list btn_txt02" style="cursor: pointer;" onclick="location.href='./list.do'" />
			</div>
			<div class="align_right">
				<input type="button" value="수정" class="btn_list btn_txt02" style="cursor: pointer;" onclick="location.href='./modify.do?seq=<%=seq %>'" />
				<input type="button" value="삭제" class="btn_list btn_txt02" style="cursor: pointer;" onclick="location.href='./delete.do?seq=<%=seq %>'" />
				<input type="button" value="새 글쓰기" class="btn_write btn_txt01" style="cursor: pointer;" onclick="location.href='./write.do?seq=<%=seq %>'" />
			</div>	
		</div>
		<!--//게시판-->
		
		<!-- 이전글 / 다음글 -->
		<div class="next_data_area">
		<span class="b">다음글 | </span>
<%
		if( nSeq != null ) {	
			out.println( "<a href='board_view1.jsp?seq=" + nSeq+"'>" + nSubject +"</a>");
		} else {
		out.println( "다음글이 없습니다." );	
		}
%>			
		</div>
		<div class="prev_data_area">
			<span class="b">이전글 | </span>
<%
		if( bSeq != null ) {	
			out.println( "<a href='board_view1.jsp?seq=" + bSeq+"'>" + bSubject +"</a>");
		} else {
			out.println( "이전글이 없습니다." );		
		}
%>
		</div>
		<!-- //이전글 / 다음글 -->
	</div>
<!-- 하단 디자인 -->
</div>

</body>
</html>
