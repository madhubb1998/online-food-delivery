package onlineFoodDelivery;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField customername;
	
	private Connection con,con1;
	private Statement st,st1,st2,st3,st4,st5;
	private ResultSet rs,rs1,rs2,rs3,rs5;
	int rs4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerList frame = new CustomerList(args[0]);
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
	public CustomerList(String pass) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200,50,1000,600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/online_food_delivery?autoReconnect=true&serverTimezone=UTC&useSSL=False&allowPublicKeyRetrieval=true","root","");
			

				
				st2=con.createStatement();
				String sql2="Select CustomerName from customerlogin";
				rs2= st2.executeQuery(sql2);
				
				int length = 0;
				int i = 0;
				while(rs2.next())
				{
					length++;
				}
				
				Object[][] data = new Object[length][6];
				
				st1=con.createStatement();
				String sql="Select CustomerId,username,CustomerName,Address,Mobile,password from customerlogin";
				rs1= st1.executeQuery(sql);
				while(rs1.next())
				{
					data[i][0] = rs1.getString(1);			
					data[i][1] = rs1.getString(2);
					data[i][2] = rs1.getString(3);
					data[i][3] = rs1.getString(4);
					data[i][4] = rs1.getString(5);
					data[i][5] = rs1.getString(6);					
					i++;
				}
				String[] columns = {"CustomerId","username","CustomerName","Address","Mobile","password"};
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 46, 885, 396);
		contentPane.add(scrollPane);
		
		table = new JTable(data,columns);
		scrollPane.setViewportView(table);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] arr = new String[1];
				arr[0]=pass;
				Admin.main(arr);
				dispose();
				
			}
		});
		btnBack.setBounds(10, 11, 63, 23);
		contentPane.add(btnBack);
		
		JLabel lblDeleteCustomer = new JLabel("Delete Customer");
		lblDeleteCustomer.setForeground(Color.MAGENTA);
		lblDeleteCustomer.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDeleteCustomer.setBounds(390, 452, 128, 31);
		contentPane.add(lblDeleteCustomer);
		
		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setForeground(Color.BLUE);
		lblCustomerName.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblCustomerName.setBounds(356, 477, 128, 31);
		contentPane.add(lblCustomerName);
		
		customername = new JTextField();
		customername.setBounds(443, 482, 187, 20);
		contentPane.add(customername);
		customername.setColumns(10);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					st3=con.createStatement();
					String sql3="Select CustomerName from customerlogin where CustomerName='"+customername.getText()+"'";
					rs3= st3.executeQuery(sql3);
					if(rs3.next()) {
						st4=con.createStatement();
						String sql4="delete from customerlogin where CustomerName='"+customername.getText()+"'";
						rs4= st4.executeUpdate(sql4);				
						if(rs4!=0) {
						JOptionPane.showMessageDialog(null, "Customer deleted successfully......");	
						String[] arr = new String[1];
						arr[0]=pass;
						CustomerList.main(arr);
						dispose();
						}
					}
					else
						JOptionPane.showMessageDialog(null, "Customer name is incorrect......");
					
					
				}catch(Exception e1) {
					System.out.println("Erro : "+e1);
				}
				
			}
		});
		btnDelete.setBackground(Color.RED);
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBounds(395, 519, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnLogout = new JButton("logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login.main(null);
				dispose();
			}
		});
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setBackground(Color.RED);
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogout.setBounds(821, 11, 89, 23);
		contentPane.add(btnLogout);
		
		}catch(Exception ex) {
			System.out.println("Erro: "+ex);
		}
	}
}
