package controller.upload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(value = "/formGameUpload", name = "formGameUpload")
public class FormGameUpload extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("header.jsp");
        requestDispatcher.include(req, resp);
        if(req.getSession().getAttribute("logged") == null || !(Boolean) req.getSession().getAttribute("logged")){
            requestDispatcher = req.getRequestDispatcher("errorNotLogged.html");
        }
        else{
            requestDispatcher = req.getRequestDispatcher("formGameUpload.html");
        }
        requestDispatcher.include(req, resp);
        requestDispatcher = req.getRequestDispatcher("footer.html");
        requestDispatcher.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // guide to: https://www.tutorialspoint.com/servlets/servlets-file-uploading.htm
        //TODO: Upload and retrieve the gameID
        String nome = req.getParameter("nome");
        String descrizione = req.getParameter("descrizione");
        String specifiche = req.getParameter("specifiche");
        String prezzo = req.getParameter("prezzo");
        ArrayList<String> links = new ArrayList<>();
        this.log(nome);
        this.log(descrizione);
        this.log(specifiche);
        this.log(prezzo);
        int gameId = 2;
        resp.sendRedirect("/gamePage?id="+gameId);
    }
}
