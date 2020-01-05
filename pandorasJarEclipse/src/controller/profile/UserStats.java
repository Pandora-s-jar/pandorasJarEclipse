package controller.profile;

import model.Game;
import model.User;
import persistence.DBManager;
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
        //User user = DBManager.getInstance().getUser((int) req.getSession().getAttribute("userId"));
        User user = DBManager.getInstance().getUser(5);
        HashSet<String> gamesPlayed = new HashSet<String>();
        float totalHours = 0f;
        ArrayList<Game> game = user.getLibrary();
        SortedMap<String, Float> hoursPlayedYear = new TreeMap<String, Float>();
        hoursPlayedYear.put("2018", 0f);
        hoursPlayedYear.put("2019", 0f);
        hoursPlayedYear.put("2020", 0f);
        SortedMap<String, Integer> gamesPlayedYear = new TreeMap<String, Integer>();
        gamesPlayedYear.put("2018", 0);
        gamesPlayedYear.put("2019", 0);
        gamesPlayedYear.put("2020", 0);
        for(String year : hoursPlayedYear.keySet())
        {
            for(Game g: game)
            {
                hoursPlayedYear.replace(year, hoursPlayedYear.get(year) + g.getHoursPlayed().get(year));
                totalHours += g.getHoursPlayed().get(year);
                if(g.getHoursPlayed().get(year) > 0)
                {
                    gamesPlayedYear.replace(year, gamesPlayedYear.get(year) + 1);
                    gamesPlayed.add(g.getName());
                }
            }
        }

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
        }
        req.getSession().setAttribute("hoursePlayedKeys", hoursPlayedYear.keySet());
        req.getSession().setAttribute("hoursePlayedValues", hoursPlayedYear.values());

        req.getSession().setAttribute("totalHoursPlayed", totalHours);

        req.getSession().setAttribute("gamesPlayedKeys", gamesPlayedYear.keySet());
        req.getSession().setAttribute("gamesPlayedValues", gamesPlayedYear.values());

        req.getSession().setAttribute("totalGamesPlayed", gamesPlayed.size());

        req.getSession().setAttribute("bestScoreName", bestScore.getFirst());
        req.getSession().setAttribute("bestScoreValue", bestScore.getSecond());

        RequestDispatcher rd = req.getRequestDispatcher("userStats.jsp");
        rd.forward(req,resp);
    }
}
