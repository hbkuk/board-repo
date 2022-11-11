import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class admin_Login_Field_DIalog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tf_ID;
	private JPasswordField pf_pw;
	int LoginNumber;
	String currentAdminID;

	/**
	 * Create the dialog.
	 */
	public admin_Login_Field_DIalog() {
		setBounds(100, 100, 600, 347);
		getContentPane().setLayout(null);
		contentPanel.setBackground(new Color(0, 51, 153));
		contentPanel.setBounds(0, 0, 584, 315);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		tf_ID = new JTextField();
		tf_ID.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		tf_ID.setBounds(158, 43, 344, 36);
		contentPanel.add(tf_ID);
		tf_ID.setColumns(10);
		{
			JLabel lblNewLabel = new JLabel("ID");
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setBackground(new Color(255, 255, 255));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
			lblNewLabel.setBounds(53, 43, 76, 36);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblPw = new JLabel("P/W");
			lblPw.setForeground(new Color(255, 255, 255));
			lblPw.setHorizontalAlignment(SwingConstants.CENTER);
			lblPw.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
			lblPw.setBounds(53, 111, 76, 36);
			contentPanel.add(lblPw);
		}
		{
			pf_pw = new JPasswordField();
			pf_pw.setFont(new Font("함초롬돋움", Font.PLAIN, 20));
			pf_pw.setBounds(158, 111, 344, 36);
			contentPanel.add(pf_pw);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setBounds(0, 181, 584, 134);
			contentPanel.add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.setBackground(new Color(0, 51, 153));
				okButton.setForeground(new Color(255, 255, 255));
				okButton.setFont(new Font("Bahnschrift", Font.BOLD, 25));
				okButton.setBounds(76, 67, 182, 44);
				okButton.addMouseListener(new MouseAdapter() {
					// 아이디와 비밀번호가 맞으면 관리자 로그인 dailog -> 메인화면 panel로 넘어가기
					// 아이디와 비밀번호가 틀리다면 다시 관리자 로그인 dailog 생성 
					@Override
					public void mouseClicked(MouseEvent e) {
						AdminLoginConnect adminLogin = new AdminLoginConnect();
						
						if( adminLogin.isadmin( tf_ID.getText(), pf_pw.getText())) {
							admin_Login_Field_DIalog.this.dispose();
							currentAdminID = tf_ID.getText();
							LoginNumber = 1;
						} else {
							LoginNumber = 0;
							JOptionPane.showMessageDialog( null, "로그인 실패", "로그인 실패", JOptionPane.ERROR_MESSAGE);
							admin_Login_Field_DIalog.this.dispose();
							
						}
					}
				});
				buttonPane.setLayout(null);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBackground(new Color(255, 255, 255));
				cancelButton.setForeground(new Color(0, 51, 153));
				cancelButton.setFont(new Font("Bahnschrift", Font.BOLD, 25));
				cancelButton.setBounds(348, 67, 166, 44);
				cancelButton.addMouseListener(new MouseAdapter() {
					
					// 관리자 로그인 dailog -> 초기 상태로 돌아가기
					@Override
					public void mouseClicked(MouseEvent e) {
						admin_Login_Field_DIalog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("등록하지 않았나요?");
				lblNewLabel_1.setBounds(220, 10, 150, 23);
				buttonPane.add(lblNewLabel_1);
				lblNewLabel_1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						AdminAccountCreateDialog dialog = new AdminAccountCreateDialog();
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setModal(true);
						dialog.setVisible(true);
						
						
					}
				});
				lblNewLabel_1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 15));
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			}
		}
	}
}
