package aed.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:8889/bdFutbol";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "root";

	// Connection
	private static Connection connection;

	public static Connection connect() {
		// Register JDBC driver
		try {
			Class.forName(JDBC_DRIVER);
			// Open a connection
			System.out.println("Connecting to a selected database...");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected database successfully...");
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return connection;
	}

}
