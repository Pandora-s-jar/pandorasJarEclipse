package controller.authentication;

import model.User;
import persistence.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/register/insertDatabase", name = "insertUserToDatabase")
public class InsertDatabase extends HttpServlet {

    private User createUser(HttpSession session){
        String email = (String) session.getAttribute("email");
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        User user = new User(username, password, "ciao sono" + username, null, email, null);
        return user;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = createUser(req.getSession());
        DAOFactory.getInstance().makeUserDAO().insertUser(user);
        resp.sendRedirect("/?registered=true");
    }
}
