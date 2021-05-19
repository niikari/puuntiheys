package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.BalsalevyDAO;
import database.JDBCBalsalevyDAO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private final static String pin = "108";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}
		
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String givenPin = req.getParameter("salasana");
		if (givenPin.equals(pin)) {
			resp.sendRedirect("/niilespuuntiheys2/database");
		} else {
			resp.sendRedirect("/niilespuuntiheys2/login");
		}
		
		
	}
}
