package servlet;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.BalsalevyDAO;
import database.JDBCBalsalevyDAO;
import model.Balsalevy;

@WebServlet("/database")
public class DatabaseServlet extends HttpServlet {
	
	
	String tulos = "";
	String alkuPaksuus = "";
	String alkuPituus = "";
	String alkuLeveys = "";
	String alkuPaino = "";
	String alkuGrain = "";
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BalsalevyDAO balsalevyDAO = new JDBCBalsalevyDAO();
		req.setAttribute("alkuPaksuus", alkuPaksuus);
		req.setAttribute("alkuPituus", alkuPituus);
		req.setAttribute("alkuLeveys", alkuLeveys);
		req.setAttribute("alkuPaino", alkuPaino);
		req.setAttribute("alkuGrain", alkuGrain);
		req.setAttribute("tulos", tulos);
		tulos = "";
		alkuPaksuus = "";
		alkuPituus = "";
		alkuLeveys = "";
		alkuPaino = "";
		alkuGrain = "";
		req.setAttribute("items", balsalevyDAO.getAll());
		req.getRequestDispatcher("/database.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		BalsalevyDAO balsalevyDAO = new JDBCBalsalevyDAO();
		DecimalFormat des = new DecimalFormat("0.00");

		try {
			double paksuus = Double.valueOf(req.getParameter("paksuus")) / 1000;
			double pituus = Double.valueOf(req.getParameter("pituus")) / 1000;
			double leveys = Double.valueOf(req.getParameter("leveys")) / 1000;
			double paino = Double.valueOf(req.getParameter("paino")) / 1000;
			String grain = req.getParameter("grain");
			double tiheys = Math.floor(paino / (paksuus * pituus * leveys));
			tulos = des.format(tiheys);
			alkuPaksuus = req.getParameter("paksuus");
			alkuPituus = req.getParameter("pituus");
			alkuLeveys = req.getParameter("leveys");
			alkuPaino = req.getParameter("paino");
			alkuGrain = req.getParameter("grain");
			
			
			Balsalevy balsalevy = new Balsalevy(tiheys, paksuus, leveys, paino, pituus, grain);
			balsalevyDAO.addItem(balsalevy);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resp.sendRedirect("/niilespuuntiheys2/database");

	}
	
	@Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		BalsalevyDAO balsalevyDAO = new JDBCBalsalevyDAO();
		int id = Integer.valueOf(req.getParameter("id"));
		Balsalevy toDelete = balsalevyDAO.getItem(id);
		balsalevyDAO.removeItem(toDelete);
		
    }
}
