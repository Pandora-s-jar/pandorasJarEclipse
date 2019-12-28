package controller.upload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/saveTemporarilyForUpload")
public class EmailAndPayment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //this.log((String) req.getSession().getAttribute("secretCode"));
        //this.log(req.getParameter("code"));
        req.getRequestDispatcher("/profile").forward(req, resp);
        /*if((req.getSession().getAttribute("secretCode").equals(req.getParameter("code")))){
            req.getSession().setAttribute("paymentCoordinates", req.getParameter("paymentCoordinates"));
            req.getRequestDispatcher("gameDetails").forward(req, resp);
        }
        else{
            resp.setStatus(401);
        }*/
    }
}
