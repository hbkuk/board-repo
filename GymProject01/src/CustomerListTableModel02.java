import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class CustomerListTableModel02 extends AbstractTableModel {
	private ArrayList<CustomerListTO> customers;
	private String[] columnNames = new String[] { "회원번호", "성명", "성별", "생년월일", "등록일자", "회원권 번호", "매니저번호" };
	
	public CustomerListTableModel02() {
		// TODO Auto-generated constructor stub
		CustomerListDAO dao = new CustomerListDAO();
		customers = dao.fullSearchCustomers();
	}


	public CustomerListTableModel02(String cname) {
		// TODO Auto-generated constructor stub
		CustomerListDAO dao = new CustomerListDAO();
		customers = dao.searchCustomers( cname );
	}
	
	public CustomerListTableModel02(int currentMonth) {
		// TODO Auto-generated constructor stub
		CustomerListDAO dao = new CustomerListDAO();
		customers = dao.searchMonthNewCustomers( currentMonth );
	}
	
	public CustomerListTableModel02(int a, int b) {
		// TODO Auto-generated constructor stub
		CustomerListDAO dao = new CustomerListDAO();
		customers = dao.MembershipDeadline();
	}
	
	public CustomerListTableModel02(String a, int b) {
		// TODO Auto-generated constructor stub
		CustomerListDAO dao = new CustomerListDAO();
		customers = dao.CurrentCustomer();
	}
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columnNames[column];
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return customers.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 6;
	}

	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		CustomerListTO to = customers.get( rowIndex );
		
		String result = "";
		switch( columnIndex ) {
		case 0:
			result = to.getCno();
			break;
		case 1:
			result = to.getCname();
			break;
		case 2:
			result = to.getCsex();
			break;
		case 3:
			result = to.getCbday();
			break;
		case 4:
			result = to.getCrgday();
			break;
		case 5:
			result = to.getMtno();
			break;
		case 6:
			result = to.getMgno();
			break;
		}
		return result;
	}
}
	
//	public Object getZipcode1(int rowIndex) {
//		// TODO Auto-generated method stub
//		CustomerListTO to = customers.get( rowIndex );
//		
//		String result = String.format( "[%s]", to.getZipcode());
//		
//		return result;
//	}
//	
//	public Object getZipcode2(int rowIndex) {
//		// TODO Auto-generated method stub
//		SidoGugunDongTO to = zipcodes.get( rowIndex );
//		
//		String result = String.format( "%s %s %s", to.getSido(), to.getGugun(), to.getDong());
//		
//		return result;
//	}
//	
//	public Object getZipcode3(int rowIndex) {
//		// TODO Auto-generated method stub
//		SidoGugunDongTO to = zipcodes.get( rowIndex );
//		
//		String result = String.format( "%s %s", to.getRi(), to.getBunji());
//		
//		return result;
//	}
//}
