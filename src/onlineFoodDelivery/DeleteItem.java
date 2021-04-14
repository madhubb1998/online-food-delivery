package onlineFoodDelivery;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DeleteItem extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField itemname;
	
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
					DeleteItem frame = new DeleteItem(args[0]);
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
	public DeleteItem(String pass) {
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
		lblItemName.setBounds(147, 203, 155, 30);
		contentPane.add(lblItemName);
		
		itemname = new JTextField();
		itemname.setBounds(238, 203, 155, 30);
		contentPane.add(itemname);
		itemname.setColumns(10);
		
		JButton delete = new JButton("Delete Item");
		delete.addActionListener(new ActionListener() {
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
						
						st5=con1.createStatement();						
						String sql5= "Select ItemId from item where ItemName='"+itemname.getText()+"'and RestaurentName='"+rname+"'";
						rs5=st5.executeQuery(sql5);
						if(rs5.next()) {
													
						String id = rs5.getString(1);
						st4=con1.createStatement();
						String sql3="delete from item where item.ItemId='"+id+"'";					
						rs4=st4.executeUpdate(sql3);
						if(rs4!=0) {
						JOptionPane.showMessageDialog(null, "Item deleted successfully......");	
						String[] arr = new String[1];
						arr[0]=pass;
						DeleteItem.main(arr);
						dispose();
						}
			
						}else
							JOptionPane.showMessageDialog(null, "Item not in the List......");
				}
					
					
					
				}catch(Exception e1) {
					System.out.println("Erro : "+e1);
				}
				
				
			}
		});
		delete.setForeground(Color.WHITE);
		delete.setFont(new Font("Tahoma", Font.BOLD, 14));
		delete.setBackground(Color.GREEN);
		delete.setBounds(214, 267, 125, 45);
		contentPane.add(delete);
		
		}catch(Exception ex) {
			System.out.println("Erro : "+ex);
		}
		
		
	}

}
