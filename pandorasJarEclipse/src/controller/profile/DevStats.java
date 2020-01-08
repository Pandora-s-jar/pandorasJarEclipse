package controller.profile;

import utility.Acquisto;
import model.User;
import persistence.DBManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(value = "/devStats")
public class DevStats extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //User user = DBManager.getInstance().getUser((int) req.getSession().getAttribute("userId"));
        User user = DBManager.getInstance().getUser(5);

        ArrayList<Acquisto> games = user.getSoldGames();
        int totalSoldGames = 0;
        double totalMoneyEarned = 0;
        SortedMap<String, Integer> soldGamePerYear = new TreeMap<String, Integer>();
        soldGamePerYear.put("2018", 0);
        soldGamePerYear.put("2019", 0);
        soldGamePerYear.put("2020", 0);
        SortedMap<String, Double> moneyEarnedPerYear = new TreeMap<String, Double>();
        moneyEarnedPerYear.put("2018", (double) 0);
        moneyEarnedPerYear.put("2019", (double) 0);
        moneyEarnedPerYear.put("2020", (double) 0);
        for(String year : soldGamePerYear.keySet())
        {
            for(Acquisto g: games)
            {
                if(g.getDataAcquisto().getYear().equals(year))
                {
                    totalSoldGames++;
                    totalMoneyEarned += g.getGame().getPrice();
                    soldGamePerYear.replace(year, soldGamePerYear.get(year) + 1);
                    moneyEarnedPerYear.replace(year, moneyEarnedPerYear.get(year) + g.getGame().getPrice());
                }
            }
        }
        req.getSession().setAttribute("soldGameKeys", soldGamePerYear.keySet());
        req.getSession().setAttribute("soldGameValues", soldGamePerYear.values());

        req.getSession().setAttribute("moneyEarnedKeys", moneyEarnedPerYear.keySet());
        req.getSession().setAttribute("moneyEarnedValues", moneyEarnedPerYear.values());

        req.getSession().setAttribute("totalSold", totalSoldGames);
        req.getSession().setAttribute("totalMoney", totalMoneyEarned);

        RequestDispatcher rd = req.getRequestDispatcher("devStats.jsp");
        rd.forward(req, resp);
    }
}
