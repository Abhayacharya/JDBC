import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App {
	public static void main(String[] args) throws SQLException {
		System.out.println("1. New User");
		System.out.println("2. Existing User");
		Scanner sc = new Scanner(System.in);
		System.out.println("enter the option ");
		int op = sc.nextInt();
		switch(op)
		{
		case 1:register();
		break;
		
		case 2:login();
		break;
		
		default:System.out.println("invalid option");
		break;
		}
	}

	private static void login() throws SQLException {
		// TODO Auto-generated method stub
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		Connection c= DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "12345678");
		Scanner sc = new Scanner(System.in);
		System.out.println("enter the username");
		String username = sc.next();
		System.out.println("enter the password");
		String password = sc.next();
			String s4 = "select * from user where username =?";
			PreparedStatement p = c.prepareStatement(s4);
			p.setString(1, username);
			ResultSet res = p.executeQuery();
			if(res.next()) {
			if(password.equals(res.getString(3))) {
				System.out.println("Login successfull");
			}
			else {
				System.out.println("Invalid password");
			}
			
		}
			else {
				System.out.println("Invalid user name");
			}
	}

	private static void register() throws SQLException {
		// TODO Auto-generated method stub
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		Connection c= DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "12345678");
		String s = "insert into user values(?, ?, ?, ?)";
		PreparedStatement pt = c.prepareStatement(s);
		Scanner sc =  new Scanner(System.in);
		System.out.println("enter the name");
		String name=sc.next();
		System.out.println("enter the username");
		String username = sc.next();
		while(true) {
		String s1 = "select * from user where username=?";
		PreparedStatement ps = c.prepareStatement(s1);
		ps.setString(1, username);
		ResultSet res = ps.executeQuery();
		if(res.next()) {
			System.out.println("already existe");
			username = sc.next();
		}
		else {
			break;
		}
		}
		String password;
		String cp;
		do {
		System.out.println("enter the password");
		 password = sc.next();
		System.out.println("confirm password");
		 cp = sc.next();
		}while(!password.equals(cp));
		System.out.println("enter the email");
		String email = sc.next();
		pt.setString(1, name);
		pt.setString(2, username);
		pt.setString(3, password);
		pt.setString(4, email);
		int r = pt.executeUpdate();
		System.out.println(r);
	}
   
}
