import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class UI extends JFrame {

	private JPanel contentPane;
	private JPasswordField pf1;
	private JPanel Main_Panel;
	private JTextField tf1;
	private JTextField tf2;
	private JTextField tf3;
	private JTextField tf4;
	private JTextArea ta1;
	private JTextArea ta5;
	private JTextArea ta2;
	private JTextArea ta3;
	private JTextArea ta4;
	private JPanel C_Logout_suc_Panel;
	String currentAdminID;
	int exitNumber;
	private JTable table;
	private JTextField searchTf1;
	private JPanel Customer_Panel;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_1_1;
	private JLabel lbl_currentAdminID;
	private JButton btnNewButton_4;
	private JButton btnNewButton_1_3;
	private JButton btnNewButton_1_1_2;
	private JPanel C_Login_Suc_Panel;
	LocalDate now = LocalDate.now();
	private JLabel lblNewLabel_2_1_1_1;
	private JLabel lblNewLabel_2_1_1_1_1;
	private JLabel lblNewLabel_1_1;
	private JLabel lblNewLabel_1;

	/*
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI frame = new UI();
					frame.setTitle("GYM");
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds( 100, 100, 1100, 600 );
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon icon01 = new ImageIcon("Image/Main_LOGO.jpg");
		Image img01 = icon01.getImage();
		Image changeImg01 = img01.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		
		ImageIcon icon02 = new ImageIcon("Image/checkImage.png");
		Image img02 = icon02.getImage();
		Image changeImg02 = img02.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		JPanel Login_Panel = new JPanel();
		Login_Panel.setForeground(new Color(255, 255, 255));
		Login_Panel.setBackground(new Color(0, 51, 153));
		Login_Panel.setBounds(0, 0, 1084, 561);
		contentPane.add(Login_Panel);
		Login_Panel.setLayout(null);
		
		JLabel lbl_title = new JLabel("Strong GYM");
		lbl_title.setForeground(new Color(255, 255, 255));
		lbl_title.setBackground(new Color(255, 255, 255));
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_title.setFont(new Font("Bahnschrift", Font.BOLD, 81));
		lbl_title.setBounds(266 , 60, 552, 104);
		Login_Panel.add(lbl_title);
		
		JLabel lbl_coment = new JLabel("입장 및 퇴실 시 회원번호를 입력하세요.");
		lbl_coment.setBackground(Color.WHITE);
		lbl_coment.setForeground(Color.WHITE);
		lbl_coment.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 30));
		lbl_coment.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_coment.setBounds(266 , 192, 552, 55);
		Login_Panel.add(lbl_coment);
		
		pf1 = new JPasswordField();
		pf1.setFont(new Font("굴림", Font.PLAIN, 80));
		pf1.setHorizontalAlignment(SwingConstants.CENTER);
		pf1.setBounds(264 , 306, 552, 55);
		Login_Panel.add(pf1);
		
		JButton btn_login = new JButton("Click");
		btn_login.setForeground(new Color(255, 255, 255));
		btn_login.setBackground(new Color(0, 51, 153));
		btn_login.addMouseListener(new MouseAdapter() {
			private AbstractButton lblNewLabel_1_1_1;

			@Override
			public void mouseClicked(MouseEvent e) {
				String pf1Value =  pf1.getText(); 
				
				LoginConnection login = new LoginConnection();
				
				// 숫자 형식 and 정말 회원번호가 있는지 확인.
			if( pf1Value.matches( "[0-9]+") && login.isCno(Integer.valueOf(pf1Value)) ) {
				
				// 이 회원번호가 현재 체크인 상태인지.
				if( login.isCheckIn( Integer.valueOf(pf1Value) )) {
					
					Login_Panel.setVisible(false);
					C_Logout_suc_Panel.setVisible(true);
					
					LoginConnection login1 = new LoginConnection();
					lblNewLabel_2_1_1_1.setText(login1.checkInName(Integer.valueOf(pf1Value))+"님");
					lblNewLabel_1_1.setIcon(new ImageIcon(changeImg02));
					login.checkOut(Integer.valueOf(pf1Value));
					
					Timer timer = new Timer(2000, new ActionListener() {
						  @Override
						  public void actionPerformed(ActionEvent arg0) {
							  
							  C_Login_Suc_Panel.setVisible(false);
							  Login_Panel.setVisible(true);
							  pf1.setText("");
						  }
						});
						timer.setRepeats(false); // Only execute once
						timer.start(); // Go go go!
					
				} else {
					Login_Panel.setVisible(false);
					C_Login_Suc_Panel.setVisible(true);
					LoginConnection login2 = new LoginConnection();
					lblNewLabel_2_1_1_1_1.setText(login2.checkInName(Integer.valueOf(pf1Value))+"님");
					lblNewLabel_1_1_1.setIcon(new ImageIcon(changeImg02));
					login.checkIn(Integer.valueOf(pf1Value));
					// 일정 시간 뒤에 실행
					Timer timer = new Timer(2000, new ActionListener() {
						  @Override
						  public void actionPerformed(ActionEvent arg0) {
							  
							  C_Login_Suc_Panel.setVisible(false);
							  Login_Panel.setVisible(true);
							  pf1.setText("");
						  }
						});
						timer.setRepeats(false); // Only execute once
						timer.start(); // Go go go!
					}
				} else {
					JOptionPane.showMessageDialog( null, "로그인 실패", "로그인 실패", JOptionPane.ERROR_MESSAGE);
				}
			}
	});
		
		btn_login.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
		btn_login.setBounds(263 , 382, 554, 46);
		Login_Panel.add(btn_login);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(0, 281, 1084, 280);
		Login_Panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lbl_manager = new JLabel("관리자 로그인");
		lbl_manager.setBounds(263, 208, 552, 46);
		panel_3.add(lbl_manager);
		lbl_manager.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				admin_Login_Field_DIalog dialog = new admin_Login_Field_DIalog();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setResizable(false);
				dialog.setModal(true);
				dialog.setVisible(true);
				
				if( dialog.LoginNumber == 1) {
					currentAdminID = dialog.currentAdminID;
					lbl_currentAdminID.setText(dialog.currentAdminID + "님 반갑습니다.");
					Login_Panel.setVisible(false);
					Main_Panel.setVisible(true);
					
					MainPanelDAO dao = new MainPanelDAO();
					ArrayList<String> currentCustomer = dao.CurrentCustomer();
					for( String Customer : currentCustomer ) {
						ta1.append(Customer + "\n");
						}
					
					MainPanelDAO dao1 = new MainPanelDAO();
					ArrayList<String> CurrentCustomerNumber = dao1.CurrentCustomerNumber();
					for( String Customer : CurrentCustomerNumber ) {
						tf1.setText(Customer);
						}
					
					MainPanelDAO dao2 = new MainPanelDAO();
					ArrayList<String> NewCustomerNumber = dao2.NewCustomerNumber();
					for( String Customer : NewCustomerNumber ) {
						tf2.setText(Customer);
						}
					
					MainPanelDAO dao3 = new MainPanelDAO();
					ArrayList<String> NewCustomerInfo = dao3.NewCustomerInfo();
					for( String Customer : NewCustomerInfo ) {
						ta2.append(Customer + "\n");
						}
					
					MainPanelDAO dao4 = new MainPanelDAO();
					ArrayList<String> MembershipDeadline = dao4.MembershipDeadline();
					for( String Customer : MembershipDeadline ) {
						ta3.append(Customer + "\n");
						}
					
					MainPanelDAO dao5 = new MainPanelDAO();
					ArrayList<String> MembershipDeadlineNumber = dao5.MembershipDeadlineNumber();
					for( String Customer : MembershipDeadlineNumber ) {
						tf3.setText(Customer);
						}
					
					MainPanelDAO dao6 = new MainPanelDAO();
					ArrayList<String> TodayReservation = dao6.TodayReservation();
					for( String Customer : TodayReservation ) {
						ta4.append(Customer + "\n");
						}
					
					MainPanelDAO dao7 = new MainPanelDAO();
					ArrayList<String> TodayReservationNumber = dao7.TodayReservationNumber();
					for( String Customer : TodayReservationNumber ) {
						tf4.setText(Customer);
						}
					
					MainPanelDAO dao8 = new MainPanelDAO();
					ArrayList<String> ShowNoticeBoard = dao8.ShowNoticeBoard();
					for( String Customer : ShowNoticeBoard ) {
						ta5.append(Customer + "\n");
						}
				}
			}
		});
		lbl_manager.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_manager.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 16));
		
		JLabel timeLabel = new JLabel("New label");
		timeLabel.setText( now.toString());
		timeLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		timeLabel.setForeground(new Color(255, 255, 255));
		timeLabel.setBounds(885, 10, 187, 39);
		Login_Panel.add(timeLabel);
		
		Main_Panel = new JPanel();
		Main_Panel.setBackground(Color.WHITE);
		Main_Panel.setBounds(0, 0, 1084, 561);
		contentPane.add(Main_Panel);
		Main_Panel.setLayout(null);
		Main_Panel.setVisible(false);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(changeImg01));
		lblNewLabel.setBounds(11, 26, 89, 72);
		
		Main_Panel.add(lblNewLabel);
		
		btnNewButton = new JButton("회원 관리");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 51, 153));
		btnNewButton.setFont(new Font("한컴산뜻돋움", Font.BOLD, 50));
		btnNewButton.setBounds(523, 25, 411, 73);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main_Panel.setVisible(false);
				Customer_Panel.setVisible(true);
				
			}
		});
		btnNewButton.setFont(new Font("한컴산뜻돋움", Font.BOLD, 50));
		btnNewButton.setBounds(109, 25, 402, 73);
		Main_Panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("일정관리\r\n");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(0, 51, 153));
		btnNewButton_1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 50));
		btnNewButton_1.setBounds(523, 25, 411, 73);
		Main_Panel.add(btnNewButton_1);
		
		btnNewButton_1_1 = new JButton("EXIT");
		btnNewButton_1_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1_1.setBackground(new Color(0, 51, 153));
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ExitConfirmDialog dialog = new ExitConfirmDialog();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setModal(true);
				dialog.setVisible(true);
				if ( dialog.exitNumber == 1) {
					System.exit(exitNumber);
				}
			}
		});
		btnNewButton_1_1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 40));
		btnNewButton_1_1.setBounds(946, 25, 126, 73);
		Main_Panel.add(btnNewButton_1_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 140, 331, 30);
		Main_Panel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("현재 운동중인 회원");
		lblNewLabel_3.setBackground(new Color(240, 255, 255));
		lblNewLabel_3.setForeground(new Color(0, 51, 153));
		lblNewLabel_3.setBounds(0, 0, 331, 30);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		
		tf1 = new JTextField();
		tf1.setEditable(false);
		tf1.setFont(new Font("굴림", Font.PLAIN, 13));
		tf1.setForeground(new Color(0, 0, 0));
		tf1.setBackground(new Color(255, 255, 255));
		tf1.setHorizontalAlignment(SwingConstants.CENTER);
		tf1.setText("남 00명 / 여 00명");
		tf1.setBounds(12, 180, 331, 21);
		Main_Panel.add(tf1);
		tf1.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 211, 331, 94);
		Main_Panel.add(scrollPane);
		
		ta1 = new JTextArea();
		ta1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		scrollPane.setViewportView(ta1);
		ta1.setTabSize(20);
		ta1.setEditable(false);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(12, 333, 331, 30);
		Main_Panel.add(panel_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("오늘 신규 등록한 회원");
		lblNewLabel_3_1.setForeground(new Color(0, 51, 153));
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		lblNewLabel_3_1.setBounds(0, 0, 331, 30);
		panel_1.add(lblNewLabel_3_1);
		
		tf2 = new JTextField();
		tf2.setFont(new Font("굴림", Font.PLAIN, 13));
		tf2.setBackground(new Color(255, 255, 255));
		tf2.setText("남 00명 / 여 00명");
		tf2.setHorizontalAlignment(SwingConstants.CENTER);
		tf2.setEditable(false);
		tf2.setColumns(10);
		tf2.setBounds(12, 373, 331, 21);
		Main_Panel.add(tf2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(14, 404, 329, 92);
		Main_Panel.add(scrollPane_1);
		
		ta2 = new JTextArea();
		scrollPane_1.setViewportView(ta2);
		ta2.setTabSize(20);
		ta2.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		ta2.setEditable(false);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBounds(376, 333, 331, 30);
		Main_Panel.add(panel_1_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("재등록 대상 회원");
		lblNewLabel_3_1_1.setForeground(new Color(0, 51, 153));
		lblNewLabel_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		lblNewLabel_3_1_1.setBounds(0, 0, 331, 30);
		panel_1_1.add(lblNewLabel_3_1_1);
		
		tf3 = new JTextField();
		tf3.setFont(new Font("굴림", Font.PLAIN, 13));
		tf3.setForeground(new Color(0, 0, 0));
		tf3.setBackground(new Color(255, 255, 255));
		tf3.setText("남 00명 / 여 00명");
		tf3.setHorizontalAlignment(SwingConstants.CENTER);
		tf3.setEditable(false);
		tf3.setColumns(10);
		tf3.setBounds(376, 373, 331, 21);
		Main_Panel.add(tf3);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(376, 404, 331, 90);
		Main_Panel.add(scrollPane_2);
		
		ta3 = new JTextArea();
		scrollPane_2.setViewportView(ta3);
		ta3.setTabSize(20);
		ta3.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		ta3.setEditable(false);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(376, 140, 680, 30);
		Main_Panel.add(panel_2);
		
		JLabel lblNewLabel_3_2 = new JLabel("전체 공지");
		lblNewLabel_3_2.setForeground(new Color(0, 51, 153));
		lblNewLabel_3_2.setBackground(new Color(255, 255, 255));
		lblNewLabel_3_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_2.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		lblNewLabel_3_2.setBounds(0, 0, 680, 30);
		panel_2.add(lblNewLabel_3_2);
		
		JButton btnNewButton_2 = new JButton("글쓰기");
		btnNewButton_2.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 13));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				createNoticeBoardDialog dialog = new createNoticeBoardDialog(currentAdminID);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setModal(true);
				dialog.setVisible(true);
			}
		});
		btnNewButton_2.setBackground(new Color(0, 51, 153));
		btnNewButton_2.setBounds(539, 0, 141, 29);
		panel_2.add(btnNewButton_2);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(376, 180, 680, 125);
		Main_Panel.add(scrollPane_3);
		
		ta5 = new JTextArea();
		scrollPane_3.setViewportView(ta5);
		ta5.setTabSize(20);
		ta5.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 11));
		ta5.setEditable(false);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setLayout(null);
		panel_1_1_1.setBounds(737, 333, 319, 30);
		Main_Panel.add(panel_1_1_1);
		
		JLabel lblNewLabel_3_1_1_1 = new JLabel("오늘의 일정");
		lblNewLabel_3_1_1_1.setForeground(new Color(0, 51, 153));
		lblNewLabel_3_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_1_1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		lblNewLabel_3_1_1_1.setBounds(0, 0, 319, 30);
		panel_1_1_1.add(lblNewLabel_3_1_1_1);
		
		tf4 = new JTextField();
		tf4.setFont(new Font("굴림", Font.PLAIN, 13));
		tf4.setForeground(new Color(0, 0, 0));
		tf4.setBackground(new Color(255, 255, 255));
		tf4.setText("남 00명 / 여 00명");
		tf4.setHorizontalAlignment(SwingConstants.CENTER);
		tf4.setEditable(false);
		tf4.setColumns(10);
		tf4.setBounds(737, 373, 319, 21);
		Main_Panel.add(tf4);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(737, 403, 319, 88);
		Main_Panel.add(scrollPane_4);
		
		ta4 = new JTextArea();
		scrollPane_4.setViewportView(ta4);
		ta4.setTabSize(20);
		ta4.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 12));
		ta4.setEditable(false);
		
		lbl_currentAdminID = new JLabel("adminid");
		lbl_currentAdminID.setFont(new Font("굴림체", Font.BOLD, 15));
		lbl_currentAdminID.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_currentAdminID.setBounds(798, 107, 258, 23);
		Main_Panel.add(lbl_currentAdminID);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(0, 51, 153));
		panel_5.setBounds(0, 520, 1083, 40);
		Main_Panel.add(panel_5);
		Customer_Panel = new JPanel();
		Customer_Panel.setBackground(new Color(255, 255, 255));
		Customer_Panel.setBounds(0, 0, 1084, 561);
		contentPane.add(Customer_Panel);
		Customer_Panel.setLayout(null);
		Customer_Panel.setVisible(false);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBounds(12, 0, 100, 100);
		Customer_Panel.add(lblNewLabel_4);
		
		
		
		
		
		btnNewButton_4 = new JButton("회원 관리");
		btnNewButton_4.setForeground(new Color(255, 255, 255));
		btnNewButton_4.setBackground(new Color(0, 51, 153));
		btnNewButton_4.setFont(new Font("한컴산뜻돋움", Font.BOLD, 50));
		btnNewButton_4.setBounds(523, 25, 411, 73);
		btnNewButton_4.setFont(new Font("한컴산뜻돋움", Font.BOLD, 50));
		btnNewButton_4.setBounds(109, 25, 402, 73);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Customer_Panel.setVisible(true);
			}
		});
		Customer_Panel.add(btnNewButton_4);
		btnNewButton_1_3 = new JButton("일정관리\r\n");
		btnNewButton_1_3.setForeground(new Color(255, 255, 255));
		btnNewButton_1_3.setBackground(new Color(0, 51, 153));
		btnNewButton_1_3.setFont(new Font("한컴산뜻돋움", Font.BOLD, 50));
		btnNewButton_1_3.setBounds(523, 25, 411, 73);
		Customer_Panel.add(btnNewButton_1_3);

		btnNewButton_1_1_2 = new JButton("EXIT");
		btnNewButton_1_1_2.setForeground(new Color(255, 255, 255));
		btnNewButton_1_1_2.setBackground(new Color(0, 51, 153));
		btnNewButton_1_1_2.setFont(new Font("한컴산뜻돋움", Font.BOLD, 40));
		btnNewButton_1_1_2.setBounds(946, 25, 126, 73);
		btnNewButton_1_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ExitConfirmDialog dialog = new ExitConfirmDialog();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setModal(true);
				dialog.setVisible(true);
				if ( dialog.exitNumber == 1) {
					System.exit(exitNumber);
				}
			}
		});
		Customer_Panel.add(btnNewButton_1_1_2);	
		JButton btnNewButton_5 = new JButton("신규등록");
		btnNewButton_5.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		btnNewButton_5.setBackground(Color.WHITE);
		btnNewButton_5.setForeground(new Color(0, 51, 153));
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				createCustomerDialog dialog = new createCustomerDialog();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				
			}
		});
		btnNewButton_5.setBounds(12, 137, 179, 34);
		Customer_Panel.add(btnNewButton_5);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setForeground(new Color(0, 0, 0));
		comboBox.setFont(new Font("굴림체", Font.PLAIN, 12));
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if( e.getStateChange() == ItemEvent.SELECTED ) {
					
					if( comboBox.getSelectedIndex() == 0) {
						// 남성 회원만
						table.setModel( new CustomerListTableModel01(1));
						
					} else if (comboBox.getSelectedIndex() == 1) {
						// 여성 회원만
						table.setModel( new CustomerListTableModel01(0));
					} else if ( comboBox.getSelectedIndex() == 2 ) {
						// 이달의 등록한 회원만
						LocalDate now = LocalDate.now();
						table.setModel( new CustomerListTableModel02(now.getMonthValue()));
					} else if ( comboBox.getSelectedIndex() == 3 ) {
						int a = 0;
						int b = 0;
						// 이용권 만료중인 회원만
						table.setModel( new CustomerListTableModel02( a, b));
					} else if ( comboBox.getSelectedIndex() == 4 ) {
						String a = "";
						int b = 0;
						table.setModel( new CustomerListTableModel02( a, b));
						
					}
				}
				
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"남성회원 전체보기", "여성회원 전체보기", "이달의 등록한 회원 전체보기", "이용권 만료된 회원 전체보기", "현재 운동중인 회원 보기"}));
		comboBox.setBounds(12, 225, 179, 23);
		Customer_Panel.add(comboBox);
		
		JButton btnNewButton_5_1 = new JButton("회원 전체보기");
		btnNewButton_5_1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		btnNewButton_5_1.setForeground(new Color(0, 51, 153));
		btnNewButton_5_1.setBackground(new Color(255, 255, 255));
		btnNewButton_5_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				table.setModel( new CustomerListTableModel01());
			}
		});
		btnNewButton_5_1.setBounds(12, 181, 179, 34);
		Customer_Panel.add(btnNewButton_5_1);
		
		table = new JTable();
		table.setBackground(UIManager.getColor("CheckBox.background"));
		table.setBounds(203, 181, 869, 370);
		Customer_Panel.add(table);
		
		JLabel lblNewLabel_5 = new JLabel("회원 이름 검색");
		lblNewLabel_5.setForeground(new Color(0, 0, 0));
		lblNewLabel_5.setBackground(new Color(0, 51, 153));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("한컴산뜻돋움", Font.BOLD, 18));
		lblNewLabel_5.setBounds(203, 137, 171, 34);
		Customer_Panel.add(lblNewLabel_5);
		
		searchTf1 = new JTextField();
		searchTf1.setBounds(386, 137, 499, 34);
		Customer_Panel.add(searchTf1);
		searchTf1.setColumns(10);
		
		JButton btnNewButton_6 = new JButton("검색하기");
		btnNewButton_6.setFont(new Font("한컴산뜻돋움", Font.BOLD, 21));
		btnNewButton_6.setForeground(new Color(255, 255, 255));
		btnNewButton_6.setBackground(new Color(0, 51, 153));
		btnNewButton_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String cname = searchTf1.getText();
				searchTf1.setText("");
				
				table.setModel( new CustomerListTableModel01(cname));

			}
		});
		btnNewButton_6.setBounds(897, 137, 175, 34);
		Customer_Panel.add(btnNewButton_6);
		
		JButton btnNewButton_3 = new JButton("BACK");
		btnNewButton_3.setFont(new Font("Bahnschrift", Font.PLAIN, 25));
		btnNewButton_3.setBackground(new Color(0, 51, 153));
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Customer_Panel.setVisible(false);
				Main_Panel.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(12, 509, 179, 42);
		Customer_Panel.add(btnNewButton_3);
		
		C_Login_Suc_Panel = new JPanel();
		C_Login_Suc_Panel.setLayout(null);
		C_Login_Suc_Panel.setBackground(new Color(255, 255, 255));
		C_Login_Suc_Panel.setBounds(0, 0, 1084, 561);
		contentPane.add(C_Login_Suc_Panel);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("체크인 되었습니다.");
		lblNewLabel_2_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2_1_2.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		lblNewLabel_2_1_2.setBounds(684, 219, 300, 33);
		C_Login_Suc_Panel.add(lblNewLabel_2_1_2);
		
		lblNewLabel_2_1_1_1 = new JLabel("");
		lblNewLabel_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_1_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_2_1_1_1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 50));
		lblNewLabel_2_1_1_1.setBounds(267, 77, 443, 117);
		C_Login_Suc_Panel.add(lblNewLabel_2_1_1_1);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(397, 300, 313, 219);
		C_Login_Suc_Panel.add(lblNewLabel_1);
		
		C_Logout_suc_Panel = new JPanel();
		C_Logout_suc_Panel.setBackground(new Color(255, 255, 255));
		C_Logout_suc_Panel.setBounds(0, 0, 1084, 561);
		contentPane.add(C_Logout_suc_Panel);
		C_Logout_suc_Panel.setLayout(null);
		
		JLabel lblNewLabel_2_1_2_1 = new JLabel("체크아웃 되었습니다.");
		lblNewLabel_2_1_2_1.setBackground(new Color(0, 0, 0));
		lblNewLabel_2_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_2_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_2_1_2_1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		lblNewLabel_2_1_2_1.setBounds(696, 209, 300, 39);
		C_Logout_suc_Panel.add(lblNewLabel_2_1_2_1);
		
		lblNewLabel_2_1_1_1_1 = new JLabel("");
		lblNewLabel_2_1_1_1_1.setBackground(new Color(0, 0, 0));
		lblNewLabel_2_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_1_1_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_2_1_1_1_1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 50));
		lblNewLabel_2_1_1_1_1.setBounds(279, 67, 443, 117);
		C_Logout_suc_Panel.add(lblNewLabel_2_1_1_1_1);
		
		lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(381, 303, 313, 219);
		C_Logout_suc_Panel.add(lblNewLabel_1_1);
		C_Logout_suc_Panel.setVisible(false);
		LocalDate now = LocalDate.now();

	}
}
