import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MainPanelDAO {
	
	private Connection conn;
	
	// 데이터베이스 접속
	public MainPanelDAO() {
		
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
	
	public ArrayList<String> CurrentCustomer( ) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<String> resultArray = new ArrayList<>();
		try {		
			String sql = "select i.cno, c.cname, date_format(i.checkin,'%H시 %i분에 입장하셨습니다.') '입장시간' "
					+ "from check_in i left outer join check_out o "
					+ "on (i.cno = o.cno) inner join customer c "
					+ "on (i.cno = c.cno) "
					+ "where checkin like ? and checkout is null "
					+ "order by checkin desc";
			
			pstmt = this.conn.prepareStatement(sql);
			LocalDate curDate = LocalDate.now();
			pstmt.setString(1, curDate.toString() + "%");
			
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				String cno = rs.getString( "cno" );
				String cname = rs.getString( "cname" );
				String checkin = rs.getString( "입장시간" );
				
				String result = String.format("회원번호 %s | %s %s", rs.getString(1), rs.getString(2), rs.getString(3));
				
				resultArray.add(result);
				
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
	
	public ArrayList<String> CurrentCustomerNumber( ) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<String> resultArray = new ArrayList<>();
		try {		
			String sql = "select sum(if(csex=1,1,0)) '남', sum(if(csex=0,1,0)) '여' from check_in i left outer join check_out o on (i.cno = o.cno) inner join customer c on (i.cno = c.cno) where checkin like ? and checkout is null";
			
			pstmt = this.conn.prepareStatement(sql);
			LocalDate curDate = LocalDate.now();
			pstmt.setString(1, curDate.toString() + "%");
			
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				String man = rs.getString( "남" );
				String wo = rs.getString( "여" );
				
				String result = String.format("남자: %s, 여자: %s", rs.getString(1), rs.getString(2));
				
				resultArray.add(result);
				
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
	
	public ArrayList<String> NewCustomerNumber( ) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<String> resultArray = new ArrayList<>();
		try {		
			String sql = "select sum( if( csex = 1, 1, 0) ) '남', sum( if( csex = 0, 1, 0) ) '여' from customer c inner join membership m on ( c.mtno = m.mtno ) where c.crgday like ?";
			
			pstmt = this.conn.prepareStatement(sql);
			LocalDate curDate = LocalDate.now();
			pstmt.setString(1, curDate.toString() + "%");
			
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				String man = rs.getString( "남" );
				String wo = rs.getString( "여" );
				
				String result = String.format("남자: %s, 여자: %s", rs.getString(1), rs.getString(2));
				
				resultArray.add(result);
				
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
	
	public ArrayList<String> NewCustomerInfo( ) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<String> resultArray = new ArrayList<>();
		try {		
			String sql = "select  c.cno, c.cname, if(c.csex > 0, '남', '여') 'csex', c.cbday, m.mmonth from customer c inner join membership m on ( c.mtno = m.mtno ) where c.crgday like ?";
			
			pstmt = this.conn.prepareStatement(sql);
			LocalDate curDate = LocalDate.now();
			pstmt.setString(1, curDate.toString() + "%");
			
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				String cno = rs.getString( "cno" );
				String cname = rs.getString( "cname" );
				String csex = rs.getString( "csex" );
				String cbday = rs.getString( "cbday" );
				String mmonth = rs.getString( "mmonth" );
				
				String result = String.format("[%s] | %s | %s | %s | %s개월",
						rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				
				resultArray.add(result);
				
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
	
	public ArrayList<String> MembershipDeadline( ) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<String> resultArray = new ArrayList<>();
		try {		
			String sql = "select c.cno, c.cname, c.crgday '등록한 날짜', m.mmonth '등록한 월수',   DATE_ADD( c.crgday, INTERVAL (m.mmonth) month ) '마감일' "
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
				String cno = rs.getString( "cno" );
				String cname = rs.getString( "cname" );
				String crgday = rs.getString( "crgday" );
				String mmonth = rs.getString( "mmonth" );
				String deadLine = rs.getString( "마감일" );
				
				String result = String.format("[%s] | %s | %s | %s | %s",
						rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				
				resultArray.add(result);
				
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
	
	
	public ArrayList<String> MembershipDeadlineNumber( ) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<String> resultArray = new ArrayList<>();
		try {		
			String sql = "select sum( if( csex = 1, 1, 0) ) '남', sum( if( csex = 0, 1, 0) ) '여' "
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
				String man = rs.getString( "남" );
				String wo = rs.getString( "여" );
				pstmt.setString(1, curDate.toString());
				pstmt.setString(2, curDate.toString());
				
				String result = String.format("남자: %s, 여자: %s",
						rs.getString(1), rs.getString(2) );
				
				resultArray.add(result);
				
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
	
	public ArrayList<String> TodayReservation( ) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<String> resultArray = new ArrayList<>();
		try {		
			String sql = "select date_format(ptdate,'%H:%i') 'ptdate', cname, mname "
					+ "from reserv r inner join customer c "
					+ "on (r.cno = c.cno) inner join Manager m "
					+ "on( c.mgno = m.mgno) "
					+ "where ptdate > now() and   ptdate < DATE_ADD( now() , INTERVAL (1) day ) "
					+ "order by ptdate;";
			
			pstmt = this.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				String ptdate = rs.getString( "ptdate" );
				String cname = rs.getString( "cname" );
				String mname = rs.getString( "mname" );
				
				String result = String.format("%s | %s | %s ",
						rs.getString(1), rs.getString(2), rs.getString(3) );
				
				resultArray.add(result);
				
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
	
	public ArrayList<String> TodayReservationNumber( ) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<String> resultArray = new ArrayList<>();
		try {		
			String sql = "select sum( if( csex = 1, 1, 0) ) '남', sum( if( csex = 0, 1, 0) ) '여' "
					+ "from reserv r inner join customer c "
					+ "on (r.cno = c.cno) inner join Manager m "
					+ "on( c.mgno = m.mgno) "
					+ "where ptdate > now() and ptdate < DATE_ADD( now() , INTERVAL (1) day ) ";
			
			pstmt = this.conn.prepareStatement(sql);
			LocalDate curDate = LocalDate.now();
			
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				String man = rs.getString( "남" );
				String wo = rs.getString( "여" );
				
				String result = String.format("남자: %s, 여자: %s",
						rs.getString(1), rs.getString(2) );
				
				resultArray.add(result);
				
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
	
	public void CreateNoticeBoard(String title, String content, String adminid ) {
		PreparedStatement pstmt = null;
		try {		
			String sql = "insert into notice_board (title, content, adminid) values(?, ?, ?)";
			
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString( 1, title );
			pstmt.setString( 2, content );
			pstmt.setString( 3, adminid );
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( conn != null ) try { conn.close(); } catch( SQLException e1 ) {}
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e1 ) {}
		}
	}
	
	public ArrayList<String> ShowNoticeBoard( ) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<String> resultArray = new ArrayList<>();
		try {		
			String sql = "select bno, title, content, date_format(bdate, '%H시 %i분') 'bdate', adminid "
					+ "from notice_board order by bdate desc";
			
			pstmt = this.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				String bno = rs.getString( "bno" );
				String title = rs.getString( "title" );
				String bdate = rs.getString( "bdate" );
				String adminid = rs.getString( "adminid" );
				
				String result = String.format("%s %s %s %s ",
						rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				
				resultArray.add(result);
				
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
	
	
	
//	public static void main(String[] args) {
//		MainPannelDAO dao = new MainPannelDAO();
//		ArrayList<String> MembershipDeadlineNumber = dao.MembershipDeadlineNumber();
//		for( String Customer : MembershipDeadlineNumber ) {
//			System.out.println(Customer);
//			}
//	}
}

