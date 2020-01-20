package controller;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.mail.smtp.SMTPTransport;
import model.User;
import persistence.DAOFactory;

@WebServlet(value = "/help")
public class GeneralHelp extends HttpServlet
{
	String to;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		getPage(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		if(req.getParameter("send").equals("false"))
			getPage(req, resp);
		else if(req.getParameter("send").equals("true"))
		{
			try
			{
				Properties props = System.getProperties();
				props.put("mail.smtps.host", "smtp.gmail.com");
				props.put("mail.smtps.auth", "true");
				Session session = Session.getInstance(props, null);
				Message msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress("pandorasjar2019@gmail.com"));
				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
				msg.setSubject(req.getParameter("subject"));
				msg.setText("Questa email è stata inviata da " + req.getParameter("email") + ":\n" + req.getParameter("message") + "");
				//msg.setHeader("X-Mailer", "");
				msg.setSentDate(new Date());
				SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
				t.connect("smtp.gmail.com", "pandorasjar2019@gmail.com", "ptqehcaqsmgrhjni");
				t.sendMessage(msg, msg.getAllRecipients());
				t.close();
			}
			catch (MessagingException e)
			{
				//e.printStackTrace();
			}
			finally
			{
				resp.sendRedirect("/");
			}
		}
		else
		{
			resp.sendRedirect("/");
		}
	}

	private void getPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		to = (String) req.getParameter("emailTo");
		if(to == null)
		{
			to = "pandorasjar2019@gmail.com";
		}
		User loggedUser = null;
		if(req.getSession().getAttribute("userId") != null)
		{
			int idUser = (int) req.getSession().getAttribute("userId");
			loggedUser = DAOFactory.getInstance().makeUserDAO().getUserByIdUser(idUser);
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
