package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	//private static final String URL = "jdbc:sqlite:C:\\Users\\pakni\\git\\niilespuuntiheys2\\balsa.sqlite";
	private static final String URL = "jdbc:sqlite:/home/niiles/balsa.sqlite";
	//private static final String URL = "jdbc:sqlite:.\\balsa.sqlite";
	
	
	public Connection connect() throws ClassNotFoundException {
		try {
			Class.forName("org.sqlite.JDBC");
			return DriverManager.getConnection(URL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
		
	}
}
