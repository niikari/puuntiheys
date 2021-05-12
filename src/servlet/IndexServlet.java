package servlet;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.BalsalevyDAO;
import database.JDBCBalsalevyDAO;
import model.Balsalevy;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

	BalsalevyDAO balsalevy = new JDBCBalsalevyDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String tulos ="";
		String amerikanTulos = "";
		DecimalFormat des = new DecimalFormat("0.00");
		try {
			
			Double paksuus = Double.parseDouble(req.getParameter("paksuus")) / 1000;
			Double pituus = Double.parseDouble(req.getParameter("pituus")) / 1000;
			Double leveys = Double.parseDouble(req.getParameter("leveys")) / 1000;
			Double paino = Double.parseDouble(req.getParameter("paino")) / 1000;
			//System.out.println(paino /(paksuus * pituus * leveys));
			amerikanTulos = des.format(paino * 2.20462262185 / ((paksuus * 3.2808399) * (pituus * 3.2808399) * (leveys * 3.2808399)));
			tulos = des.format(paino /(paksuus * pituus * leveys));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		req.setAttribute("tulos", tulos);
		req.setAttribute("amerikanTulos", amerikanTulos);
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		

		resp.sendRedirect("/index");

	}
	
	@Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    }

}

