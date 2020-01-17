package controller.forgotPassword;

import model.User;
import persistence.DAOFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/forgotPassword", name = "forgotPassword")
public class ForgotPassword extends HttpServlet {

    private boolean checkEmail(String email){
        User user = DAOFactory.getInstance().makeUserDAO().getUserByEmail(email);
        return user.getEmail() == null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("header.jsp");
        requestDispatcher.include(req, resp);
        requestDispatcher = req.getRequestDispatcher("forgotPassword.html");
        requestDispatcher.include(req, resp);
        requestDispatcher = req.getRequestDispatcher("footer.html");
        requestDispatcher.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        if(!checkEmail(email)){
            req.getSession().setAttribute("nextPage", "/resetPassword");
            req.getSession().setAttribute("previousPage", "/forgotPassword");
            req.getSession().setAttribute("email", email);
            resp.sendRedirect("/sendCode");
        }
        else{
            resp.sendRedirect("/forgotPassword?emailNotExists=true");
        }
    }
}
