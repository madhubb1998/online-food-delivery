package onlineFoodDelivery;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class CustomerRegister extends JFrame {
	
	private Connection con;
	private Statement st;
	private ResultSet rs;

	private JPanel contentPane;
	private JTextField CustomerName;
	private JTextField username;
	private JTextField Moblie;
	private JTextField Password;
	private JTextField confirmPassword;
	private JTextField Address;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerRegister frame = new CustomerRegister();
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
	public CustomerRegister() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200,50,1000,600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCreateAnAccount = new JLabel("Create an account for customer");
		lblCreateAnAccount.setForeground(Color.MAGENTA);
		lblCreateAnAccount.setFont(new Font("Gabriola", Font.PLAIN, 30));
		lblCreateAnAccount.setBounds(73, 45, 436, 45);
		contentPane.add(lblCreateAnAccount);
		
		CustomerName = new JTextField();
		CustomerName.setBounds(401, 95, 317, 31);
		contentPane.add(CustomerName);
		CustomerName.setColumns(10);
		
		username = new JTextField();
		username.setColumns(10);
		username.setBounds(401, 137, 317, 31);
		contentPane.add(username);
		
		Moblie = new JTextField();
		Moblie.setColumns(10);
		Moblie.setBounds(401, 179, 317, 31);
		contentPane.add(Moblie);
		
		Password = new JTextField();
		Password.setColumns(10);
		Password.setBounds(401, 221, 317, 31);
		contentPane.add(Password);
		
		confirmPassword = new JTextField();
		confirmPassword.setColumns(10);
		confirmPassword.setBounds(401, 263, 317, 31);
		contentPane.add(confirmPassword);
			
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost/online_food_delivery?autoReconnect=true&serverTimezone=UTC&useSSL=False&allowPublicKeyRetrieval=true","root","");
					st=con.createStatement();
					
						String pass,rpass;
						pass = Password.getText();
						rpass = confirmPassword.getText();
						if(pass.equals(rpass)) {
							String query = "insert into customerlogin(username,CustomerName,Address,Mobile,Password) values(?,?,?,?,?)";
							PreparedStatement pst = con.prepareStatement(query);
							pst.setString(1, username.getText());
							pst.setString(2, CustomerName.getText());
							pst.setString(3, Address.getText());
							pst.setString(4, Moblie.getText());
							pst.setString(5, Password.getText());
							pst.executeUpdate();
							JOptionPane.showMessageDialog(null, "Registered successfully......");	
							CustomerLogin.main(null);
							dispose();						
						}
						else {
							JOptionPane.showMessageDialog(null, "Password mismatch......");					
						}
					
					
				}catch(Exception ex) {
					//System.out.println("Erro:"+ex);
					JOptionPane.showMessageDialog(null, "username already exits......");
				}
				
				
				
				
				
				
			}
		});
		btnSubmit.setBackground(Color.GREEN);
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSubmit.setBounds(355, 390, 100, 31);
		contentPane.add(btnSubmit);
		
		JLabel lblFullName = new JLabel("Full name");
		lblFullName.setForeground(Color.BLUE);
		lblFullName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFullName.setBounds(274, 95, 127, 31);
		contentPane.add(lblFullName);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.BLUE);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(274, 137, 127, 31);
		contentPane.add(lblUsername);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setForeground(Color.BLUE);
		lblMobile.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMobile.setBounds(274, 179, 127, 31);
		contentPane.add(lblMobile);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.BLUE);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(274, 221, 127, 31);
		contentPane.add(lblPassword);
		
		JLabel lblConfirmpassword = new JLabel("Confirm-password");
		lblConfirmpassword.setForeground(Color.BLUE);
		lblConfirmpassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConfirmpassword.setBounds(274, 263, 127, 31);
		contentPane.add(lblConfirmpassword);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setForeground(Color.BLUE);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAddress.setBounds(274, 313, 127, 31);
		contentPane.add(lblAddress);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerLogin.main(null);
				dispose();
			}
		});
		btnBack.setBounds(10, 11, 72, 31);
		contentPane.add(btnBack);
		
		Address = new JTextField();
		Address.setColumns(10);
		Address.setBounds(401, 313, 317, 31);
		contentPane.add(Address);
	}
}
