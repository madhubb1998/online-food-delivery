package onlineFoodDelivery;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.sql.*;
import javax.swing.*;

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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddItem extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField itemname;
	private JTextField itemprice;
	private JTextField nos;
	
	private Connection con,con1;
	private Statement st,st1,st2,st3,st4,st5;
	private ResultSet rs,rs1,rs2,rs3,rs4,rs5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddItem frame = new AddItem(args[0]);
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
	public AddItem(String pass) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200,50,1000,600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		try {
		
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/online_food_delivery?autoReconnect=true&serverTimezone=UTC&useSSL=False&allowPublicKeyRetrieval=true","root","");
			
			st=con.createStatement();
			String sqll="Select RestaurentName from restaurentownerlogin where username='"+pass+"'";
			rs= st.executeQuery(sqll);
			String rname;
			if(rs.next()) {
				
				}
				rname = rs.getString(1);
				st2=con.createStatement();
				String sql2="Select RestaurentName from item where RestaurentName='"+rname+"'";
				rs2= st2.executeQuery(sql2);
				
				int length = 0;
				int i = 0;
				while(rs2.next())
				{
					length++;
				}
				
				Object[][] data = new Object[length][3];
				
				st1=con.createStatement();
				String sql="Select ItemName,ItemPrice,Quantity from item where RestaurentName='"+rname+"'";
				rs1= st1.executeQuery(sql);
				while(rs1.next())
				{
					data[i][0] = rs1.getString(1);			
					data[i][1] = rs1.getString(2);
					data[i][2] = rs1.getString(3);
					i++;
				}
				String[] columns = {"ItemName","ItemPrice","Quantity"};
			
			
			
			
			
			
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(561, 57, 396, 479);
		contentPane.add(scrollPane);
		
		table = new JTable(data,columns);
		scrollPane.setViewportView(table);
		
		JButton btnLogout = new JButton("logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RestraurentOwnerLogin.main(null);
				dispose();
			}
		});
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogout.setBackground(Color.RED);
		btnLogout.setBounds(823, 11, 89, 23);
		contentPane.add(btnLogout);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] arr= new String[1];
				arr[0]=pass;
				RestaurentPersonal.main(arr);
				dispose();
			}
		});
		btnBack.setBounds(28, 24, 72, 30);
		contentPane.add(btnBack);
		
		JLabel lblItemName = new JLabel("Item Name");
		lblItemName.setForeground(Color.BLUE);
		lblItemName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblItemName.setBounds(166, 101, 155, 30);
		contentPane.add(lblItemName);
		
		JLabel lblItemPrice = new JLabel("Item price");
		lblItemPrice.setForeground(Color.BLUE);
		lblItemPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblItemPrice.setBounds(166, 181, 155, 30);
		contentPane.add(lblItemPrice);
		
		JLabel lblNos = new JLabel("No's");
		lblNos.setForeground(Color.BLUE);
		lblNos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNos.setBounds(166, 252, 155, 30);
		contentPane.add(lblNos);
		
		itemname = new JTextField();
		itemname.setBounds(257, 101, 155, 30);
		contentPane.add(itemname);
		itemname.setColumns(10);
		
		itemprice = new JTextField();
		itemprice.setColumns(10);
		itemprice.setBounds(257, 181, 72, 30);
		contentPane.add(itemprice);
		
		nos = new JTextField();
		nos.setColumns(10);
		nos.setBounds(257, 254, 72, 30);
		contentPane.add(nos);
		
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					con1=DriverManager.getConnection("jdbc:mysql://localhost/online_food_delivery?autoReconnect=true&serverTimezone=UTC&useSSL=False&allowPublicKeyRetrieval=true","root","");
					
					st3=con1.createStatement();
					String sqll="Select RestaurentName from restaurentownerlogin where username='"+pass+"'";
					rs3= st3.executeQuery(sqll);
					String rname;
					if(rs3.next()) {
						rname = rs3.getString(1);
						
						
						st4=con.createStatement();
						String sql3="insert into item(ItemName,RestaurentName,ItemPrice,Quantity) values(?,?,?,?)";					
						PreparedStatement pst = con1.prepareStatement(sql3);
						pst.setString(1, itemname.getText());
						pst.setString(2, rname);
						pst.setString(3, itemprice.getText());
						pst.setString(4, nos.getText());
						pst.executeUpdate();
					
						JOptionPane.showMessageDialog(null, "Item Added successfully......");	
						String[] arr = new String[1];
						arr[0]=pass;
						AddItem.main(arr);
						dispose();
			
					}
					
					
					
					
					
				}catch(Exception e1) {
					System.out.println("Erro : "+e1);
				}
				
				
			}
		});
		btnAddItem.setForeground(Color.WHITE);
		btnAddItem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAddItem.setBackground(Color.GREEN);
		btnAddItem.setBounds(185, 323, 125, 45);
		contentPane.add(btnAddItem);
		
		}catch(Exception ex) {
			System.out.println("Erro : "+ex);
		}
		
	}
}
