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
		//fatto nel login
		req.getSession().setAttribute("logged", true);
		req.getSession().setAttribute("userId", 5);
		//
		int idUser = (int) req.getSession().getAttribute("userId");
		User principale = null;
		if(req.getSession().getAttribute("userId") != null)
			principale = DBManager.getInstance().getUser(idUser);
		try{
			User friend = DBManager.getInstance().getUser(Integer.parseInt(req.getParameter("id")));
			req.setAttribute("user", friend);
			req.setAttribute("friend", true);
		}catch(NumberFormatException e)
		{
			req.setAttribute("user", principale);
			req.setAttribute("friend", false);
		}
		RequestDispatcher rd = null;
		rd = req.getRequestDispatcher("profile.jsp");
		rd.forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}

}
