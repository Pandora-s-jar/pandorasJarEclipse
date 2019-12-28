package controller.auth.forgot;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/confirmCode")
public class ConfirmCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("userEmail").equals(req.getParameter("email")) &&
                req.getSession().getAttribute("secretCode").equals(req.getParameter("secretCode"))){
            //TODO: database query to get userId by req.getSession().getAttribute("userEmail")
            int userId = 1234;
            req.getSession().setAttribute("userId",userId);
            resp.setStatus(201);
        }else{
            resp.setStatus(401);
        }
    }
}
