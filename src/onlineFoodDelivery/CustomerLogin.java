package onlineFoodDelivery;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class CustomerLogin extends JFrame {
	
	private Connection con;
	private Statement st,st1;
	private ResultSet rs,rs1;

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerLogin frame = new CustomerLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CustomerLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200,50,1000,600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		username = new JTextField();
		username.setBounds(405, 140, 300, 45);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUsername.setBounds(305, 140, 90, 45);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword.setBounds(305, 212, 90, 45);
		contentPane.add(lblPassword);
		
		JButton btnSignin = new JButton("Signin");
		btnSignin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost/online_food_delivery?autoReconnect=true&serverTimezone=UTC&useSSL=False&allowPublicKeyRetrieval=true","root","");
					st=con.createStatement();
					 
					String sql= "Select * from customerlogin where username='"+username.getText()+"'and password='"+password.getText()+"'";
					rs=st.executeQuery(sql);
					
					st1=con.createStatement();
					 
					String sql1= "Select CustomerName from customerlogin where username='"+username.getText()+"'";
					rs1=st1.executeQuery(sql1);
									
					if(rs.next()) {
						if(rs1.next()) {
							//JOptionPane.showMessageDialog(null, "Login sucessfully........");
							String pass = rs1.getString(1);
							String[] arr = new String[1];
							arr[0] = pass;
							OrderItem.main(arr);
							dispose();
					}}
					else
						JOptionPane.showMessageDialog(null, "username and password are incorrert........");
					
					
				
				}catch(Exception ex) {
					System.out.println("Erro"+ex);
				}
				
			}
		});
		btnSignin.setBackground(Color.BLUE);
		btnSignin.setForeground(Color.WHITE);
		btnSignin.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSignin.setBounds(364, 323, 107, 38);
		contentPane.add(btnSignin);
		
		JButton btnSignup = new JButton("Signup");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerRegister.main(null);
				dispose();
			}
		});
		btnSignup.setBackground(Color.GREEN);
		btnSignup.setForeground(Color.WHITE);
		btnSignup.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSignup.setBounds(527, 323, 99, 38);
		contentPane.add(btnSignup);
		
		JLabel lblCustomerLogin = new JLabel("Customer Login");
		lblCustomerLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomerLogin.setFont(new Font("Gabriola", Font.BOLD, 30));
		lblCustomerLogin.setForeground(Color.MAGENTA);
		lblCustomerLogin.setBounds(305, 65, 400, 45);
		contentPane.add(lblCustomerLogin);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginType.main(null);
				dispose();
			}
		});
		btnBack.setBounds(22, 11, 63, 30);
		contentPane.add(btnBack);
		
		password = new JPasswordField();
		password.setBounds(405, 213, 300, 45);
		contentPane.add(password);
	}
}
