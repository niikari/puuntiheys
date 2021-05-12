package database;

import java.util.List;

import model.Balsalevy;

public interface BalsalevyDAO {
	
	public List<Balsalevy> getAll();
	public Balsalevy getItem(int id);
	public boolean addItem(Balsalevy balsalevy);
	public boolean removeItem(Balsalevy balsalevy);
}
