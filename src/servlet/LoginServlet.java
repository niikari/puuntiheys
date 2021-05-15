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
		BalsalevyDAO balsalevyDAO = new JDBCBalsalevyDAO();
		try {
			String givenPin = req.getParameter("salasana");
			if (givenPin.equals(pin)) {
				//Cookie cookie = new Cookie("pin", "givenPin");
				//cookie.setPath("/database");
				//resp.addCookie(cookie);
				req.setAttribute("items", balsalevyDAO.getAll());
				//System.out.println(balsalevyDAO.getAll());
				resp.sendRedirect("/niilespuuntiheys2/database");
			} else {
				throw new Exception();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
		
		
	}
		
		
}
