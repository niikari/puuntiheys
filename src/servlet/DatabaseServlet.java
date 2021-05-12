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
	
	BalsalevyDAO balsalevyDAO = new JDBCBalsalevyDAO();
	String tulos = "";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doget");
		
		if (req.getCookies().length < 1) {
			req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
		} else {
			req.setAttribute("items", balsalevyDAO.getAll());
			req.setAttribute("tulos", tulos);
			tulos = "";
			req.getRequestDispatcher("/database.jsp").forward(req, resp);
		}
		
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		DecimalFormat des = new DecimalFormat("0.00");

		try {
			double paksuus = Double.valueOf(req.getParameter("paksuus")) / 1000;
			double pituus = Double.valueOf(req.getParameter("pituus")) / 1000;
			double leveys = Double.valueOf(req.getParameter("leveys")) / 1000;
			double paino = Double.valueOf(req.getParameter("paino")) / 1000;
			String grain = req.getParameter("grain");
			double tiheys = Math.floor(paino / (paksuus * pituus * leveys));
			tulos = des.format(tiheys);
			
			Balsalevy balsalevy = new Balsalevy(tiheys, paksuus, leveys, paino, pituus, grain);
			balsalevyDAO.addItem(balsalevy);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resp.sendRedirect("/database");

	}
	
	@Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id = Integer.valueOf(req.getParameter("id"));
		Balsalevy toDelete = balsalevyDAO.getItem(id);
		balsalevyDAO.removeItem(toDelete);
		
    }
}
