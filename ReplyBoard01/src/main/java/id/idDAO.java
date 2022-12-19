package id;

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

public class idDAO {
	private DataSource dataSource;
	
	public idDAO() {
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
	
	public int hasId( String id ) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 비정상 >= 1, 정상 : 0;
		int flag = 1;
		
		try {
			
			conn = this.dataSource.getConnection();
			
			String sql = "select m_id from member where m_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id );
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
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
