package java_gui_group7;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

//import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class loginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	HashMap<String,String> idPassHashMap = new HashMap<String,String>();
	private JTextField adminIdField;
	private JPasswordField passwordField;
	
	/*Launch the application.*/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginFrame frame = new loginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*Create the frame.*/
	public loginFrame() {
		login_info idandpassInfo = new login_info();
		initialize(idandpassInfo.getIdandPass());
	}
	
	private void initialize(HashMap<String,String> loginInfo) {
		
		idPassHashMap = loginInfo;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 450);
		
		setTitle("Login - USTP DBMS");
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.decode("#e6edf7"));;

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//set image icon
		ImageIcon ustpLogoIcon = new ImageIcon("USTP_DBMS-removebg-preview.png");
		setIconImage(ustpLogoIcon.getImage());
		
		
		JLabel udbmsLogoImg = new JLabel("");
		udbmsLogoImg.setIcon(new ImageIcon("udbms_logo (1).png"));
		udbmsLogoImg.setBounds(123, 31, 235, 156);
		contentPane.add(udbmsLogoImg);
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBounds(114, 198, 235, 174);
		loginPanel.setBackground(Color.decode("#e6edf7"));
		contentPane.add(loginPanel);
		loginPanel.setLayout(null);
		
		JLabel adminIdLbl = new JLabel("Admin ID:");
		adminIdLbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		adminIdLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		adminIdLbl.setBounds(11, 70, 60, 14);
		adminIdLbl.setForeground(Color.decode("#004aad"));
		loginPanel.add(adminIdLbl);
		
		adminIdField = new JTextField();
		adminIdField.setBounds(81, 67, 123, 20);
		loginPanel.add(adminIdField);
		adminIdField.setColumns(10);
		
		JLabel adminLoginLbl = new JLabel("ADMIN LOGIN");
		adminLoginLbl.setHorizontalAlignment(SwingConstants.CENTER);
		adminLoginLbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		adminLoginLbl.setBounds(33, 11, 172, 27);
		adminLoginLbl.setForeground(Color.decode("#004aad"));
		loginPanel.add(adminLoginLbl);
		
		JLabel passwordLbl = new JLabel("Password:");
		passwordLbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		passwordLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLbl.setBounds(0, 101, 70, 14);
		passwordLbl.setForeground(Color.decode("#004aad"));
		loginPanel.add(passwordLbl);
		
		JButton clearBtn = new JButton("Clear");
		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminIdField.setText("");
				passwordField.setText("");
			}
		});
		clearBtn.setFocusPainted(false);
		clearBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		clearBtn.setBounds(81, 129, 59, 23);
		loginPanel.add(clearBtn);
		
		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String adminId = adminIdField.getText();
				String pass = passwordField.getText();
				
				if(idPassHashMap.containsKey(adminId)) {
					if(idPassHashMap.get(adminId).equals(pass)) {
						dispose();
						
						facultydbFrame facultydbframe = new facultydbFrame();
						facultydbframe.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(contentPane,"Login unsuccessful!");
					}
				}
				else {
					JOptionPane.showMessageDialog(contentPane,"Username not found!");
				}
			}
		});
		loginBtn.setFocusPainted(false);
		loginBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		loginBtn.setBounds(146, 129, 60, 23);
		loginPanel.add(loginBtn);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(81, 98, 123, 20);
		loginPanel.add(passwordField);
		
		setLocationRelativeTo(null);
	}
}