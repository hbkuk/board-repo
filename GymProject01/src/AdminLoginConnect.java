import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminLoginConnect {

	private Connection conn;
	
	public AdminLoginConnect() {
		
		String url = "jdbc:mysql://localhost:3306/project1";
		String user = "project1";
		String password = "1234";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			this.conn = DriverManager.getConnection(url, user, password);
			
			
		} catch (ClassNotFoundException e) {
			System.out.println( "[에러] " + e.getMessage() );
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println( "[에러] " + e.getMessage() );
		}
		
	}
	
	public boolean isadmin(String adminid, String adminpassword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql =  "select * from admin "
						+ "where adminid = ? and adminpassword = ?";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, adminid);
			pstmt.setString(2, adminpassword);
			
			rs=pstmt.executeQuery();
			if( rs.next()) {
				return true;
			}
		}
		catch(Exception e) {
			
			System.out.println( "DB 검색 오류" + e.getMessage() );
		}
		return false;
	}
	
	public boolean isadminID(String adminid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql =  "select * from admin "
						+ "where adminid = ?";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, adminid);
			
			rs=pstmt.executeQuery();
			if( rs.next()) {
				return true;
			}
		}
		catch(Exception e) {
			
			System.out.println( "DB 검색 오류" + e.getMessage() );
		}
		return false;
	}
	
	
	public void createAdaminAccount(String adminid, String adminpassword ) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "insert into admin (adminid, adminpassword) values (?, ?)";
			
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, adminid );
			pstmt.setString( 2, adminpassword );
			pstmt.executeUpdate();
			
			} catch (SQLException e) {
				System.out.println( "[에러] " + e.getMessage() );
			} finally {
				if( conn != null ) try { conn.close(); } catch( SQLException e1 ) {}
				if( pstmt != null ) try { pstmt.close(); } catch( SQLException e1 ) {}
			}
		
	}
}
