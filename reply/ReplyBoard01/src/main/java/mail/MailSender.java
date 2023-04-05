package mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
	private String fromEmail;
	private String fromPassword;
	
	public MailSender() {
		this.fromEmail = "qudrnr1025@gmail.com";
		this.fromPassword = "xcwq pqas juvg eavt";
	}
	
	/*
	public static void main(String[] args) {
		// 받을 사람 이메일
		String toEmail = "qudrnr1025@gmail.com";
		
		// 받을 사람 이름
		String toName = "테스터";
		
		// 보낼 제목
		String subject = "테스터 제목";
		
		// 보낼 내용
		String content = "<html><head><meta charset='utf-8'/></head><body style='color:blue'><font color='blue'>내용 테스트 1</font><img src ='https://t1.daumcdn.net/daumtop_chanel/op/20200723055344399.png'/></body></html>";
		
		MailSender mailSender = new MailSender();
		
		mailSender.sendMail(toEmail, toName, subject, content);
		
		//System.out.println( "메일이 전송되었습니다." );
		
	}
	*/
	
	public void sendMail( String toEmail, String toName, String subject, String content) {
		
		try {
			// hash map과 유사한 맵계열 collection
			// 연결 환경 설정
			Properties props = new Properties();
			
			props.put( "mail.smtp.starttls.enable", "true" );
			props.put( "mail.smtp.host", "smtp.gmail.com");
			props.put( "mail.smtp.port", "587");
			props.put( "mail.smtp.auth", "true");
			
			props.put( "mail.smtp.ssl.trust", "smtp.gmail.com");
			props.put( "mail.smtp.ssl.protocols", "TLSv1.2");
			
			MyAuthenticator authenticator = new MyAuthenticator( fromEmail, fromPassword);
			
			// 연결이 이루어지면 세션을 만든다고 표현
			Session session = Session.getDefaultInstance( props, authenticator);
			
			// 메시지 내용
			MimeMessage msg = new MimeMessage(session);
			
			// 메시지 헤더 : 보내질 문서 형태에 대한 내용 기술
			msg.setHeader( "Content-Type", "text/plain; charset=utf-8");
			
			// 받는 사람 정보
			msg.addRecipient( Message.RecipientType.TO, new InternetAddress(toEmail, toName, "utf-8"));
			
			//제목글
			msg.setSubject( subject );
			msg.setContent( content, "text/html; charset=utf-8");
			
			msg.setSentDate( new java.util.Date() );
			
			Transport.send( msg );
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
