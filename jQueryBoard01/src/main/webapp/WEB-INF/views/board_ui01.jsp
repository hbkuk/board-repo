<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<style type="text/css">
	body { font-size: 80%; }
	
	#accordion > div > div:last-child { text-align: right; }
	#accordion-resizer {
		margin: 0 60px;
		max-width: 1500px;
	}
	#btngroup {
		text-align: right;
	}
	#btngroup button {
		margin: 3px;
		padding: 3px;
		width: 100px;
	}
	label.header {
		font-size: 10pt;
		margin-right: 5px;
	}
	input.text {
		width: 80%;
		margin-bottom: 12px;
		padding: 0.4em;
	}
	input.mail1 {
		width: 45%;
		margin-bottom: 12px;
		padding: 0.4em;
	}
	input.mail2 {
		width: 30%;
		margin-bottom: 12px;
		padding: 0.4em;
	}
	fieldset {
		margin-left: 15px;
		margin-top: 15px;
		border: 0;
	}
</style>
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript" src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script type="text/javascript">


	$( document ).ready( function() {
		
		let temSeq = "";
		let temAciveNumber = 0;
		
		$( '#accordion' ).accordion({
			collapsible: 'true',
			heightStyle: 'content'
		});
		
		//$( '#writeDialog' ).css( 'display', 'none' );
		//$( '#modifyDialog' ).css( 'display', 'none' );
		//$( '#deleteDialog' ).css( 'display', 'none' );
		//$( '#replyDialog' ).css( 'display', 'none' );
		
		$( '#writeDialog' ).dialog ({
			autoOpen: false,
			width: 700,
			height: 500,
			modal: true,
			buttons: {
				'쓰기': function() {
					
					//console.log( $( '#w_subject' ).val() ); 
					//console.log( $( '#w_writer' ).val() );
					//console.log( $( '#w_mail1' ).val() + $( '#w_mail2' ).val() ); 
					//console.log( $( '#w_password' ).val() );
					//console.log( $( '#w_content' ).text() );
					
					writeServer( $( '#w_subject' ).val(),$( '#w_writer' ).val(), $( '#w_mail1' ).val() + '@' + $( '#w_mail2' ).val(), $( '#w_password' ).val(), $( '#w_content' ).val());
					
				},
				'취소': function() {
					$( this ).dialog( 'close' );
				}
			}
		});
		
		$( '#modifyDialog' ).dialog ({
			autoOpen: false,
			width: 700,
			height: 500,
			modal: true,
			buttons: {
				'수정': function() {
					
					modifyServer( $( '#m_subject' ).val(), $( '#m_writer' ).val(), $( '#m_mail1' ).val() + '@' + $( '#m_mail2' ).val(), $( '#m_password' ).val(), $( '#m_content' ).val() );
					
				},
				'취소': function() {
					$( this ).dialog( 'close' );
				}
			}
		});
		
		$( '#deleteDialog' ).dialog({
			autoOpen: false,
			width: 700,
			height: 200,
			modal: true,
			buttons: {
				'삭제': function() {
					deleteServer( $( '#d_subject' ).val(), $( '#d_password' ).val() )
				},
				'취소': function() {
					$( this ).dialog( 'close' );
				}
			}
		});
		
		$( '#replyDialog' ).dialog ({
			autoOpen: false,
			width: 700,
			height: 300,
			modal: true,
			buttons: {
				'쓰기': function() {
					replyServer( $( '#r_subject' ).val(),$( '#r_writer' ).val(), $( '#r_mail1' ).val() + '@' + $( '#r_mail2' ).val(), $( '#r_password' ).val() );
				},
				'취소': function() {
					$( this ).dialog( 'close' );
				}
			}
		});
	
		$( 'button.action' ).button();
		
		// wirte 열기
		$( 'button[action=write]' ).button().on( 'click', function() {
			$( '#writeDialog' ).dialog( 'open' );
		});
		
		
		listServer();
});
	
	const loveBtn = function(seq) {
		
		this.temSeq = ($('h3#' + seq +''  ).text().split(":"))[0];
		let loveAciveNumber = $( "#accordion" ).accordion( 'option', 'active');
		temAciveNumber = $( "#accordion" ).accordion( 'option', 'active');
		
		//console.log( loveAciveNumber );
		
		$.ajax({
			url: './love.json',
			type: 'get',
			data: {
				seq : this.temSeq,
			},
			dataType: 'json',
			success: function( jsonData ) {
				if( jsonData.flag == 0 ) {
					alert( '정상 처리' );
					
					listServer();
											
				} else {
					alert( '다시 시도해주세요.' );
				}
			},
			error: function(err) {
				alert( '[에러] ' + err.status);
			}
		});
		
		$( "#accordion" ).accordion( 'option', 'active', loveAciveNumber);
		
		console.log( loveAciveNumber );
	};
	
	const hateBtn = function(seq) {
		
		this.temSeq = ($('h3#' + seq +''  ).text().split(":"))[0];
		let hateAciveNumber = $( "#accordion" ).accordion( 'option', 'active');
		temAciveNumber = $( "#accordion" ).accordion( 'option', 'active');
		
		$.ajax({
			url: './hate.json',
			type: 'get',
			data: {
				seq : seq,
			},
			dataType: 'json',
			success: function( jsonData ) {
				if( jsonData.flag == 0 ) {
					alert( '정상 처리' );
					
					listServer();
											
				} else {
					alert( '다시 시도해주세요.' );
				}
			},
			error: function(err) {
				alert( '[에러] ' + err.status);
			}
		});
		
		$( "#accordion" ).accordion( 'option', 'active', hateAciveNumber);
		
		console.log( hateAciveNumber );
	};
	
	const deleteBtn = function(seq) {
		
		this.temSeq = ($('h3#' + seq +''  ).text().split(":"))[0];
		
		$ ( '#d_subject').val( $('h3#' + seq +''  ).next().children('div:eq(2)').text() );
		
		$( '#deleteDialog' ).dialog( 'open' );
		
	};
	
	const deleteServer = function( subject, password ) {
		
		$.ajax({
			url: './delete.json',
			type: 'get',
			data: {
				seq : this.temSeq,
				subject: subject,
				password : password
			},
			dataType: 'json',
			success: function( jsonData ) {
				if( jsonData.flag == 0 ) {
					alert( '정상 처리' );
					
					listServer();
					
					$( '#d_subject').val('');
					$( '#d_password').val('');
					
					$( '#deleteDialog' ).dialog( 'close' );
				
					
				} else if( jsonData.flag == 1 ) {
					alert( '비밀번호 입력 오류' );					
				} else {
					alert( '에러' );
				}
			},
			error: function(err) {
				alert( '[에러] ' + err.status);
			}
		});
	}
	
	
	const modifyBtn = function(seq) {
		
		this.temSeq = ($('h3#' + seq +''  ).text().split(":") )[0];
		
		this.temAciveNumber = $( "#accordion" ).accordion( 'option', 'active' );
		
		
		$( '#m_writer').val( ($('h3#' + seq +''  ).text().split(":"))[1] );
		$( '#m_mail1').val(  ($('h3#' + seq +''  ).next().children('div:eq(0)').text().split("@"))[0] );
		$( '#m_mail2').val(  ($('h3#' + seq +''  ).next().children('div:eq(0)').text().split("@"))[1] );
		$( '#m_subject').val( $('h3#' + seq +''  ).next().children('div:eq(2)').text() );
		$( '#m_content').val( $('h3#' + seq +''  ).next().children('div:eq(3)').text() );
		
		
		$( '#modifyDialog' ).dialog( 'open' );
		
	};
	
	const modifyServer = function( subject, name, mail, password, content ) {
		
		console.log( this.temSeq );
		
		$.ajax({
			url: './modify.json',
			type: 'get',
			data: {
				seq : this.temSeq,
				subject: subject,
				name : name,
				mail : mail,
				password : password,
				content : content
			},
			dataType: 'json',
			success: function( jsonData ) {
				if( jsonData.flag == 0 ) {
					alert( '정상 처리' );
					
					listServer();
					
					$( '#m_seq').val('');
					$( '#m_password').val('');
					$( '#m_email').val('');
					$( '#m_address').val('');
					
					$( '#modifyDialog' ).dialog( 'close' );
					
					
				} else if( jsonData.flag == 1 ) {
					alert( '비밀번호 입력 오류' );					
				} else {
					alert( '에러' );
				}
			},
			error: function(err) {
				alert( '[에러] ' + err.status);
			}
		});
	}
	
	const listServer = function() {
		$.ajax({
			url : './list.json',
			type: 'get',
			dataType: 'json',
			success: function( jsonData ) {
				
				//$( '#accordion' ).empty();
				let html = "";
				
				$.each( jsonData.data, function( index, item ) {
					
					let sgrpl = "";
					if( item.wgap == 0 ) {
						
						if( item.grpl != 0 ) {
							for( let j = 1; j <= item.grpl; j++) {
								sgrpl += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
							}
								sgrpl += "ㄴ[댓글]";
						}
								html +=  '<h3 id = "' + item.seq + '">' + sgrpl + item.seq + ': ' + item.name + ' : [new]</h3>';	
					
					} else {
						
						if( item.grpl != 0 ) {
							for( let j = 1; j <= item.grpl; j++) {
								sgrpl += "&nbsp;&nbsp;&nbsp;&nbsp;";
							}
								sgrpl += "ㄴ[댓글]";
						}
								html +=  '<h3 id = "' + item.seq + '">' + sgrpl + item.seq + ': ' + item.name + '</h3>';
						
					}
					
					if( item.grpl = 0 ) {
					
					html += '<div>';
					html += '	<div>' + item.mail + '</div>';
					html += '	<div>' + item.wdate + '</div>';
					html += '	<div>' + item.subject + '</div>';
					html += '	<br />';
					html += '	<hr noshade="noshade" />';
					html += '	<div>' + item.content + '</div>';
					html += '	<br />';
					html += '	<hr noshade="noshade" />';
					html += '	<br />';
					html += '	<div>';
					html += '	<div> 좋아요: ' + item.love + '</div>';
					html += '	<div> 싫어요: ' + item.hate + '</div>';
					html += '		<button idx="' + item.seq + '" onclick="loveBtn(' + item.seq +')" action="love" class="action">좋아요</button>';
					html += '		<button idx="' + item.seq + '" onclick="hateBtn(' + item.seq +')" action="hate" class="action">싫어요</button>';
					html += '		<button idx="' + item.seq + '" onclick="replyBtn(' + item.seq +')" action="reply" class="action">댓글쓰기</button>';
					html += '		<button idx="' + item.seq + '" onclick="modifyBtn(' + item.seq +')" action="modify" class="action">수정</button>';
					html += '		<button idx="' + item.seq + '" onclick="deleteBtn(' + item.seq +')" action="delete" class="action">삭제</button>';
					html += '	</div>';
					html += '</div>';
					
					
					} else {
						
						html += '<div>';
						html += '	<div>' + item.mail + '</div>';
						html += '	<div>' + item.wdate + '</div>';
						html += '	<div>' + item.subject + '</div>';
						html += '	<br />';
						html += '	<br />';
						html += '	<hr noshade="noshade" />';
						html += '	<br />';
						html += '	<div>';
						html += '	<div> 좋아요: ' + item.love + '</div>';
						html += '	<div> 싫어요: ' + item.hate + '</div>';
						html += '		<button idx="' + item.seq + '" onclick="loveBtn(' + item.seq +')" action="love" class="action">좋아요</button>';
						html += '		<button idx="' + item.seq + '" onclick="hateBtn(' + item.seq +')" action="hate" class="action">싫어요</button>';
						html += '		<button idx="' + item.seq + '" onclick="replyBtn(' + item.seq +')" action="reply" class="action">댓글쓰기</button>';
						html += '		<button idx="' + item.seq + '" onclick="modifyBtn(' + item.seq +')" action="modify" class="action">수정</button>';
						html += '		<button idx="' + item.seq + '" onclick="deleteBtn(' + item.seq +')" action="delete" class="action">삭제</button>';
						html += '	</div>';
						html += '</div>';
						
					}
						
									
				});
				
				$( '#accordion' ).html( html );
				
				$( 'button[class=action]' ).button();
				
				$( '#accordion' ).accordion( 'refresh' );
				
				$( "#accordion" ).accordion( 'option', 'active', this.temAciveNumber );
			},
			error: function(err) {
				alert( '[에러] ' + err.status);
			}
		});
	};
	
	
	const writeServer = function( subject, writer, mail, password, content ) {
		
		$.ajax({
			url: './write.json',
			type: 'get',
			data: {
				subject : subject,
				name : writer,
				mail :mail,
				password : password,
				content : content
			},
			dataType: 'json',
			success: function(jsonData) {
				if( jsonData.flag == 0) {
					alert( '정상처리' );
					
					listServer();
					
					$( '#writeDialog' ).dialog( 'close' );
					$( '#w_subject' ).val(""); 
					$( '#w_writer' ).val("");
					$( '#w_mail1' ).val("");
					$( '#w_mail2' ).val("");
					$( '#w_password' ).val(""); 
					$( '#w_content' ).val("");
				} else {
					alert( '에러' );
				}
			},
			error: function(err) {
				alert( '[에러] ' + err.status);
			}
		});
	};
	
	const replyBtn = function(seq) {
		
		this.temSeq = seq;
		
		$( '#replyDialog' ).dialog( 'open' );
		
	};
	
	const replyServer = function( subject, writer, mail, password) {
		
		$.ajax({
			url: './reply.json',
			type: 'get',
			data: {
				seq : this.temSeq,
				subject : subject,
				name : writer,
				mail :mail,
				password : password,
			},
			dataType: 'json',
			success: function(jsonData) {
				if( jsonData.flag == 0) {
					alert( '정상처리' );
					
					listServer();
					
					$( '#replyDialog' ).dialog( 'close' );
					$( '#r_subject' ).val(""); 
					$( '#r_writer' ).val("");
					$( '#r_mail1' ).val("");
					$( '#r_mail2' ).val("");
					$( '#r_password' ).val(""); 
				} else {
					alert( '에러' );
				}
			},
			error: function(err) {
				alert( '[에러] ' + err.status);
			}
		});
	};
	
</script>
</head>
<body>

<div id="accordion-resizer">
	<hr noshade="noshade" />
	<div id="accordion">
	</div>
	<hr />
	<div id="btngroup">
		<button action="write" class="action">글쓰기</button>
	</div>
</div>
<div id="writeDialog" title="글쓰기">   
	<fieldset>
		<div>
			<label for="subject" class="header">제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목</label>
			<input type="text" value="" id="w_subject" class="text ui-widget-content ui-corner-all"/>
		</div>
		<div>
			<label for="writer" class="header">이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름</label>
			<input type="text" value="" id="w_writer" class="text ui-widget-content ui-corner-all"/>
		</div>
		<div>
			<label for="mail" class="header">메&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;일</label>
			<input type="text" value="" id="w_mail1" class="mail1 ui-widget-content ui-corner-all"/>
			@
			<input type="text" value="" id="w_mail2" class="mail2 ui-widget-content ui-corner-all"/>
		</div>
		<div>
			<label for="password" class="header">비밀&nbsp;번호</label>
			<input type="password" value="" id="w_password" class="text ui-widget-content ui-corner-all"/>
		</div>
		<div>
			<label for="content" class="header">본&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;문</label>
			<br /><br />
			<textarea rows="15" cols="100" id="w_content" class="text ui-widget-content ui-corner-all"></textarea>
		</div>
	</fieldset>
</div>

<div id="replyDialog" title="댓글쓰기">
	<fieldset>
		<div>
			<label for="subject" class="header">내&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;용</label>
			<input type="text" id="r_subject" class="text ui-widget-content ui-corner-all"/>
		</div>
		<div>
			<label for="writer" class="header">이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름</label>
			<input type="text" id="r_writer" class="text ui-widget-content ui-corner-all"/>
		</div>
		<div>
			<label for="mail" class="header">메&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;일</label>
			<input type="text" id="r_mail1" class="mail1 ui-widget-content ui-corner-all"/>
			@
			<input type="text" id="r_mail2" class="mail2 ui-widget-content ui-corner-all"/>
		</div>
		<div>
			<label for="password" class="header">비밀&nbsp;번호</label>
			<input type="password" id="r_password" class="text ui-widget-content ui-corner-all"/>
		</div>
	</fieldset>
</div>

<div id="modifyDialog" title="글수정">   
	<fieldset>
		<div>
			<label for="subject" class="header">제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목</label>
			<input type="text" id="m_subject" class="text ui-widget-content ui-corner-all"/>
		</div>
		<div>
			<label for="writer" class="header">이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름</label>
			<input type="text" id="m_writer" class="text ui-widget-content ui-corner-all" />
		</div>
		<div>
			<label for="mail" class="header">메&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;일</label>
			<input type="text" id="m_mail1" class="mail1 ui-widget-content ui-corner-all"/>
			@
			<input type="text" id="m_mail2" class="mail2 ui-widget-content ui-corner-all"/>
		</div>
		<div>
			<label for="password" class="header">비밀&nbsp;번호</label>
			<input type="password" id="m_password" class="text ui-widget-content ui-corner-all"/>
		</div>
		<div>
			<label for="content" class="header">본&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;문</label>
			<br /><br />
			<textarea rows="15" cols="100" id="m_content" class="text ui-widget-content ui-corner-all"></textarea>
		</div>
	</fieldset>
</div>
<div id="deleteDialog" title="글삭제">   
	<fieldset>
		<div>
			<label for="subject" class="header">제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목</label>
			<input type="text" id="d_subject" class="text ui-widget-content ui-corner-all" readonly="readonly"/>
		</div>
		<div>
			<label for="password" class="header">비밀&nbsp;번호</label>
			<input type="password" id="d_password" class="text ui-widget-content ui-corner-all"/>
		</div>
	</fieldset>
</div>

</body>
</html>
