package mail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model1.signUpTO;

public class EmailDAO {
	private DataSource dataSource;
	
	public EmailDAO() {
		// TODO Auto-generated constructor stub
		
		try {
			
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup( "java:comp/env" );
			this.dataSource = (DataSource)envCtx.lookup( "jdbc/mariadb2" );
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			System.out.println( "[에러] " + e.getMessage() );
		}
	}
	
	public int EmailSubmit( EmailTO to ) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		// 비정상 >= 1, 정상 : 0;
		int flag = 1;
		
		try {
			
			conn = this.dataSource.getConnection();
			
			String sql = "insert into mailsender values ( 0, ?, ?, now() )";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getMail());
			pstmt.setString(2, to.getCetificationNumber());
			
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
	
	public int EmailConfirmOK ( EmailTO to ) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 비정상 >= 1, 정상 : 0;
		int flag = 1;
		
		try {
			
			conn = this.dataSource.getConnection();
			
			String sql = "select s_ino from mailsender where s_mail =? and s_cno = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getMail());
			pstmt.setString(2, to.getCetificationNumber());
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				flag = 0;
				System.out.println( rs.getString( "s_ino" ) );
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
