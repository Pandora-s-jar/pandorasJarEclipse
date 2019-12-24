package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

@WebServlet("/login")
public class GeneralLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(403); // Permission danied, only POST here
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter o = resp.getWriter();
        resp.setContentType("text/html");
        if(true){ //TODO: Database query
            //Back to the same page as before
            req.getSession().setAttribute("logged",true);
            resp.sendRedirect(req.getHeader("referer"));
        }else{
            //TODO: Forse si può fare con ajax che ricevi un errore, poi controllo
            o.println("Credenziali sbagliate");
        }
    }
}
