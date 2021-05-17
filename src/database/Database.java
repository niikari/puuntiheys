package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	
	//private static final String URL = "jdbc:sqlite:C:\\Users\\pakni\\git\\niilespuuntiheys2\\balsa.sqlite";
	private static final String URL = "jdbc:sqlite:/home/niiles/balsa.sqlite";
	
	
	public Connection connect() {
		Connection connection = null;
		try {
			Class.forName("org.sqlite.JDBC");
			System.out.println(URL);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
}
