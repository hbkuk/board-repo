package model2;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model1.BoardDAO;
import model1.BoardTO;

public class WriterOkAction implements BoardAction {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		System.out.println( "WriterOkAction 호출");
		
		String uploadPath = "C:\\java2\\jsp-workspace\\AlbumModel2Ex01\\src\\main\\webapp\\upload";
		int maxFileSize = 2 * 2000 * 2000;
		String encoding = "utf-8";
		
		BoardTO to = new BoardTO();
		
		try {
			MultipartRequest multi = new MultipartRequest(request, uploadPath, maxFileSize, encoding, new DefaultFileRenamePolicy() );
			
			to.setWriter( multi.getParameter( "writer" ) );
			to.setSubject( multi.getParameter( "subject" ) );
			to.setPassword( multi.getParameter( "password") );
			to.setContent( multi.getParameter( "content") );	
			to.setFilename( multi.getFilesystemName("upload") );
			to.setFilesize( multi.getFile( "upload" ).length() );
			to.setMail( multi.getParameter( "mail1") + "@" + multi.getParameter( "mail2") );
			to.setWip( request.getRemoteAddr() );
			
			BoardDAO dao = new BoardDAO();
			
			int flag = dao.boardWrite_ok( to );
			
			request.setAttribute("flag", flag);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println( "[에러] :" + e.getMessage());
		}
	}

}
