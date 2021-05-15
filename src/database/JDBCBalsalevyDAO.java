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
		// TODO Auto-generated method stub
		List<Balsalevy> items = new ArrayList<>();
		
		Connection connection = new Database().connect();
		String sql = "SELECT * FROM Balsalevy";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			try (ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					Balsalevy balsalevy = new Balsalevy(rs.getInt("id"), rs.getDouble("tiheys"), rs.getDouble("korkeus"), rs.getDouble("leveys"), rs.getDouble("paino"), rs.getDouble("pituus"), rs.getString("grain"));
					items.add(balsalevy);
				}
			}
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}

	@Override
	public Balsalevy getItem(int id) {
		// TODO Auto-generated method stub
		Connection connection = new Database().connect();
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
		
		Connection connection = new Database().connect();
		//String sql = "INSERT INTO Balsalevy VALUES (" + id + ", " + balsalevy.getTiheys() + ", " + balsalevy.getPaksuus() + ", " + balsalevy.getLeveys() + ", " + balsalevy.getPaino() + ", " +balsalevy.getPituus() + ", '" + balsalevy.getGrain() + "');";
		String sql = "INSERT INTO Balsalevy VALUES(?, ?, ?, ?, ?, ?, ?)";
		//System.out.println(sql);
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, id);
			statement.setDouble(2, balsalevy.getTiheys());
			statement.setDouble(3, balsalevy.getPaksuus());
			statement.setDouble(4, balsalevy.getLeveys());
			statement.setDouble(5, balsalevy.getPaino());
			statement.setDouble(6, balsalevy.getPituus());
			statement.setString(7, balsalevy.getGrain());
			int rowsAffected = statement.executeUpdate();
			if (rowsAffected > 0) {
				return true;
			}
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean removeItem(Balsalevy balsalevy) {
		Connection connection = new Database().connect();
		//String sql = "DELETE FROM Balsalevy WHERE id='" + balsalevy.getId() + "'";
		String sql = "DELETE FROM Balsalevy WHERE id = ?";		
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, balsalevy.getId());
			int rowsAffected = statement.executeUpdate();
			if (rowsAffected > 0) {
				return true;
			}
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return false;
	}

}
