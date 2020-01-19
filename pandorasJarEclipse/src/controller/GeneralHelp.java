package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import persistence.DAOFactory;

@WebServlet(value = "/help")
public class GeneralHelp extends HttpServlet
{	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		getPage(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		if(req.getParameter("sendEmail").equals("false"))
			getPage(req, resp);
		else
		{
			//invia email
		}
	}

	private void getPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String to = (String) req.getParameter("emailTo");
		if(to == null)
		{
			to = "pandorasjar2019@gmail.com";
		}
		System.out.println(to);
		User loggedUser = null;
		if(req.getSession().getAttribute("userId") != null)
		{
			int idUser = 1;//(int) req.getSession().getAttribute("userId");
			loggedUser = DAOFactory.getInstance().makeUserDAO().getUserFromIdUser(idUser);
			String name = loggedUser.getUsername();
			String email = loggedUser.getEmail();
			req.setAttribute("name", name);
			req.setAttribute("email", email);
		}
		req.setAttribute("emailTo", to);
		RequestDispatcher rd;
		rd = req.getRequestDispatcher("header.jsp");
		rd.include(req, resp);
		rd = req.getRequestDispatcher("help.jsp");
		rd.include(req, resp);
		rd = req.getRequestDispatcher("footer.html");
		rd.include(req, resp);
	}
}
