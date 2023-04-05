import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerPanelDAO {
	
	private Connection conn;
	
	public CustomerPanelDAO() {
		
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
	
	public void NewCustomer(String cname, int csex, String cbday, String crgday, int mtno, int mgno ) {
		PreparedStatement pstmt = null;
		try {		
			String sql = "insert into customer (cname, csex, cbday, crgday, mtno, mgno) values ( ?, ?, STR_TO_DATE(?,'%d-%m-%Y'), STR_TO_DATE(?,'%d-%m-%Y'), ?, ?)";
					
			pstmt = this.conn.prepareStatement(sql);
			
			pstmt.setString( 1,  cname );
			pstmt.setInt( 2,  csex );
			pstmt.setString( 3,  cbday );
			pstmt.setString( 4,  crgday );
			pstmt.setInt( 5,  mtno );
			pstmt.setInt( 6,  mgno );
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( conn != null ) try { conn.close(); } catch( SQLException e1 ) {}
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e1 ) {}
		}
	}
	
	public static void main(String[] args) {
		CustomerPanelDAO CustomerDAO = new CustomerPanelDAO();
		CustomerDAO.NewCustomer("황병국", 1, "28-08-1997", "10-11-2022", 1, 1 );
	}

}
