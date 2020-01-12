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
        HashSet<String> gamesPlayed = new HashSet<String>();
        float totalHours = 0f;
        //ArrayList<Game> game = user.getLibrary();
        TreeMap<Integer, Integer> hoursPlayedYear = DAOFactory.getInstance().makeHoursPlayedDAO().getHoursPlayedFromIdUser(id).getHoursPlayed();
        for(Integer year: hoursPlayedYear.keySet())
        {
            totalHours += hoursPlayedYear.get(year);
        }
        /*SortedMap<String, Integer> gamesPlayedYear = new TreeMap<String, Integer>();
        gamesPlayedYear.put("2018", 0);
        gamesPlayedYear.put("2019", 0);
        gamesPlayedYear.put("2020", 0);

        Pair<String, Float> bestScore = new Pair<String, Float>("",0f);
        if(!game.isEmpty())
        {
            bestScore.setFirst(game.get(0).getName());
            bestScore.setSecond(game.get(0).getRanking().get(user.getId()));
            for(Game g: game)
            {
                if(g.getRanking().get(user.getId()) > bestScore.getSecond())
                {
                    bestScore.setSecond(g.getRanking().get(user.getId()));
                    bestScore.setFirst(g.getName());
                }
            }
        }*/
        req.getSession().setAttribute("hoursPlayedKeys", hoursPlayedYear.keySet());
        req.getSession().setAttribute("hoursPlayedValues", hoursPlayedYear.values());

        req.getSession().setAttribute("totalHoursPlayed", totalHours);
        /*
        req.getSession().setAttribute("gamesPlayedKeys", gamesPlayedYear.keySet());
        req.getSession().setAttribute("gamesPlayedValues", gamesPlayedYear.values());

        req.getSession().setAttribute("totalGamesPlayed", gamesPlayed.size());

        req.getSession().setAttribute("bestScoreName", bestScore.getFirst());
        req.getSession().setAttribute("bestScoreValue", bestScore.getSecond());*/

        RequestDispatcher rd = req.getRequestDispatcher("userStats.jsp");
        rd.forward(req,resp);
    }
}
