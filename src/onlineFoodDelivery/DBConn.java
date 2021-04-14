
package onlineFoodDelivery;
import java.sql.*;



public class DBConn {

	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public DBConn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/online_food_delivery?autoReconnect=true&serverTimezone=UTC&useSSL=False&allowPublicKeyRetrieval=true","root","");
			st=con.createStatement();
			
		}catch(Exception ex) {
			System.out.println("Erro: "+ex);
		}
	}
	
	public void getData() {
		try {
			String query = "select * from login";
			rs=st.executeQuery(query);
			System.out.println("Record from database");
			while(rs.next()) {
				String userName = rs.getString("username");
				String passwd = rs.getString("password");
				System.out.println("User name : "+userName+"\tPassword : "+passwd);
			}
			
		}catch(Exception ex) {
			System.out.println("Erro: "+ex);
		}
	}

}
