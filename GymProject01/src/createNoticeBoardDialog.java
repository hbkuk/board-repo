import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollBar;
import java.awt.Color;
import java.awt.Font;

public class createNoticeBoardDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tf1;
	String adminID;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			createNoticeBoardDialog dialog = new createNoticeBoardDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	
	public createNoticeBoardDialog(String adminID) {
		this();
		this.adminID = adminID;
	}
	public createNoticeBoardDialog() {
		setBounds(100, 100, 946, 473);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 930, 397);
		contentPanel.setBackground(new Color(0, 51, 153));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			tf1 = new JTextField();
			tf1.setBackground(new Color(255, 255, 255));
			tf1.setFont(new Font("새굴림", Font.PLAIN, 15));
			tf1.setToolTipText("제목");
			tf1.setText("제목을 입력하세요");
			tf1.setBounds(100, 51, 710, 47);
			contentPanel.add(tf1);
			tf1.setColumns(10);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 127, 710, 228);
		contentPanel.add(scrollPane);
		
		JTextArea ta1 = new JTextArea();
		ta1.setFont(new Font("새굴림", Font.PLAIN, 14));
		ta1.setToolTipText("내용");
		ta1.setText("내용을 입력하세요");
		scrollPane.setViewportView(ta1);
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			panel.setBounds(0, 37, 930, 360);
			contentPanel.add(panel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setBounds(0, 397, 930, 37);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.setForeground(new Color(255, 255, 255));
				okButton.setBackground(new Color(0, 51, 153));
				okButton.setFont(new Font("Bahnschrift", Font.BOLD, 15));
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						MainPanelDAO mainDAO = new MainPanelDAO();
						mainDAO.CreateNoticeBoard( tf1.getText(), ta1.getText(), adminID);
						JOptionPane.showMessageDialog( null, "작성이 완료되었습니다.", "성공", JOptionPane.INFORMATION_MESSAGE);
						createNoticeBoardDialog.this.dispose();
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setForeground(new Color(0, 51, 153));
				cancelButton.setBackground(new Color(255, 255, 255));
				cancelButton.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						createNoticeBoardDialog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
