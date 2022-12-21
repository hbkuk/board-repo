package model1;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BoardDAO {
	
	private DataSource dataSource;
	
	private String uploadPath = "/home/master/apache-tomcat-9.0.70/webapps/project01/upload";
	
	public BoardDAO() {
		
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup( "java:comp/env" );
			this.dataSource = (DataSource)envCtx.lookup( "jdbc/mariadb2" );
		} catch (NamingException e) {
			System.out.println( "[에러] " + e.getMessage());
		}
		
	}
	
	public void boardInsert() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.dataSource.getConnection();
			
			String sql = "insert into album_cmt_board1 values( 0, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, 0, ?, now())";
			pstmt = conn.prepareStatement( sql );
			
			for( int i = 1; i < 100; i++) {

			    pstmt.setString(1, "테스트" + i);
			    pstmt.setString(2, "테스트" + i);
			    pstmt.setString(3, "test" + i +"@test.com");
			    pstmt.setString(4, "1234");
			    pstmt.setString(5, "내용 : " + i );
			    pstmt.setString(6, "board" + i + ".png" );
			    pstmt.setInt(7, 0);
			    pstmt.setString(8, "33.431441");
			    pstmt.setString(9, "126.431441");
			    pstmt.setString(10, "000.000.000.000");

			    pstmt.executeUpdate();

			}
		} catch (SQLException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( pstmt != null) try { pstmt.close(); } catch( SQLException e ) {}
			if( conn != null) try { conn.close(); } catch( SQLException e ) {}
		}
	}
	
	public BoardListTO boardList(BoardListTO listTO) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int cpage = listTO.getCpage();
		int recordPerPage = listTO.getRecordPerPage();
		int blockPerPage = listTO.getBlockPerPage();
		
		try {
			
			conn = dataSource.getConnection();
			
			String sql = "select seq, filename, subject, cmt, writer, date_format(wdate,'%Y-%m-%d') wdate, hit, datediff(now(), wdate) wgap from album_cmt_board1 order by seq desc";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			rs.last();
			listTO.setTotalRecord( rs.getRow() );
			rs.beforeFirst();
			
			listTO.setTotalPage( ( (listTO.getTotalRecord() - 1)  / recordPerPage ) + 1 );
			
			int skip = (cpage - 1) * recordPerPage;
			if( skip != 0) rs.absolute(skip);
			
			ArrayList<BoardTO> boardLists = new ArrayList<>();
			
			for( int i = 0; i < recordPerPage && rs.next(); i++ ) {
				
				BoardTO to = new BoardTO();
				
				to.setSeq( rs.getString("seq") );
				to.setFilename( rs.getString("filename") );
				to.setSubject( rs.getString("subject") );
				to.setCmt( rs.getString("cmt") );
				to.setWriter( rs.getString("writer") );
				to.setWdate( rs.getString("wdate") );
				to.setHit( rs.getString("hit") );
				to.setWgap( rs.getInt( "wgap") );
				
				boardLists.add(to);
			}
			
			listTO.setBoardLists( boardLists );
			
			listTO.setStartBlock( ((cpage -1) / blockPerPage) * blockPerPage + 1  );
			listTO.setEndBlock( ((cpage - 1) / blockPerPage )	 * blockPerPage + blockPerPage  );
			
			if( listTO.getEndBlock() >= listTO.getTotalPage() ) {
				listTO.setEndBlock( listTO.getTotalPage() );
			}
				
			} catch( SQLException e) {
				System.out.println( "[에러]" + e.getMessage());
			} finally {
				if( rs != null ) try { rs.close(); } catch( SQLException e ) {}
				if( conn != null ) try { conn.close(); } catch( SQLException e ) {}
				if( pstmt != null ) try { pstmt.close(); } catch( SQLException e ) {}
			}

		
		return listTO;
	}
	
	public int boardWriterOk( BoardTO to ) {
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int flag = 1;
		
		try {
			
			conn = this.dataSource.getConnection();
			
			String sql = "insert into album_cmt_board1 values( 0, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, 0, ?, now())";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getSubject());
			pstmt.setString(2, to.getWriter());
			pstmt.setString(3, to.getMail());
			pstmt.setString(4, to.getPassword());
			pstmt.setString(5, to.getContent());
			pstmt.setString(6, to.getFilename());
			pstmt.setLong(7, to.getFilesize());
			pstmt.setString(8, to.getLatitude());
			pstmt.setString(9, to.getLongitude());
			pstmt.setString(10 , to.getWip());
			
			if( pstmt.executeUpdate() == 1) {
				flag = 0;
			}
			
		} catch( SQLException e) {
			System.out.println( "[에러]" + e.getMessage());
		} finally {
			if( conn != null ) try { conn.close(); } catch( SQLException e ) {}
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e ) {}
		}
		
		
		
		return flag;
	}
	
	public int boardModifyOk( BoardTO to ) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int flag = 2;
		
		try {
			
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup( "java:comp/env" );
			DataSource dataSource = (DataSource)envCtx.lookup( "jdbc/mariadb2" );
			
			conn = dataSource.getConnection();
			
			String sql = "select filename from album_cmt_board1 where seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString( 1, to.getSeq() );
			
			rs = pstmt.executeQuery();
			
			String oldFilename = null;
			
			if( rs.next() ) {
				oldFilename = rs.getString( "filename" );
			}
			
			if( to.getFilename() != null ) {
				
				sql = "update album_cmt_board1 set writer=?, subject=?, content=?, filename=?, filesize=?, latitude=?, longitude=?, mail=? where seq=? and password=?";
				pstmt = conn.prepareStatement( sql );
				pstmt.setString( 1, to.getWriter() );
				pstmt.setString( 2, to.getSubject() );
				pstmt.setString( 3, to.getContent() );
				pstmt.setString( 4, to.getFilename() );
				pstmt.setLong( 5, to.getFilesize() );
				pstmt.setString( 6, to.getLatitude() );
				pstmt.setString( 7, to.getLongitude() );
				pstmt.setString( 8, to.getMail() );
				pstmt.setString( 9, to.getSeq() );
				pstmt.setString( 10, to.getPassword() );	
				
			} else {
				sql = "update album_cmt_board1 set writer=?, subject=?, content=?, latitude=?, longitude=?, mail=? where seq=? and password=?";
				pstmt = conn.prepareStatement( sql );
				pstmt.setString( 1, to.getWriter() );
				pstmt.setString( 2, to.getSubject() );
				pstmt.setString( 3, to.getContent() );
				pstmt.setString( 4, to.getLatitude() );
				pstmt.setString( 5, to.getLongitude() );
				pstmt.setString( 6, to.getMail() );
				pstmt.setString( 7, to.getSeq() );
				pstmt.setString( 8, to.getPassword() );
				
			}
			
			int result = pstmt.executeUpdate();
			
			// 업데이트가 안된 경우 password가 틀린 경우
			if( result == 0 ) {
				flag = 1;
				
				if( to.getFilename() != null ) {
					
					File file = new File( this.uploadPath, to.getFilename() );
					
				}
				
			} else if( result == 1 ) {
				
				flag = 0;
				
				if( to.getFilename() != null && oldFilename != null ) {
					
					File file = new File( this.uploadPath, oldFilename );
					file.delete();
					
				}
			}
			
		} catch( NamingException e ) {
			System.out.println( "[에러] " + e.getMessage() );
		} catch( SQLException e ) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( conn != null ) try { conn.close(); } catch( SQLException e ) {}
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e ) {}
		}
		
		
		
		return flag;
	}
	
	public int boardDeleteOk( BoardTO to ) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int flag = 2;
		
		try {
			
			conn = this.dataSource.getConnection();
			
			
			String sql = "select filename from album_cmt_board1 where seq =?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getSeq());
			
			rs = pstmt.executeQuery();
			
			String filename = null;
			
			if( rs.next() ) {
				filename = rs.getString("filename");
			}
			
			System.out.println( to.getPassword() );
			
			sql = "delete from album_cmt_board1 where seq = ? and password = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString( 1, to.getSeq() );
			pstmt.setString( 2, to.getPassword() );
			
			int result = pstmt.executeUpdate();
			
			if( result == 0 ) {
				
				flag = 1;
				 
			} else if ( result == 1) {
				 
				flag = 0;
				
				if( filename != null ) {
					File file = new File( this.uploadPath, filename );
					file.delete();
				}
			}
			
			if( flag == 0) {
				sql = "delete from album_cmt_comment1 where pseq=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString( 1, to.getSeq() );
				pstmt.executeUpdate();
			}
			
		} catch( SQLException e ) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( conn != null ) try { conn.close(); } catch( SQLException e ) {}
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e ) {}
		}
		
		
		return flag;
	}
	
	public int boardCmtWriterOk( CommentTO to ) {
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int flag = 1;
		
		try {
			
			conn = this.dataSource.getConnection();
			
			String sql = "insert into album_cmt_comment1 values ( 0, ?, ?, ?, ?, now() )";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getPseq() );
			pstmt.setString(2, to.getWriter() );
			pstmt.setString(3, to.getPassword() );
			pstmt.setString(4, to.getContent() );

			
			if( pstmt.executeUpdate() == 1) {
				flag = 0;
			}
			
			if( flag == 0 ) {
				
				sql = "update album_cmt_board1 set cmt = cmt+1 where seq=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, to.getPseq() );
				
				if( pstmt.executeUpdate() == 1) {
					flag = 0;
				} else {
					flag = 1;
				}
			}
			
		} catch( SQLException e) {
			System.out.println( "[에러]" + e.getMessage());
		} finally {
			if( conn != null ) try { conn.close(); } catch( SQLException e ) {}
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e ) {}
		}
		
		return flag;
	}
	
	public int boardCmtDeleteOk( CommentTO to ) {
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		int flag = 2;
		
		try {
			
			conn = this.dataSource.getConnection();
			
			String sql = "delete from album_cmt_comment1 where seq = ? and password = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString( 1, to.getCseq() );
			pstmt.setString( 2, to.getPassword() );
			
			int result = pstmt.executeUpdate();
			
			if( result == 0 ) {
				flag = 1;
			} else if ( result == 1) {
				flag = 0;
			}
			
			if( flag == 0 ) {
				
				sql = "update album_cmt_board1 set cmt = cmt-1 where seq=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString( 1, to.getPseq() );
				
			} else {
				flag = 1;
			}
			
			pstmt.executeUpdate();
			
			
		} catch( SQLException e ) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( conn != null ) try { conn.close(); } catch( SQLException e ) {}
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e ) {}
		}
		
		return flag;
	}
	
	public BoardTO boardView( BoardTO to ) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = this.dataSource.getConnection();
			
			String sql = "update album_cmt_board1 set hit = hit+1 where seq=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getSeq());
			
			pstmt.executeUpdate();
			
			// 부모 테이블 select 
			sql = "select subject, wip, writer, latitude, longitude, wdate, hit, filename, content from album_cmt_board1 where seq=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getSeq());
			
			rs = pstmt.executeQuery();
			
			if( rs.next()) {
				to.setSubject( rs.getString( "subject" ) );
				to.setWip( rs.getString( "wip" ) );
				to.setWriter( rs.getString( "writer" ) );
				to.setLatitude( rs.getString( "latitude" ) );
				to.setLongitude( rs.getString( "longitude" ) );
				to.setWdate( rs.getString( "wdate" ) );
				to.setHit( rs.getString( "hit" ) );
				to.setFilename( rs.getString( "filename" ) );
				to.setContent( rs.getString( "content" ) == null ? "" : rs.getString( "content" ).replace("\n", "<br>") );
			}
			
			} catch( SQLException e) {
				System.out.println( "[에러]" + e.getMessage());
			} finally {
				if( rs != null ) try { rs.close(); } catch( SQLException e ) {}
				if( conn != null ) try { conn.close(); } catch( SQLException e ) {}
				if( pstmt != null ) try { pstmt.close(); } catch( SQLException e ) {}
			}
		
		ArrayList<CommentTO> cmtLists = null;
		
		try {
				conn = this.dataSource.getConnection();
				
				String sql = "select seq, writer, wdate, content from album_cmt_comment1 where pseq=?";
				pstmt = conn.prepareStatement( sql );
				pstmt.setString(1, to.getSeq());
				
				rs = pstmt.executeQuery();
				
				cmtLists = new ArrayList<>();
				
				while( rs.next() ) {
					
					CommentTO c_to = new CommentTO();
					
					c_to.setCseq( rs.getString( "seq" ) );
					c_to.setWriter( rs.getString( "writer" ) );
					c_to.setContent( rs.getString( "content" ) == null ? "" : rs.getString( "content" ).replace("\n", "<br>") );
					c_to.setWdate( rs.getString( "wdate" ) );
					
					cmtLists.add(c_to);
				}
				
				to.setCmtLists(cmtLists);
					
			} catch( SQLException e) {
				System.out.println( "[에러]" + e.getMessage());
			} finally {
				if( rs != null ) try { rs.close(); } catch( SQLException e ) {}
				if( conn != null ) try { conn.close(); } catch( SQLException e ) {}
				if( pstmt != null ) try { pstmt.close(); } catch( SQLException e ) {}
			}
				
		
		return to;
	}
	
	public BoardTO boardModify( BoardTO to ) {
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.dataSource.getConnection();
			
			// 부모 테이블 select 
			String sql = "select writer, subject, content, filename, filesize, latitude, longitude, mail from album_cmt_board1 where seq=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getSeq());
			
			rs = pstmt.executeQuery();
			
			if( rs.next()) {
				
				to.setWriter( rs.getString( "writer" ) );
				to.setSubject( rs.getString( "subject" ) );
				to.setContent( rs.getString( "content" ) == null ? "" : rs.getString( "content" ).replace("\n", "<br>") );
				to.setFilename( rs.getString( "filename" ) );
				to.setFilesize( rs.getLong( "filesize" ) );
				to.setLatitude( rs.getString( "latitude" ) );
				to.setLongitude( rs.getString( "latitude" ) );
				to.setMail( rs.getString( "mail" ) );
				
			}
			
			} catch( SQLException e) {
				System.out.println( "[에러]" + e.getMessage());
			} finally {
				if( rs != null ) try { rs.close(); } catch( SQLException e ) {}
				if( conn != null ) try { conn.close(); } catch( SQLException e ) {}
				if( pstmt != null ) try { pstmt.close(); } catch( SQLException e ) {}
			}
		return to;
		
	}
	
	public BoardTO boardDelete( BoardTO to ) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = this.dataSource.getConnection();
			
			//
			String sql = "select subject, writer from album_cmt_board1 where seq=?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getSeq() );
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				to.setSubject( rs.getString( "subject" ) );
				to.setWriter( rs.getString( "writer" ) );
			}
			
		} catch( SQLException e ) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch( SQLException e ) {}
			if( conn != null ) try { conn.close(); } catch( SQLException e ) {}
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e ) {}
		}
		
		
		return to;
	}
}
