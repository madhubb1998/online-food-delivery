package onlineFoodDelivery;
 import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class Admin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin(args[0]);
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
	public Admin(String pass) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200,50,1000,600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton logout = new JButton("LOGOUT");
		logout.setBackground(Color.RED);
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			login.main(null);
			dispose();
			}
		});
		logout.setBounds(409, 378, 88, 39);
		contentPane.add(logout);
		
		JButton btnCustomerList = new JButton("Customer List");
		btnCustomerList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] arr = new String[1];
				arr[0]=pass;
				CustomerList.main(arr);
				dispose();
				}
		});
		btnCustomerList.setBackground(Color.MAGENTA);
		btnCustomerList.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCustomerList.setForeground(Color.WHITE);
		btnCustomerList.setBounds(333, 121, 231, 45);
		contentPane.add(btnCustomerList);
		
		JButton btnRestaurentOwnersList = new JButton("Restaurent Owner's List");
		btnRestaurentOwnersList.setBackground(Color.BLUE);
		btnRestaurentOwnersList.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRestaurentOwnersList.setForeground(Color.WHITE);
		btnRestaurentOwnersList.setBounds(333, 202, 231, 45);
		contentPane.add(btnRestaurentOwnersList);
		
		JButton btnUpdateProfile = new JButton("Update Profile");
		btnUpdateProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnUpdateProfile.setBackground(Color.GREEN);
		btnUpdateProfile.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpdateProfile.setForeground(Color.WHITE);
		btnUpdateProfile.setBounds(333, 285, 231, 45);
		contentPane.add(btnUpdateProfile);
	}
}
