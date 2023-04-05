package model2;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model1.BoardDAO;
import model1.BoardTO;

public class ModifyOkAction implements BoardAction {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println( "ModifyOkAction 호출");
		
		String uploadPath = BoardAction.UPLOADPATH;
		int maxFileSize = 2 * 1024 * 1024;
		String encoding = "utf-8";
		
		int flag = 1;
		String seq = "";
		String cpage = "";
		
		try {
			MultipartRequest multi = new MultipartRequest(request, uploadPath, maxFileSize, encoding, new DefaultFileRenamePolicy() );
			
			BoardTO to = new BoardTO();
			
			to.setSeq( multi.getParameter( "seq" ) );
			
			seq = to.getSeq();
			
			cpage = multi.getParameter( "cpage" );
			
			to.setSubject( multi.getParameter( "subject" ) );
			to.setWriter( multi.getParameter( "writer" ) );
			to.setPassword( multi.getParameter( "password" ) );
			to.setMail("");
			
			if( !multi.getParameter( "mail1" ).equals("") && !multi.getParameter( "mail2").equals("") ) {
				to.setMail( multi.getParameter( "mail1" ) + "@" + multi.getParameter( "mail2" ) );
			}
			
			to.setContent(multi.getParameter( "content"));
			to.setWip( request.getRemoteAddr() );
			
			to.setFilename( multi.getFilesystemName( "upload" ) );
			to.setFilesize( 0 );
			
			if ( multi.getFile("upload") != null ) {
				to.setFilesize( multi.getFile("upload").length() );
			}
			
			to.setLatitude( multi.getParameter("latitude") );
			to.setLongitude( multi.getParameter("longitude") );
			
			BoardDAO dao = new BoardDAO();
			
			flag = dao.boardModifyOk(to);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println( "[에러] " + e.getMessage() );
		}
		
		request.setAttribute("flag", flag);
		request.setAttribute("seq", seq);
		request.setAttribute("cpage", cpage);
		
		//System.out.println( "modifyOkAction에서의 " + cpage );
		
	}

}
