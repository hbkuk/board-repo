package model2;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model1.BoardDAO;
import model1.BoardTO;

public class ModifyOkAction implements BoardAction {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		System.out.println( "ModifyOkAction 호출");
		
		// 파일 업로드 시작
		String uploadPath = "C:\\java2\\jsp-workspace\\AlbumModel2Ex01\\src\\main\\webapp\\upload";
		int maxFileSize = 2 * 2000 * 2000;
		String encoding = "utf-8";
		
		try {
			MultipartRequest multi = new MultipartRequest(request, uploadPath, maxFileSize, encoding, new DefaultFileRenamePolicy() );
			
			BoardTO to = new BoardTO();
			
			to.setSeq( multi.getParameter("seq") );
			to.setSubject( multi.getParameter("subject") );
			to.setWriter( multi.getParameter("writer") );
			to.setPassword( multi.getParameter("password") );
			to.setContent( multi.getParameter("content") );
			to.setMail( "" );
			if(!multi.getParameter( "mail1" ).equals( "" ) && !multi.getParameter( "mail2" ).equals( "" )) {
				to.setMail( multi.getParameter( "mail1" ) + "@" + multi.getParameter( "mail2" ) );
			}
			
			to.setNewFilename( multi.getFilesystemName("upload") );
			to.setFilesize(0);
			if( multi.getFile("upload") != null ) {
				to.setFilesize( multi.getFile( "upload" ).length() );
			}
			
			BoardDAO dao = new BoardDAO();
			
			int flag = dao.boardModify_ok(to);
			
			request.setAttribute("flag", flag);
			request.setAttribute("seq", to.getSeq());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println( "에러" + e.getMessage());
		}
		
		

	}

}
