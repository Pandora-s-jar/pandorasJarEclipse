package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import persistence.DBManager;

public class GeneralProfile extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//String idUser = (String) req.getSession().getAttribute("userId");
		int idUser = 1;
		//User u = DBManager.getInstance().getUser(Integer.parseInt(idUser));
		User u = DBManager.getInstance().getUser(idUser);
		req.getSession().setAttribute("user", u);
		RequestDispatcher rd = req.getRequestDispatcher("header.html");
		rd.include(req, resp);
		if(u != null)
			rd = req.getRequestDispatcher("profile.jsp");
		else
			rd = req.getRequestDispatcher("login.html");
		rd.include(req, resp);
		rd = req.getRequestDispatcher("footer.html");
		rd.include(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}

}
