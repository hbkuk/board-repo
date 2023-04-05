import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginConnection {
	
	private Connection conn;
	
	public LoginConnection() {
		
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
	
	public boolean isCno(int cno) {
		Statement st = null;
		ResultSet rs = null;
		
		try {
			String sql =  "select * from customer where cno =" + cno;
			st = this.conn.createStatement();
			
			rs = st.executeQuery(sql);
			if( rs.next()) {
				return true;
			}
		}
		catch(Exception e) {
			
			System.out.println( "DB 검색 오류" + e.getMessage() );
		}
		return false;
	}
	
	public boolean isCheckIn(int cno) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql =  "select * from check_in where cno = ? and date_format(checkin, '%Y-%m-%d') = date_format(now(), '%Y-%m-%d')";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, cno);
			
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
	
	public void checkIn(int cno) {
		
		PreparedStatement pstmt = null;
		
		try {	
			String sql = "insert into check_in (cno) values (?)";
			
			pstmt = conn.prepareStatement( sql );
			pstmt.setInt( 1, cno );
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( conn != null ) try { conn.close(); } catch( SQLException e1 ) {}
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e1 ) {}
		}
	}
	
	public void checkOut(int cno) {
		
		PreparedStatement pstmt = null;
		
		try {	
			String sql = "insert into check_out (cno) values (?)";
			pstmt = conn.prepareStatement( sql );
			pstmt.setInt( 1, cno );
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( conn != null ) try { conn.close(); } catch( SQLException e1 ) {}
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e1 ) {}
		}
	}
	
	public String checkInName( int cno) {
		String name ="";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			String sql =  "select cname from customer where cno = ?";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, cno);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				name = rs.getString("cname");
			}
			
		}
		catch(Exception e) {
			
			System.out.println( "DB 검색 오류" + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch( SQLException e1 ) {}
			if( conn != null ) try { conn.close(); } catch( SQLException e1 ) {}
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e1 ) {}
		
		}
			return name;
	}
}
