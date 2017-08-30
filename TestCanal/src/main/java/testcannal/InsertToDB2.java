package testcannal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InsertToDB2 {

	public static void main(String[] args) throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/test?user=root&password=root";
		Connection con = DriverManager.getConnection(url);

		con.setAutoCommit(false);
		Statement stmt = con.createStatement();
		stmt.executeUpdate("update ssttt set b=b+1");

		Thread.sleep(40000);

		stmt.executeUpdate("update ssttt set b=b+1");
		con.commit();

	}

}
