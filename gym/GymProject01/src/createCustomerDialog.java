import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;
import java.awt.Color;

public class createCustomerDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tf1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JSpinner spinner1;
	private JSpinner spinner2;
	private JSpinner spinner3;
	private JComboBox cb1;
	private JSpinner spinner4;
	private JSpinner spinner5;
	private JSpinner spinner6;
	private JSpinner spinner7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			createCustomerDialog dialog = new createCustomerDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public createCustomerDialog() {
		setBounds(100, 100, 497, 401);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("성명");
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
			lblNewLabel.setBounds(12, 26, 90, 35);
			contentPanel.add(lblNewLabel);
		}
		{
			tf1 = new JTextField();
			tf1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 18));
			tf1.setBounds(138, 33, 316, 23);
			contentPanel.add(tf1);
			tf1.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("성별");
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
			lblNewLabel.setBounds(12, 71, 90, 35);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel = new JLabel("생년월일");
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
			lblNewLabel.setBounds(12, 116, 90, 35);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel = new JLabel("회원권");
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
			lblNewLabel.setBounds(12, 161, 90, 35);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel = new JLabel("년");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 12));
			lblNewLabel.setBounds(219, 116, 36, 35);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel = new JLabel("월");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 12));
			lblNewLabel.setBounds(323, 116, 36, 35);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel = new JLabel("일");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 12));
			lblNewLabel.setBounds(418, 116, 36, 35);
			contentPanel.add(lblNewLabel);
		}
		{
			cb1 = new JComboBox();
			cb1.setForeground(new Color(0, 0, 0));
			cb1.setBackground(new Color(255, 255, 255));
			cb1.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
			cb1.setModel(new DefaultComboBoxModel(new String[] {"1개월", "3개월", "6개월", "12개월"}));
			cb1.setBounds(138, 168, 84, 23);
			contentPanel.add(cb1);
		}
		{
			JLabel lblNewLabel = new JLabel("담당자 번호");
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
			lblNewLabel.setBounds(12, 256, 104, 35);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel = new JLabel("등록일자");
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
			lblNewLabel.setBounds(12, 206, 90, 35);
			contentPanel.add(lblNewLabel);
		}
		{
			spinner4 = new JSpinner();
			spinner4.setBounds(138, 211, 84, 23);
			contentPanel.add(spinner4);
		}
		{
			JLabel lblNewLabel = new JLabel("년");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 12));
			lblNewLabel.setBounds(219, 213, 36, 23);
			contentPanel.add(lblNewLabel);
		}
		{
			spinner5 = new JSpinner();
			spinner5.setBounds(257, 214, 70, 22);
			contentPanel.add(spinner5);
		}
		{
			JLabel lblNewLabel = new JLabel("월");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 12));
			lblNewLabel.setBounds(323, 213, 36, 23);
			contentPanel.add(lblNewLabel);
		}
		{
			spinner6 = new JSpinner();
			spinner6.setModel(new SpinnerNumberModel(1, 1, 31, 1));
			spinner6.setBounds(355, 214, 66, 22);
			contentPanel.add(spinner6);
		}
		{
			JLabel lblNewLabel = new JLabel("일");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 12));
			lblNewLabel.setBounds(418, 213, 36, 23);
			contentPanel.add(lblNewLabel);
		}
		
		JRadioButton radiobtn1 = new JRadioButton("남자");
		radiobtn1.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		radiobtn1.setBackground(new Color(255, 255, 255));
		buttonGroup.add(radiobtn1);
		radiobtn1.setBounds(138, 72, 70, 35);
		contentPanel.add(radiobtn1);
		
		JRadioButton radiobtn2 = new JRadioButton("여자");
		radiobtn2.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		radiobtn2.setBackground(new Color(255, 255, 255));
		buttonGroup.add(radiobtn2);
		radiobtn2.setBounds(291, 72, 64, 35);
		contentPanel.add(radiobtn2);
		{
			spinner1 = new JSpinner();
			spinner1.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
			spinner1.setModel(new SpinnerNumberModel(0, null, 3000, 1));
			spinner1.setBounds(138, 123, 84, 23);
			contentPanel.add(spinner1);
		}
		{
			spinner2 = new JSpinner();
			spinner2.setModel(new SpinnerNumberModel(1, 1, 12, 1));
			spinner2.setBounds(257, 123, 70, 23);
			contentPanel.add(spinner2);
		}
		{
			spinner3 = new JSpinner();
			spinner3.setModel(new SpinnerNumberModel(1, 1, 31, 1));
			spinner3.setBounds(355, 123, 70, 23);
			contentPanel.add(spinner3);
		}
		
		spinner7 = new JSpinner();
		spinner7.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 12));
		spinner7.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		spinner7.setBounds(138, 264, 84, 22);
		contentPanel.add(spinner7);
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(0, 51, 153));
			panel.setBounds(0, 0, 125, 327);
			contentPanel.add(panel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setFont(new Font("Bahnschrift", Font.BOLD, 13));
				okButton.setForeground(new Color(255, 255, 255));
				okButton.setBackground(new Color(0, 51, 153));
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						int selectedNum;
						String birthDay = spinner3.getValue() + "-" + spinner2.getValue() + "-" + spinner1.getValue();
						String mDay = spinner6.getValue() + "-" + spinner5.getValue() + "-" + spinner4.getValue();
						
						System.out.println( birthDay );
						System.out.println( mDay );
						
						if(radiobtn1.isSelected() == true) {
							selectedNum = 1; 
						} else {
							selectedNum = 0;
						}
						CustomerPanelDAO CustomerDAO = new CustomerPanelDAO();
						CustomerDAO.NewCustomer(tf1.getText(), selectedNum, birthDay, mDay, cb1.getSelectedIndex() + 1, (int)spinner7.getValue() );
						
						JOptionPane.showMessageDialog( null, "회원으로 등록했습니다.", "신규 등록 성공", JOptionPane.INFORMATION_MESSAGE);
						
						createCustomerDialog.this.dispose();
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setFont(new Font("Bahnschrift", Font.BOLD, 13));
				cancelButton.setForeground(new Color(0, 51, 153));
				cancelButton.setBackground(new Color(255, 255, 255));
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						createCustomerDialog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
