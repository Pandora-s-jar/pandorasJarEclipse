package controller.confirmCode;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/controlCode", name = "controlCode")
public class ControlCode extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("header.jsp");
        rd.include(req, resp);
        rd = req.getRequestDispatcher("controlCode.html");
        rd.include(req, resp);
        rd = req.getRequestDispatcher("footer.html");
        rd.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = (String) req.getSession().getAttribute("secretCode");
        if(code.equals(req.getParameter("code"))){
            resp.setStatus(200);
        }
        else{
            resp.setStatus(403);
        }
    }
}
