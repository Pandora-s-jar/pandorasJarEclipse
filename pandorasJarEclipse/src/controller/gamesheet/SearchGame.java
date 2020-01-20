package controller.gamesheet;

import model.Game;
import persistence.DAOFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value="/Search")
public class SearchGame extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String gameName = req.getParameter("ricerca");
        if(gameName != null)
        {
            ArrayList<Game> games = DAOFactory.getInstance().makeGameDAO().getGamesFromNameLike(gameName);
            req.setAttribute("games", games);
            RequestDispatcher rd = req.getRequestDispatcher("searchGame.jsp");
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
