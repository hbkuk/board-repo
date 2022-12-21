<%@page import="model1.CommentTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model1.BoardDAO"%>
<%@page import="model1.BoardTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.NamingException" %>

<%@ page import="javax.sql.DataSource" %>

<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.SQLException" %>
<%@ page import = "java.sql.PreparedStatement" %>
<%@ page import = "java.sql.ResultSet" %>

<%	
	String cpage = (String) request.getAttribute("cpage");
	BoardTO to = (BoardTO) request.getAttribute("to");

	String seq = to.getSeq();
	String subject = to.getSubject();
	String wip = to.getWip();
	
	String writer = to.getWriter();
	
	String latitude = to.getLatitude();
	String longitude = to.getLongitude();
	
	String wdate = to.getWdate();
	
	String hit = to.getHit();
	
	String filename = to.getFilename();
	String content = to.getContent();
	
	ArrayList<CommentTO> cmtLists = to.getCmtLists();
	
	StringBuilder sbHtml = new StringBuilder();
		
		sbHtml.append("		<table>");
		
	for( CommentTO c_to : cmtLists  ) {
		
		String c_seq = c_to.getCseq();
		String c_writer = c_to.getWriter();
		String c_content = c_to.getContent() == null ? "" : c_to.getContent().replace("\n", "<br>");
		String c_wdate = c_to.getWdate();
		
		sbHtml.append("	<form action='./board_dcmt_ok.do' method='post' id='dcfrm" + c_seq +"'>");
		sbHtml.append("		<input type = 'hidden' name ='pseq' value = " + seq + ">");
		sbHtml.append("		<input type = 'hidden' name ='c_seq' value = " + c_seq + ">");
		sbHtml.append("		<input type = 'hidden' name ='cpage' value = " + cpage + ">");
		sbHtml.append("		<tr>");
		sbHtml.append("			<td class='coment_re' >");
		sbHtml.append("				<strong>"+ c_writer +"</strong> (" + c_wdate +")");
		sbHtml.append("				<div class='coment_re_txt'>");
		sbHtml.append("					" + c_content +"");
		sbHtml.append("				</div>");
		sbHtml.append("			</td>");
		sbHtml.append("			<td class='coment_re' width='20%' align='right'>");
		sbHtml.append("				<input type='password' name='c_password' placeholder='비밀번호' class='coment_input pR10' />");
		sbHtml.append("				<input onclick='dcbtn(" + c_seq + ")' type='button' value='삭제' class='btn_comment btn_txt02' style='cursor: pointer;' />");
		sbHtml.append("			</td>");
		sbHtml.append("		</tr>");
		sbHtml.append("	</form>");
	}
	
		sbHtml.append("		</table>");
	
	
	
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./css/board_view.css">

<script type="text/javascript">

	window.onload = function() {
		document.getElementById( "cbtn" ).onclick = function() {
			
			if( document.cfrm.cwriter.value.trim() == '') {
				alert( "글쓴이를 입력하셔야 합니다.");
				return false;
			}
			
			if( document.cfrm.cpassword.value.trim() == '') {
				alert( "비밀번호를 입력하셔야 합니다.");
				return false;
			}
			
			if( document.cfrm.ccontent.value.trim() == '') {
				alert( "내용을 입력하셔야 합니다.");
				return false;
			}
			
			document.cfrm.submit();
			
		};
		
		/*
		document.getElementById( "dcbtn" ).onclick = function() {
			
			if( document.dcfrm.c_password[1].value.trim() == '') {
				alert( "비밀번호를 입력하셔야 합니다.");
				return false;
			}
			document.dcfrm.submit();
			
		};
		*/
	}
	
	const dcbtn = function( c_seq ) {
		
		document.getElementById("dcfrm"+ c_seq +"").submit();
	};
</script>
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
				<th>위치정보</th>
				<td>
					위도 : (<%=latitude %>) / 경도 : (<%=longitude %>)
				</td>
				<th></th>
				<td></td>
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
			
			<%=sbHtml.toString() %>

			<form action="./board_cmt_ok.do" method="post" name="cfrm">
				<input type = "hidden" name = "seq" value = "<%=seq %>">
				<input type = "hidden" name = "cpage" value = "<%=cpage %>">
			<table>
			<tr>
				<td width="94%" class="coment_re">
					글쓴이 <input type="text" name="cwriter" maxlength="5" class="coment_input" />&nbsp;&nbsp;
					비밀번호 <input type="password" name="cpassword" class="coment_input pR10" />&nbsp;&nbsp;
				</td>
				<td width="6%" class="bg01"></td>
			</tr>
			<tr>
				<td class="bg01">
					<textarea name="ccontent" cols="" rows="" class="coment_input_text"></textarea>
				</td>
				<td align="right" class="bg01">
					<input id="cbtn" type="button" value="댓글등록" class="btn_re btn_txt01"/>
				</td>
			</tr>
			</table>
			</form>
		</div>
		<div class="btn_area">
			<div class="align_left">			
				<input type="button" value="목록" class="btn_list btn_txt02" style="cursor: pointer;" onclick="location.href='board_list.do?cpage=<%=cpage %>'" />
			</div>
			<div class="align_right">
				<input type="button" value="수정" class="btn_list btn_txt02" style="cursor: pointer;" onclick="location.href='board_modify.do?seq=<%=seq %>&cpage=<%=cpage %>'" />
				<input type="button" value="삭제" class="btn_list btn_txt02" style="cursor: pointer;" onclick="location.href='board_delete.do?seq=<%=seq %>&cpage=<%=cpage %>'" />
				<input type="button" value="쓰기" class="btn_write btn_txt01" style="cursor: pointer;" onclick="location.href='board_write.do?cpage=<%=cpage %>'" />
			</div>	
		</div>
		<!--//게시판-->
	</div>
<!-- 하단 디자인 -->
</div>

</body>
</html>
