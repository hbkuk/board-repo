package model1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JBoardDAO {

	private DataSource dataSource;
	
	public JBoardDAO() {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup( "java:comp/env" );
			this.dataSource = (DataSource)envCtx.lookup( "jdbc/mariadb2" );
		} catch (NamingException e) {
			System.out.println( "[에러] " + e.getMessage());
		}
	}
	
	public ArrayList<JBoardTO> listBoard() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<JBoardTO> list = new ArrayList<>();
		
		try {
			
			conn = this.dataSource.getConnection();
			
			String sql = "select seq, grpl, subject, name, mail, content, hit, love, hate, date_format(wdate,'%Y-%m-%d') wdate, datediff(now(), wdate) wgap from jboard order by grp desc, grps asc";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				JBoardTO to = new JBoardTO();
				
				to.setSeq( rs.getString("seq") );
				
				to.setGrpl( rs.getInt("grpl") );
				to.setSubject( rs.getString("subject") );
				to.setName( rs.getString("name") );
				if( rs.getString("mail") != null ) {
					to.setMail( rs.getString("mail") );
				} else {
					to.setMail( rs.getString("") );
				}
				if( rs.getString("content") != null ) {
					to.setContent( rs.getString("content") );
				} else {
					to.setContent( "" );
				
				}
				to.setHit( rs.getInt("hit") );
				to.setLove( rs.getInt("love") );
				to.setHate( rs.getInt("hate") );
				to.setWdate( rs.getString("wdate") );
				to.setWgap( rs.getInt("wgap") );
				
				list.add(to);
			}
		
		} catch( SQLException e) {
			System.out.println( "[에러]" + e.getMessage());
		} finally {
			if( rs != null) try { rs.close(); } catch( SQLException e) {}
			if( pstmt != null) try { pstmt.close(); } catch( SQLException e) {}
			if( conn != null) try { conn.close(); } catch( SQLException e) {}
		}
		return list;
		
	}
	
	public int writeBoard(JBoardTO to ) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		// 비정상 >= 1, 정상 : 0;
		int flag = 1;
		
		try {
			
			conn = dataSource.getConnection();
			
			String sql = "insert into jboard values (0, last_insert_id()+1, 0, 0, ?, ?, ?, ?, ?, 0, 0, 0, ?, now() )";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getSubject());
			pstmt.setString(2, to.getName());
			pstmt.setString(3, to.getMail());
			pstmt.setString(4, to.getPassword());
			pstmt.setString(5, to.getContent());
			pstmt.setString(6, to.getWip());


			if( pstmt.executeUpdate() == 1) {
				flag = 0;
			}
			
		} catch( SQLException e) {
			System.out.println( "[에러]" + e.getMessage());
		} finally {
			if( pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if( conn != null) try {conn.close();} catch(SQLException e) {}
		}
		
		return flag;
		
	}
	
	public int modifyBoard( JBoardTO to ) {
		
	 	Connection conn = null;
		PreparedStatement pstmt = null;
		
		int flag = 2;
		
		try {
			
			conn = this.dataSource.getConnection();
			
			String sql = "update jboard set subject=?, mail=?, content=? where seq=? and password = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getSubject());
			pstmt.setString(2, to.getMail());
			pstmt.setString(3, to.getContent());
			pstmt.setString(4, to.getSeq());
			pstmt.setString(5, to.getPassword());
			
			int result = pstmt.executeUpdate();
			
			if( result == 0) {
				// 비밀번호가 잘못된 경우
				flag = 1;
			} else if(result == 1) {
				// 정상 동작
				flag = 0;
			}
			
		} catch( SQLException e) {
			System.out.println( "[에러]" + e.getMessage());
		} finally {
			if( pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if( conn != null) try {conn.close();} catch(SQLException e) {}
		}
		
		return flag;
		
	}
	
	public int deleteBoard( JBoardTO to ) {

	 	Connection conn = null;
		PreparedStatement pstmt = null;
		
		int flag = 2;
		try {
			
			conn = this.dataSource.getConnection();
			
			String sql = "delete from jboard where seq=? and password=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getSeq());
			pstmt.setString(2, to.getPassword());
			
			int result = pstmt.executeUpdate();
			if( result == 0) {
				// 비밀번호가 잘못된 경우
				flag = 1;
			} else if(result == 1) {
				// 정상 동작
				flag = 0;
			}
			
		} catch( SQLException e) {
			System.out.println( "[에러]" + e.getMessage());
		} finally {
			if( pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if( conn != null) try {conn.close();} catch(SQLException e) {}
		}
		
		return flag;
		
	}
	
	public int replyBoard( JBoardTO to ) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int flag = 1;
		
		try {
			
			conn = dataSource.getConnection();
			
			String sql = "select grp, grps, grpl from jboard where seq=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getSeq());
			
			to.setGrp( 0 ); 
			to.setGrps( 0 );
			to.setGrpl(0);
			
			rs = pstmt.executeQuery();
			
			if( rs.next()) {
				
				to.setGrp( rs.getInt( "grp" ) );
				to.setGrps( rs.getInt( "grps" ) );
				to.setGrpl( rs.getInt( "grpl" ) );
			}
			
			
			
			sql = "update jboard set grps=grps+1 where grp=? and grps > ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, to.getGrp());
			pstmt.setInt(2, to.getGrps());
			
			pstmt.executeUpdate();
			sql = "insert into jboard values (0, ?, ?, ?, ?, ?, ?, ?, ?, 0, 0, 0, ?, now() )";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, to.getGrp());
			pstmt.setInt(2, to.getGrps() + 1);
			pstmt.setInt(3, to.getGrpl() + 1);
			pstmt.setString(4, to.getSubject());
			pstmt.setString(5, to.getName());
			pstmt.setString(6, to.getMail());
			pstmt.setString(7, to.getPassword());
			pstmt.setString(8, "");
			pstmt.setString(9, to.getWip());
			
			
			if( pstmt.executeUpdate() == 1) {
				flag = 0;
			}
			
		} catch( SQLException e) {
			System.out.println( "[에러]" + e.getMessage());
		} finally {
			if( pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if( conn != null) try {conn.close();} catch(SQLException e) {}
		}
		
		return flag;
		
	}
	
	public int loveBoard( JBoardTO to) {
		
	 	Connection conn = null;
		PreparedStatement pstmt = null;
		
		int flag = 1;
		
		try {
			
			conn = this.dataSource.getConnection();
			
			String sql = "update jboard set love = love+1 where seq=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getSeq());
			
			int result = pstmt.executeUpdate();
			
			if( result == 0) {
				// 에러가 발생한 경우 
				flag = 1;
			} else if(result == 1) {
				// 정상 동작
				flag = 0;
			}
			
		} catch( SQLException e) {
			System.out.println( "[에러]" + e.getMessage());
		} finally {
			if( pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if( conn != null) try {conn.close();} catch(SQLException e) {}
		}
		
		return flag;
		
	}
	
	public int hateBoard( JBoardTO to ) {
		
	 	Connection conn = null;
		PreparedStatement pstmt = null;
		
		int flag = 1;
		
		try {
			
			conn = this.dataSource.getConnection();
			
			String sql = "update jboard set hate = hate+1 where seq=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getSeq());
			
			int result = pstmt.executeUpdate();
			
			if( result == 0) {
				// 에러가 발생한 경우 
				flag = 1;
			} else if(result == 1) {
				// 정상 동작
				flag = 0;
			}
			
		} catch( SQLException e) {
			System.out.println( "[에러]" + e.getMessage());
		} finally {
			if( pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if( conn != null) try {conn.close();} catch(SQLException e) {}
		}
		
		return flag;
	}
}
