package controller.authentication;

import model.User;
import persistence.DAOFactory;
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
        User user = DAOFactory.getInstance().makeUserDAO().getUserByEmail(req.getParameter("email"));
        if (user == null){
            resp.setStatus(301);
            return;
        }
        if (user.getId() == 0 || user.getPassword() == null || user.getUsername() == null || user.getEmail() == null)
        {
            resp.setStatus(301);
            return;
        }

        if(user.getPassword().equals(req.getParameter("password"))){
            req.getSession().setAttribute("logged",true);
            req.getSession().setAttribute("userId", user.getId());
            resp.addCookie(new Cookie("logged", "true"));
            req.getSession().setAttribute("user", user);
            resp.setStatus(201);
        }else{
            resp.setStatus(301);
        }
    }
}
