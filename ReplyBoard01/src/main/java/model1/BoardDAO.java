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

public class BoardDAO {
	private DataSource dataSource;
	
	public BoardDAO() {
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
	
	public void signUp() {}
	
	public int signUpOk( signUpTO to ) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		// 비정상 >= 1, 정상 : 0;
		int flag = 1;
		
		try {
			
			conn = this.dataSource.getConnection();
			
			String sql = "insert into sign1 (0, ?, ?, ?, ?, ?, ?, now(), 1)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getId());
			pstmt.setString(2, to.getNick());
			pstmt.setString(3, to.getName());
			pstmt.setString(4, to.getMail());
			pstmt.setString(5, to.getPw());
			pstmt.setString(6, to.getPw_confirm());
			
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
	
	public int login( signUpTO to ) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		// 비정상 >= 1, 정상 : 0;
		int flag = 1;
		
		try {
			
			conn = this.dataSource.getConnection();
			
			String sql = "select id from signUp where id = ? and pw=? ";
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getId());
			pstmt.setString(2, to.getPw());
			
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
}
