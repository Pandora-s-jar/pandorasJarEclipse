package controller.library;

import model.Game;
import model.User;
import persistence.DAOFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/library", name = "library")
public class Library extends HttpServlet {

    private void refreshGame(HttpServletRequest req){
        User u = (User) req.getSession().getAttribute("user");
        u.setLibrary(DAOFactory.getInstance().makeUserDAO().refreshLibrary(u));
        this.log(String.valueOf(u.getLibrary()));
        req.getSession().setAttribute("user",u);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("header.jsp");
        rd.include(req, resp);
        if(req.getSession().getAttribute("logged") == null || !(Boolean) req.getSession().getAttribute("logged")){
            rd = req.getRequestDispatcher("errorNotLogged.html");
        }
        else{
            refreshGame(req);
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
