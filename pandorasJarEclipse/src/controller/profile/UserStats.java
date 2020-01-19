package controller.profile;

import model.Game;
import model.User;
import persistence.DAOFactory;
import persistence.UserDAO;
import utility.Pair;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/UserStats")
public class UserStats extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = (int) req.getSession().getAttribute("userId");
        float totalHours = 0f;
        TreeMap<Integer, Integer> hoursPlayedYear = DAOFactory.getInstance().makeHoursPlayedDAO().getHoursPlayedFromIdUser(id);
        for(Integer year: hoursPlayedYear.keySet())
        {
            totalHours += hoursPlayedYear.get(year);
        }
        TreeMap<Integer, Integer> gamesPlayedYear = DAOFactory.getInstance().makePurchaseDAO().getGamesYearFromIdUser(id);
        int totalGames = 0;
        for(Integer year: gamesPlayedYear.keySet())
        {
            totalGames += gamesPlayedYear.get(year);
        }
        ArrayList<Pair<Integer, String>> gameScore = DAOFactory.getInstance().makeScoreDAO().getScoresFromIdUser(id);
        Pair<Integer, String> bestScore = new Pair<Integer, String>(0,"");
        bestScore.setFirst(gameScore.get(0).getFirst());
        bestScore.setSecond(gameScore.get(0).getSecond());
        for(Pair<Integer,String> p: gameScore)
        {
            if(p.getFirst() > bestScore.getFirst())
            {
                bestScore.setSecond(p.getSecond());
                bestScore.setFirst(p.getFirst());
            }
        }
        req.getSession().setAttribute("hoursPlayedKeys", hoursPlayedYear.keySet());
        req.getSession().setAttribute("hoursPlayedValues", hoursPlayedYear.values());

        req.getSession().setAttribute("totalHoursPlayed", totalHours);

        req.getSession().setAttribute("gamesPlayedKeys", gamesPlayedYear.keySet());
        req.getSession().setAttribute("gamesPlayedValues", gamesPlayedYear.values());

        req.getSession().setAttribute("totalGamesPlayed", totalGames);

        req.getSession().setAttribute("bestScoreName", bestScore.getSecond());
        req.getSession().setAttribute("bestScoreValue", bestScore.getFirst());

        RequestDispatcher rd = req.getRequestDispatcher("userStats.jsp");
        rd.forward(req,resp);
    }
}
