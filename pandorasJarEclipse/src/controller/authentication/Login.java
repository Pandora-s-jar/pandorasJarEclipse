package controller.authentication;

import model.User;
//import persistence.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/login", name = "login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(403); // Permission danied, only POST here
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(true){ //TODO: Database query
            //Back to the same page as before
            int userId = 5;
            req.getSession().setAttribute("logged",true);
            req.getSession().setAttribute("userId", userId);
            resp.addCookie(new Cookie("logged", "true"));
            resp.sendRedirect(req.getHeader("referer"));
            //TODO: da settare l'utente nella session
            //User user = DBManager.getInstance().getUser("Simone");
            //req.getSession().setAttribute("user", user);
        }else{
            //TODO: Forse si può fare con ajax che ricevi un errore, poi controllo
        }
    }
}
