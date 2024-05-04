
import java.sql.*;

public class jdbc {
	
	// change these variables to match with the SQL account on your computer
	private String databasename = "mahoa";
	private String SQL_Username = "myuser2";
	private String SQL_Userpass = "10001";

	public void update(String username, String userpass) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databasename + "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC", SQL_Username, SQL_Userpass);) {
			
			String str1 = "INSERT INTO tk(Username, Userpass) VALUES (?, ?)";
			PreparedStatement stmt = conn.prepareStatement(str1);
			stmt.setString(1, username);
			stmt.setString(2, userpass);
			
			int rset1 = stmt.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	
	public boolean select(String str1, String str2, String str3) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databasename + "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC", SQL_Username, SQL_Userpass);) {
			
			PreparedStatement stmt = conn.prepareStatement(str1);
			stmt.setString(1, str2);
			
			ResultSet rset1 = stmt.executeQuery();
			
			while (rset1.next()) {
				String str4 = rset1.getString(str3);
				if (str2.equals(str4)) {
					return true;
				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	
	
	public boolean select2(String str1, String str2, String str3) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databasename + "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC", SQL_Username, SQL_Userpass);) {
			
			PreparedStatement stmt = conn.prepareStatement(str1);
			stmt.setString(1, str2);
			
			ResultSet rset1 = stmt.executeQuery();
			
			while (rset1.next()) {
				String str4 = rset1.getString("Userpass");
				if (str3.equals(str4)) {
					return true;
				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
}
