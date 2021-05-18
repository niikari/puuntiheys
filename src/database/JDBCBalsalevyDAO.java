package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Balsalevy;

public class JDBCBalsalevyDAO implements BalsalevyDAO {
	
	
	@Override
	public List<Balsalevy> getAll() {
		Database db = new Database();
		List<Balsalevy> items = new ArrayList<>();
		
		try {
			Connection connection = db.connect();
			String sql = "SELECT * FROM Balsalevy";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				try (ResultSet rs = statement.executeQuery()) {
					while (rs.next()) {
						Balsalevy balsalevy = new Balsalevy(rs.getInt("id"), rs.getDouble("tiheys"), rs.getDouble("korkeus"), rs.getDouble("leveys"), rs.getDouble("paino"), rs.getDouble("pituus"), rs.getString("grain"));
						items.add(balsalevy);
					}
					rs.close();
				}
				statement.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return items;
		
	}

	@Override
	public Balsalevy getItem(int id) {
		// TODO Auto-generated method stub
		for (Balsalevy balsalevy : this.getAll()) {
			if (balsalevy.getId() == id) {
				return balsalevy;
			}
		}
		
		return null;
	}

	@Override
	public boolean addItem(Balsalevy balsalevy) {
		// TODO Auto-generated method stub
		int id = 0;
		for (Balsalevy b : this.getAll()) {
			if (b.getId() > id) {
				id = b.getId();
			}
		}
		
		id++;
		
		Database db = new Database();
		
		try {
			Connection connection = db.connect();
			String sql = "INSERT INTO Balsalevy VALUES(?, ?, ?, ?, ?, ?, ?)";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setLong(1, id);
				statement.setDouble(2, balsalevy.getTiheys());
				statement.setDouble(3, balsalevy.getPaksuus());
				statement.setDouble(4, balsalevy.getLeveys());
				statement.setDouble(5, balsalevy.getPaino());
				statement.setDouble(6, balsalevy.getPituus());
				statement.setString(7, balsalevy.getGrain());
				if (statement.executeUpdate() > 0) {
					return true;
				}
				
				statement.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return false;
				
	}

	@Override
	public boolean removeItem(Balsalevy balsalevy) {
		Database db = new Database();
		
		try {
			Connection connection = db.connect();
			String sql = "DELETE FROM Balsalevy WHERE id = ?";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setLong(1, balsalevy.getId());
				if (statement.executeUpdate() > 0) {
					return true;
				}
				
				statement.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return false;
	}

}
