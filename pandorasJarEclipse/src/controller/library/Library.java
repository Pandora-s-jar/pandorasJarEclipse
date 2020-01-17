package controller.library;

import model.Game;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/library", name = "library")
public class Library extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("header.jsp");
        rd.include(req, resp);
        if(req.getSession().getAttribute("logged") == null || !(Boolean) req.getSession().getAttribute("logged")){
            rd = req.getRequestDispatcher("errorNotLogged.html");
        }
        else{
            rd = req.getRequestDispatcher("library.jsp");
        }
        rd.include(req, resp);
        rd = req.getRequestDispatcher("footer.html");
        rd.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(401);
    }

}
