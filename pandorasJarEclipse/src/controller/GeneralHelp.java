package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

@WebServlet(value = "/help")
public class GeneralHelp extends HttpServlet
{	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String to = (String) req.getSession().getAttribute("emailTo");
		if(to == null)
		{
			to = "pandorasjar2019@gmail.com";
		}
		req.getSession().setAttribute("userId", 5);
		int idUser = (int) req.getSession().getAttribute("userId");
		User loggedUser = null;
		if(req.getSession().getAttribute("userId") != null)
		{
			//loggedUser = DBManager.getInstance().getUser(idUser);
			String name = "Simone";
			String email = loggedUser.getEmail();
			req.setAttribute("name", name);
			req.setAttribute("email", email);
			req.setAttribute("emailTo", to);
		}
		RequestDispatcher rd;
		rd = req.getRequestDispatcher("header.jsp");
		rd.include(req, resp);
		rd = req.getRequestDispatcher("assistenza.jsp");
		rd.include(req, resp);
		rd = req.getRequestDispatcher("footer.html");
		rd.include(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		//TODO: invio email
	}

}
