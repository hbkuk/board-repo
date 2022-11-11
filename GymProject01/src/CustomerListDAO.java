import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CustomerListDAO {
	
	private Connection conn;
	
	public CustomerListDAO() {
		
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
	
	public ArrayList<CustomerListTO> fullSearchCustomers() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 2차 배열
		ArrayList<CustomerListTO> resultArray = new ArrayList<>();
		try {
			String sql = "select * from customer ";
			pstmt = this.conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				CustomerListTO to = new CustomerListTO();
				
				to.setCno( rs.getString( "cno" ) );
				to.setCname( rs.getString( "cname" ) );
				to.setCsex( rs.getString( "csex" ) );
				to.setCbday( rs.getString( "cbday" ) );
				to.setCrgday( rs.getString( "crgday" ) );
				to.setMtno( rs.getString( "mtno" ) );
				to.setMgno( rs.getString( "mgno" ) );

				resultArray.add(to);
				
			}
		} catch (SQLException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch( SQLException e1 ) {}
			if( conn != null ) try { conn.close(); } catch( SQLException e1 ) {}
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e1 ) {}
		}
		return resultArray;
	}
	
	public ArrayList<CustomerListTO> searchCustomers(String cname) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 2차 배열
		ArrayList<CustomerListTO> resultArray = new ArrayList<>();
		try {
			String sql = "select * from customer where cname like ?";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, cname + "%");
			
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				CustomerListTO to = new CustomerListTO();
				
				to.setCno( rs.getString( "cno" ) );
				to.setCname( rs.getString( "cname" ) );
				to.setCsex( rs.getString( "csex" ) );
				to.setCbday( rs.getString( "cbday" ) );
				to.setCrgday( rs.getString( "crgday" ) );
				to.setMtno( rs.getString( "mtno" ) );
				to.setMgno( rs.getString( "mgno" ) );

				resultArray.add(to);
				
			}
		} catch (SQLException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch( SQLException e1 ) {}
			if( conn != null ) try { conn.close(); } catch( SQLException e1 ) {}
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e1 ) {}
		}
		return resultArray;
	}
	
	public ArrayList<CustomerListTO> searchSexCustomers(int csex) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 2차 배열
		ArrayList<CustomerListTO> resultArray = new ArrayList<>();
		try {
			String sql = "select * from customer where csex = ?";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, csex);
			
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				CustomerListTO to = new CustomerListTO();
				
				to.setCno( rs.getString( "cno" ) );
				to.setCname( rs.getString( "cname" ) );
				to.setCsex( rs.getString( "csex" ) );
				to.setCbday( rs.getString( "cbday" ) );
				to.setCrgday( rs.getString( "crgday" ) );
				to.setMtno( rs.getString( "mtno" ) );
				to.setMgno( rs.getString( "mgno" ) );

				resultArray.add(to);
				
			}
		} catch (SQLException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch( SQLException e1 ) {}
			if( conn != null ) try { conn.close(); } catch( SQLException e1 ) {}
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e1 ) {}
		}
		return resultArray;
	}
	
	public ArrayList<CustomerListTO> searchMonthNewCustomers(int currentMonth) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 2차 배열
		ArrayList<CustomerListTO> resultArray = new ArrayList<>();
		try {
			String sql = "select * from customer where crgday like ?";
			
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1,  "%-" + currentMonth + "-%");
			
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				CustomerListTO to = new CustomerListTO();
				
				to.setCno( rs.getString( "cno" ) );
				to.setCname( rs.getString( "cname" ) );
				to.setCsex( rs.getString( "csex" ) );
				to.setCbday( rs.getString( "cbday" ) );
				to.setCrgday( rs.getString( "crgday" ) );
				to.setMtno( rs.getString( "mtno" ) );
				to.setMgno( rs.getString( "mgno" ) );

				resultArray.add(to);
				
			}
		} catch (SQLException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch( SQLException e1 ) {}
			if( conn != null ) try { conn.close(); } catch( SQLException e1 ) {}
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e1 ) {}
		}
		return resultArray;
	}
	
	public ArrayList<CustomerListTO> MembershipDeadline( ) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<CustomerListTO> resultArray = new ArrayList<>();
		try {		
			String sql = "select c.cno, c.cname, c.csex, c.cbday, c.crgday, m.mtno, c.mgno "
					+ "from customer c inner join membership m "
					+ "on (c.mtno = m.mtno) "
					+ "where DATE_ADD( DATE_ADD( c.crgday, INTERVAL (m.mmonth) month ), INTERVAL (-10) day )  < ? "
					+ "and DATE_ADD( c.crgday, INTERVAL (m.mmonth) month ) > ?";
			
			pstmt = this.conn.prepareStatement(sql);
			LocalDate curDate = LocalDate.now();
			pstmt.setString(1, curDate.toString());
			pstmt.setString(2, curDate.toString());
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				
				CustomerListTO to = new CustomerListTO();
				to.setCno( rs.getString( "cno" ) );
				to.setCname( rs.getString( "cname" ) );
				to.setCsex( rs.getString( "csex" ) );
				to.setCbday( rs.getString( "cbday" ) );
				to.setCrgday( rs.getString( "crgday" ) );
				to.setMtno( rs.getString( "mtno" ) );
				to.setMgno( rs.getString( "mgno" ) );
				
				resultArray.add(to);
				
			}
		} catch (SQLException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch( SQLException e1 ) {}
			if( conn != null ) try { conn.close(); } catch( SQLException e1 ) {}
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e1 ) {}
		}
		return resultArray;
	}
	
	public ArrayList<CustomerListTO> CurrentCustomer( ) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<CustomerListTO> resultArray = new ArrayList<>();
		try {		
			String sql = "select c.cno, c.cname, c.csex, c.cbday, c.crgday, m.mtno, c.mgno "
					+ "from check_in i left outer join check_out o "
					+ "on (i.cno = o.cno) inner join customer c "
					+ "on (i.cno = c.cno) inner join membership m "
					+ "on (c.mtno = m.mtno) "
					+ "where checkin like ? and checkout is null "
					+ "order by checkin desc";
			
			pstmt = this.conn.prepareStatement(sql);
			LocalDate curDate = LocalDate.now();
			pstmt.setString(1, curDate.toString() + "%");
			
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				
				CustomerListTO to = new CustomerListTO();
				to.setCno( rs.getString( "cno" ) );
				to.setCname( rs.getString( "cname" ) );
				to.setCsex( rs.getString( "csex" ) );
				to.setCbday( rs.getString( "cbday" ) );
				to.setCrgday( rs.getString( "crgday" ) );
				to.setMtno( rs.getString( "mtno" ) );
				to.setMgno( rs.getString( "mgno" ) );
				
				resultArray.add(to);
			}
			
		} catch (SQLException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch( SQLException e1 ) {}
			if( conn != null ) try { conn.close(); } catch( SQLException e1 ) {}
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e1 ) {}
		}
		return resultArray;
	}
	
	public static void main(String[] args) {
		CustomerListDAO test = new CustomerListDAO();
		test.CurrentCustomer();
	}

}
