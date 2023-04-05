import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;

public class AdminAccountCreateDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tf1;
	private JPasswordField pf1;
	private JPasswordField pf2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AdminAccountCreateDialog dialog = new AdminAccountCreateDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AdminAccountCreateDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 51, 153));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		
		tf1 = new JTextField();
		tf1.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		tf1.setBounds(119, 56, 272, 33);
		contentPanel.add(tf1);
		tf1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Admin ID");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(12, 56, 83, 33);
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		contentPanel.add(lblNewLabel);
		
		JLabel lblAdminPw = new JLabel("Admin PW");
		lblAdminPw.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblAdminPw.setForeground(new Color(255, 255, 255));
		lblAdminPw.setBounds(12, 102, 95, 33);
		contentPanel.add(lblAdminPw);
		
		pf1 = new JPasswordField();
		pf1.setFont(new Font("휴먼모음T", Font.PLAIN, 20));
		pf1.setBounds(119, 102, 272, 33);
		contentPanel.add(pf1);
		
		JLabel lblNewLabel2 = new JLabel("Approval NO.");
		lblNewLabel2.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblNewLabel2.setForeground(new Color(255, 255, 255));
		lblNewLabel2.setBounds(12, 145, 168, 33);
		contentPanel.add(lblNewLabel2);
		
		pf2 = new JPasswordField();
		pf2.setFont(new Font("휴먼매직체", Font.PLAIN, 20));
		pf2.setBounds(159, 145, 232, 33);
		pf2.setEchoChar('*');
		contentPanel.add(pf2);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setBackground(new Color(0, 51, 153));
				okButton.setForeground(new Color(255, 255, 255));
				okButton.setFont(new Font("Bahnschrift", Font.BOLD, 20));
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						if( pf2.getText().equals("123456") ) {
							
							AdminLoginConnect adminNew = new AdminLoginConnect();
							if( adminNew.isadminID(tf1.getText())) {
								JOptionPane.showMessageDialog( null, "이미 존재하는 아이디입니다.", "생성 실패", JOptionPane.ERROR_MESSAGE);
								tf1.setText("");
								pf1.setText("");
								pf2.setText("");
							} else {
								adminNew.createAdaminAccount( tf1.getText(), pf1.getText());
								JOptionPane.showMessageDialog( null, "생성 완료했습니다.", "생성 완료", JOptionPane.INFORMATION_MESSAGE);
								AdminAccountCreateDialog.this.dispose();
							}
						} else {
							JOptionPane.showMessageDialog( null, "허용되지 않은 승인번호입니다.", "생성 실패", JOptionPane.ERROR_MESSAGE);
							AdminAccountCreateDialog.this.dispose();
							tf1.setText("");
							pf1.setText("");
							pf2.setText("");
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBackground(new Color(255, 255, 255));
				cancelButton.setForeground(new Color(0, 51, 153));
				cancelButton.setFont(new Font("Bahnschrift", Font.BOLD, 20));
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						AdminAccountCreateDialog.this.dispose();
						
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
