<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>나우 회원가입</title>
<link rel="icon" href="./images/favicon.png">
<link rel="stylesheet" href="./css/quiz07.css">
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript">

	$( document ).ready(function() {
		
		$( '#joinBtn' ).on( 'click', function() {
			if ( $( "#nick" ).val() == '' ) {
				alert( "닉네임을 입력하세요.");
				return false;
			}
			
			if ( $( "#id" ).val() == '' ) {
				alert( "아이디를 입력하세요.");
				return false;
			}
			
			if ( $( "#pw" ).val() == '' ) {
				alert( "비밀번호를 입력하세요.");
				return false;
			}
			
			if ( $( "#pw-confirm" ).val() == '' ) {
				alert( "비밀번호 재확인을 입력하세요.");
				return false;
			}
			
			if ( $( "#name" ).val() == '' ) {
				alert( "이름을 입력하세요.");
				return false;
			}
			
			if ( $( "#year" ).val() == '' ) {
				alert( "연도를 입력하세요.");
				return false;
			}
			
			if ( $( "#month" ).val() == '0m' ) {
				alert( "월을 선택하세요.");
				return false;
			}
			
			if ( $( "#day" ).val() == '' ) {
				alert( "일을 선택하세요.");
				return false;
			}
			
			var obj_length = document.getElementsByName("gender").length;
			var selectedGender = "";

			for (var i=0; i<obj_length; i++) {
			    if (document.getElementsByName("gender")[i].checked == true) {
			    	selectedGender = document.getElementsByName("gender")[i].value;
			    }
			}

			if( selectedGender == "") {
			    alert( "성별 항목 중 무조건 하나는 선택하셔야 합니다.");
			    return false;
			}
			
			if ( $( "#email" ).val() == '' ) {
				alert( "이메일 입력 후 인증하셔야 합니다.");
				return false;
			}
			
		});
		
		// 입력값 검사 진행할 것.... 공백이 아니거나... 뭐 .. 그런거..?
		
        let timeout = null;
        
		$("#nick").keydown(function(){
			
         clearTimeout(timeout);

	         timeout = setTimeout(function () {
	
	            //console.log ( $("#nick").val() );
	            
	            //console.log( $("#nick").val() );
	            
	            //$('.placehold-nick').toggleClass('hasNick');
	            
	 			$.ajax({
					url: './nick/hasNick.jsp',
					type: 'get',
					data: {
						nick : $("#nick").val()
					},
					dataType: 'json',
					success: function( jsonData ) {
						if( jsonData.flag == 0 ) {
							//console.log( '사용불가' );
							
							$('.placehold-nick').removeClass('hasNick'); 
							$('.placehold-nick').removeClass('ableNick'); 
							$('.placehold-nick').addClass('notAbleNick');
							
							//$('.placehold-nick').toggleClass('hasNick');
							//$('.placehold-nick').toggleClass('notAbleNick');
							
							//$(".hasNick").removeAttr("content");
							//$(".hasNick").attr("content", "사용 불가능");
							//$(".hasNick").attr("background", "red");
							
							//$('#nick').removeClass('notAbleNick'); 
						} else {
							
							//console.log( '사용가능' );
							
							$('.placehold-nick').removeClass('hasNick'); 
							$('.placehold-nick').removeClass('notAbleNick');
							$('.placehold-nick').addClass('ableNick');
							
							//$('.placehold-nick').toggleClass('hasNick');
							//$('.placehold-nick').toggleClass('ableNick');
							
							//$(".hasNick").removeAttr("content");
							//$(".hasNick").attr("content", "사용 가능");
							//$(".hasNick").attr("background", "#1187CF");
							//$('#nick').addClass('ableNick');
						}
					},
					error: function(err) {
						alert( '[에러] ' + err.status);
					}
				});
	            
	         }, 500);
				
				  
			});
		
		$("#id").keydown(function(){
			
         clearTimeout(timeout);

	         timeout = setTimeout(function () {
	            
	 			$.ajax({
					url: './id/hasId.jsp',
					type: 'get',
					data: {
						id : $("#id").val()
					},
					dataType: 'json',
					success: function( jsonData ) {
						if( jsonData.flag == 0 ) {
							console.log( '사용불가' );
							
							$('.placehold-id').removeClass('hasId'); 
							$('.placehold-id').removeClass('ableId'); 
							$('.placehold-id').addClass('notAbleId');
							
						} else {
							
							console.log( '사용가능' );
							
							$('.placehold-id').removeClass('hasId'); 
							$('.placehold-id').removeClass('notAbleId');
							$('.placehold-id').addClass('ableId');
						}
					},
					error: function(err) {
						alert( '[에러] ' + err.status);
					}
				});
	            
	         }, 500);
				
				  
			});
		
		$( '#email_submit').on( 'click', function() {
			if ( $( "#email" ).val() == '' ) {
				alert( "이메일 입력 후 인증하셔야 합니다.");
				return false;
			} else {
				
				if( timeoutHandle1 === undefined && timeoutHandle1 === undefined ) {
					alert( '1번쨰 전송' );
					$( 'span#timer' ).remove();
					$( '#b_email' ).append('<span id="timer"></span>');
					countdown(1, 00); 
					//console.log( timeoutHandle1 );
					
				} else {
					alert( '재 전송' );
					clearTimeout( timeoutHandle1 );
					clearTimeout( timeoutHandle2 );
					$( 'span#timer' ).remove();
					$( '#b_email' ).append('<span id="timer"></span>');
					countdown(1, 00); 
				}
				
				$.ajax({
					url: './mail/Mail_sender.jsp',
					type: 'get',
					data: {
						email : $('#email').val(),
						nick : $('#nick').val()
					},
					dataType: 'json',
					success: function( jsonData ) {
						
						if( jsonData.flag == 0 ) {
							alert( '[전송 완료] 서버 사정에 따라 수십분이 소요될 수 있습니다.' );
						} else {
							alert( '서버 에러' );
					}
						
					},
					error: function(err) {
						alert( '[에러] ' + err.status);
					}
				});
				
				
				$( '#email_confirm' ).remove();
				$( '#email_confirm_btn' ).remove();
				$( '.email_field').append( '<input id = "email_confirm" type="text" placeholder="인증번호를 입력하세요." maxlength="6" ><input id="email_confirm_btn" type="button" onclick="confirmBtn()" value="인증하기">' )
				
				
			}
			
		});
		
		$( '#joinBtn').on( 'click', function() {
			
			alert( '가입 완료' );
			
		});
		
	});
	
	//let cFlag = 0;
	let timeoutHandle1;
	let timeoutHandle2;
	
	function countdown(minutes, seconds) {
	        function tick() {
	            let counter = $( '#timer' ).text( minutes.toString() + ":" + (seconds < 10 ? "0" : "") + String(seconds) ); 
	            seconds--;
	            if (seconds >= 0) {
	                timeoutHandle1 = setTimeout(tick, 1000);
	                
	            } else {
	                if (minutes >= 1) {
	                    // countdown(mins-1);   never reach “00″ issue solved:Contributed by Victor Streithorst
	                timeoutHandle2 = setTimeout(function () {
	                        countdown(minutes - 1, 59);
	                    }, 1000);
	                }
	            }
	        }
	        tick();
	    }
	
	const confirmBtn = function() {
		
		if( $( '#timer').html() == '0:00' ) {
			alert( '인증이 만료되었습니다. 다시 시도해주세요.');
		} else {
			//cFlag++;
			
			$.ajax({
				url: './mail/MailConfirm.jsp',
				type: 'get',
				data: {
					
					mail : $('#email').val(),
					CetificationNumber : $('#email_confirm').val()
				},
				dataType: 'json',
				success: function( jsonData ) {
					
					if( jsonData.flag == 0 ) {
						alert( '[인증 완료] 가입을 완료해 주세요.' );
					} else {
						alert( '[인증 실패] 인증번호가 틀립니다. 다시 시도해 주세요.' );
				}
					
				},
				error: function(err) {
					alert( '[에러] ' + err.status);
				}
			});
			
			$("#email_submit").attr("disabled", "disabled");
			$("#email_submit").css('background', '#B2B2B2');
			
			$("#email_confirm_btn").attr("disabled", "disabled");
			$("#email_confirm_btn").css('background', '#B2B2B2');
			
		}
	};

</script>
</head>
<body>
<div class="member">
    <!-- 1. 로고 -->
    <img class="logo" src="./images/logo-now.png">

    <!-- 2. 필드 -->
    <div class="field">
        <b>닉네임</b>
        <span class="placehold-text placehold-nick hasNick"><input id = "nick" type="text"></span>
    </div>
    <div class="field">
        <b>아이디</b>
        <span class="placehold-text placehold-id hasId"><input id = "id" type="text"></span>
    </div>
    <div class="field">
        <b>비밀번호</b>
        <input id="pw" class="userpw" type="password">
    </div>
    <div class="field">
        <b>비밀번호 재확인</b>
        <input id="pw-confirm" class="userpw-confirm" type="password">
    </div>
    <div class="field">
        <b>이름</b>
        <input id="name" type="text">
    </div>

    <!-- 3. 필드(생년월일) -->
    <div class="field birth">
        <b>생년월일</b>
        <div>
            <input id="year" type="number" placeholder="년(4자)">                
            <select id = "month">
                <option value="0m">월</option>
                <option value="1m">1월</option>
                <option value="2m">2월</option>
                <option value="3m">3월</option>
                <option value="4m">4월</option>
                <option value="5m">5월</option>
                <option value="6m">6월</option>
                <option value="7m">7월</option>
                <option value="8m">8월</option>
                <option value="9m">9월</option>
                <option value="10m">10월</option>
                <option value="11m">11월</option>
                <option value="12m">12월</option>
            </select>
            <input id="day" type="number" placeholder="일">
        </div>
    </div>

    <!-- 4. 필드(성별) -->
    <div class="field gender">
        <b>성별</b>
        <div>
        	<label><input type="radio" name="gender" value="0">선택안함</label>
            <label><input type="radio" name="gender" value="1">남자</label>
            <label><input type="radio" name="gender" value="2">여자</label>
        </div>
    </div>

    <!-- 5. 이메일_전화번호 -->
    <div class="field email_field">
        <b id = "b_email">본인 확인 이메일</b>
        <input id = "email" type="email" placeholder="가입하기 버튼을 누르시면 이메일이 전송됩니다."><input id="email_submit" type="submit" value="전송하기">
    </div>
   

    <!-- 6. 가입하기 버튼 -->
    <input id="joinBtn" type="submit" value="가입하기">

    <!-- 7. 푸터 -->
    <div class="member-footer">
        <div>
            <a href="#none">이용약관</a>
            <a href="#none">개인정보처리방침</a>
            <a href="#none">책임의 한계와 법적고지</a>
            <a href="#none">회원정보 고객센터</a>
        </div>
        <span><a href="#none">NOW Corp.</a></span>
    </div>
</div>
</body>
</html>