package aed.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServerConnection {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static final String DB_URL = "jdbc:sqlserver://192.168.86.237:1433;databaseName=bdFutbol;";

	// Database credentials
	static final String USER = "sa";
	static final String PASS = "sa";

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
