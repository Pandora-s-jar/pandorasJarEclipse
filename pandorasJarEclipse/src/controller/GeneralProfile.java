package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GeneralProfile extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5862976528660473450L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("header.html");
		rd.include(req, resp);
		rd = req.getRequestDispatcher("profile.html");
		rd.include(req, resp);
		rd = req.getRequestDispatcher("footer.html");
		rd.include(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}

}
