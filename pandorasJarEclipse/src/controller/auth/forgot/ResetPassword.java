package controller.auth.forgot;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/resetPsw")
public class ResetPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("header.jsp");
        requestDispatcher.include(req, resp);
        requestDispatcher = req.getRequestDispatcher("resetPassword.html");
        requestDispatcher.include(req, resp);
        requestDispatcher = req.getRequestDispatcher("footer.html");
        requestDispatcher.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("header.jsp");
        requestDispatcher.include(req, resp);
        //TODO: index
        requestDispatcher = req.getRequestDispatcher("index.html");
        requestDispatcher.include(req, resp);
        requestDispatcher = req.getRequestDispatcher("footer.html");
        requestDispatcher.include(req, resp);
    }
}
