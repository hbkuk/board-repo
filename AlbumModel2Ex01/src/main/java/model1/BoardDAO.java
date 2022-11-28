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
	
	private DataSource dataSource = null;
	private String uploadPath = "C:\\java2\\jsp-workspace\\AlbumModel2Ex01\\src\\main\\webapp\\upload";
	
	public BoardDAO() {
		
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup( "java:comp/env" );
			this.dataSource = (DataSource)envCtx.lookup( "jdbc/mariadb2" );
			
		} catch (NamingException e) {
			System.out.println( "[에러] " + e.getMessage() );
		}
	}
	
	public void boardInsert() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.dataSource.getConnection();
			
			String sql = "insert into album_board1 values ( 0, ?, ?, ?, ?, ?, ?, ?, 0, ?, now() )";
			pstmt = conn.prepareStatement( sql );
			
			for( int i = 1; i < 100; i++) {

			    pstmt.setString(1, "제목" + i);
			    pstmt.setString(2, "이름");
			    pstmt.setString(3, "test@test.com");
			    pstmt.setString(4, "12");
			    pstmt.setString(5, "내용 : " + i );
			    pstmt.setString(6, "607927_1.jpg" );
			    pstmt.setInt(7, 7);
			    pstmt.setString(8, "000.000.000.000");

			    pstmt.executeUpdate();

			}
		} catch (SQLException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( pstmt != null) try { pstmt.close(); } catch( SQLException e ) {}
			if( conn != null) try { conn.close(); } catch( SQLException e ) {}
		}
	}
	
	public BoardListTO boardList( BoardListTO listTO) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		BoardListTO ListTO = new BoardListTO();
		
		int cpage = listTO.getCpage();
		int recordPerPage = listTO.getRecordPerPage();
		int blockPerPage = listTO.getBlockPerPage();
		
		ArrayList<BoardTO> boardArrayList = null;
		
		try {
			
			conn = this.dataSource.getConnection();
			
			String sql = "select seq, subject, writer, date_format(wdate,'%Y-%m-%d') wdate, hit, datediff(now(), wdate) wgap, filename from album_board1 order by seq desc";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			boardArrayList = new ArrayList<BoardTO>();
			
			rs.last();
			listTO.setTotalRecord( rs.getRow());
			rs.beforeFirst();
			
			listTO.setTotalPage( ( (listTO.getTotalRecord() - 1)  / recordPerPage ) + 1 );
			
			int skip = (listTO.getCpage() - 1 ) * listTO.getRecordPerPage();
			if( skip != 0) { rs.absolute(skip); }
			
			for( int i = 1; i <= listTO.getRecordPerPage() && rs.next() ; i++ ) {
				
				BoardTO to = new BoardTO();
				
				to.setSeq( rs.getString("seq") );
				to.setSubject( rs.getString( "subject" ) );
				to.setWriter( rs.getString( "writer" ) );
				to.setWdate( rs.getString( "wdate" ) );
				to.setHit( rs.getString( "hit" ) );
				to.setWgap( rs.getInt("wgap") );
				to.setFilename( rs.getString( "filename" ) );
				
				boardArrayList.add(to);
			}
			
			listTO.setBoardArrayList( boardArrayList );
			
			listTO.setStartBlock( ((cpage -1) / blockPerPage) * blockPerPage + 1  );
			listTO.setEndBlock( ((cpage - 1) / blockPerPage )	 * blockPerPage + blockPerPage  );
			
			if( listTO.getEndBlock() >= listTO.getTotalPage() ) {
				listTO.setEndBlock( listTO.getTotalPage() );
			}
			
			
		} catch( SQLException e) {
			System.out.println( "[에러]" + e.getMessage());
		} finally {
			if( pstmt != null) try { pstmt.close(); } catch( SQLException e ) {}
			if( conn != null) try { conn.close(); } catch( SQLException e ) {}
		}
		
		return listTO;
	}
	public void boardWrite( ) {}
	public int boardWrite_ok( BoardTO to ) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int flag = 1;
		
		try {
			
		conn = dataSource.getConnection();
		
		String sql = "insert into album_board1 values (0, ?, ?, ?, ?, ?, ?, ?, 0, ?, now())";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, to.getSubject());
		pstmt.setString(2, to.getWriter());
		pstmt.setString(3, to.getMail());
		pstmt.setString(4, to.getPassword());
		pstmt.setString(5, to.getContent());
		pstmt.setString(6, to.getFilename());
		pstmt.setLong(7, to.getFilesize());
		pstmt.setString(8, to.getWip());
		
		if( pstmt.executeUpdate() == 1) {
			flag = 0;
		}
		
		} catch( SQLException e) {
			System.out.println( "[에러]" + e.getMessage());
		} finally {
			if( pstmt != null) try { pstmt.close(); } catch( SQLException e ) {}
			if( conn != null) try { conn.close(); } catch( SQLException e ) {}
		}
		
		return flag;
	}
	public BoardViewTO boardView( BoardViewTO viewTo ) {
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.dataSource.getConnection();
			
			String sql = "update album_board1 set hit = hit+1 where seq = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, viewTo.getTo().getSeq());
			
			pstmt.executeUpdate();
			
			//다음글 찾기
			sql = "select seq, subject from album_board1 where seq = (select min(seq) from album_board1 where seq > ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, viewTo.getTo().getSeq());
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				viewTo.setnSeq( rs.getString( "seq" ) );
				viewTo.setnSubject( rs.getString( "subject" ) );
			}
			
			// 이전글 찾기
			sql = "select seq, subject from album_board1 where seq = (select max(seq) from album_board1 where seq < ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, viewTo.getTo().getSeq());
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				viewTo.setbSeq( rs.getString( "seq" ) );
				viewTo.setbSubject( rs.getString( "subject" ) );
			}
			
			
			// 찾기
			sql = "select subject, wdate, writer, hit, filename, content, wip from album_board1 where seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, viewTo.getTo().getSeq());
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				
				viewTo.getTo().setSubject( rs.getString( "subject" ) ) ;
				viewTo.getTo().setWdate( rs.getString( "wdate" ) ) ;
				viewTo.getTo().setWriter( rs.getString( "writer" ) ) ;
				viewTo.getTo().setHit( rs.getString( "hit" ) ) ;
				viewTo.getTo().setFilename( rs.getString( "filename" ) ) ;
				
				viewTo.getTo().setContent( rs.getString( "content" ).replaceAll("\n", "<br>") ) ;
				viewTo.getTo().setWip( rs.getString( "wip" ) ) ;
				
			}
			
		} catch( SQLException e) {
			System.out.println( "[에러]" + e.getMessage());
		} finally {
			if( rs != null) try { rs.close(); } catch( SQLException e ) {}
			if( pstmt != null) try { pstmt.close(); } catch( SQLException e ) {}
			if( conn != null) try { conn.close(); } catch( SQLException e ) {}
		}
		
		return viewTo;
	}
	public BoardTO boardModify( BoardTO to ) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup( "java:comp/env" );
			DataSource dataSource = (DataSource)envCtx.lookup( "jdbc/mariadb2" );
			
			conn = dataSource.getConnection();
			
			String sql = "select writer, subject, content, filename, filesize, mail from album_board1 where seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getSeq());
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				to.setWriter( rs.getString( "writer" ) );
				to.setSubject( rs.getString( "subject" ) );
				if( rs.getString( "content" ) == null ) {
					to.setContent("");				
				} else {
					to.setContent(rs.getString( "content" ));
				}
				to.setFilename( rs.getString( "filename" ) );
				to.setFilesize( rs.getLong( "filesize" ) );
				to.setMail( rs.getString( "mail" ) );
				
			}
			
			} catch( NamingException e) {
				System.out.println( "[에러]" + e.getMessage());
			} catch( SQLException e) {
				System.out.println( "[에러]" + e.getMessage());
			} finally {
				if( rs != null) try { rs.close(); } catch( SQLException e ) {}
				if( pstmt != null) try { pstmt.close(); } catch( SQLException e ) {}
				if( conn != null) try { conn.close(); } catch( SQLException e ) {}
			}
		
		return to;
	}
	public int boardModify_ok( BoardTO to ) {
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int flag = 2;
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup( "java:comp/env" );
			DataSource dataSource = (DataSource)envCtx.lookup( "jdbc/mariadb2" );
			
			conn = dataSource.getConnection();
			
			String sql = "select filename from album_board1 where seq = ?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getSeq() );
			
			rs = pstmt.executeQuery();
			
			String oldFilename = null;
			if( rs.next() ) {
				oldFilename = rs.getString("filename");
						
			}
			
			if( to.getNewFilename() != null ) {
				
				sql = "update album_board1 set subject=?, mail=?, content=?, filename=?, filesize=? where seq=? and password=?";
				pstmt = conn.prepareStatement( sql );
				pstmt.setString( 1, to.getSubject() );
				pstmt.setString( 2, to.getMail() );
				pstmt.setString( 3, to.getContent() );
				pstmt.setString( 4, to.getNewFilename() );
				pstmt.setLong( 5, to.getFilesize() );
				pstmt.setString( 6, to.getSeq() );
				pstmt.setString( 7, to.getPassword() );
				
			} else {
				
				sql = "update album_board1 set subject=?, mail=?, content=? where seq=? and password=?";
				pstmt = conn.prepareStatement( sql );
				pstmt.setString( 1, to.getSubject() );
				pstmt.setString( 2, to.getMail() );
				pstmt.setString( 3, to.getContent() );
				pstmt.setString( 4, to.getSeq() );
				pstmt.setString( 5, to.getPassword() );
				
			}
			
			int result = pstmt.executeUpdate();
			if( result == 1) {
				flag = 0;
				
			if( to.getNewFilename() != null) {
				File file = new File( uploadPath, oldFilename );
				file.delete();
				}
			
			} else if( result == 0) {
				flag = 1;
				
				if( to.getNewFilename() != null)	{
				File file = new File( uploadPath, to.getNewFilename() );
				file.delete();
				}
			}
		} catch( NamingException e ) {
			System.out.println( "[에러] " + e.getMessage() );
		} catch( SQLException e ) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( pstmt != null) try { pstmt.close(); } catch( SQLException e ) {}
			if( conn != null) try { conn.close(); } catch( SQLException e ) {}
		}
		
		
		return flag;
	}
	public BoardTO boardDelete( BoardTO to ) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = this.dataSource.getConnection();
			
			String sql = "select writer, subject from album_board1 where seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getSeq());
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				to.setWriter( rs.getString( "writer" ) );
				to.setSubject( rs.getString( "subject" ) );
			}
		
			
		} catch( SQLException e) {
			System.out.println( "[에러]" + e.getMessage());
		} finally {
			if( rs != null) try { rs.close(); } catch( SQLException e ) {}
			if( pstmt != null) try { pstmt.close(); } catch( SQLException e ) {}
			if( conn != null) try { conn.close(); } catch( SQLException e ) {}
		}
		
		return to;
	}
	public int boardDelete_ok( BoardTO to ) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int flag = 2;
		try {
			conn = this.dataSource.getConnection();
			
			String sql = "delete from album_board1 where seq = ? and password = ?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getSeq() );
			pstmt.setString( 2, to.getPassword() );
			
			int result = pstmt.executeUpdate();
			if( result == 1) {
				flag = 0;
			} else {
				flag = 1;
			}
		} catch( SQLException e ) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( pstmt != null) try { pstmt.close(); } catch( SQLException e ) {}
			if( conn != null) try { conn.close(); } catch( SQLException e ) {}
		}	
		
		return flag;
	}
}
