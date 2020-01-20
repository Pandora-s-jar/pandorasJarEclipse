package controller.profile;

import model.SoldGames;
import persistence.DAOFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.TreeMap;

@WebServlet(value = "/devStats")
public class DevStats extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        int idUser = 1;

        SoldGames tempSG = DAOFactory.getInstance().makePurchaseDAO().getSoldGamesFromIdUser(idUser);
        TreeMap<Integer, Integer> soldGPerYear = tempSG.getSoldGPerYear();
        TreeMap<Integer, Double> earnedMoneyPerYear = tempSG.getEarnedMoneyPerYear();
        int totalSoldGames = 0;
        double totalMoneyEarned = 0;
        for(Integer year : soldGPerYear.keySet())
        {
            totalSoldGames++;
            totalMoneyEarned += earnedMoneyPerYear.get(year);
        }
        this.log(totalSoldGames + "\n" + totalMoneyEarned);
        req.getSession().setAttribute("soldGameKeys", soldGPerYear.keySet());
        req.getSession().setAttribute("soldGameValues", soldGPerYear.values());

        req.getSession().setAttribute("moneyEarnedKeys", earnedMoneyPerYear.keySet());
        req.getSession().setAttribute("moneyEarnedValues", earnedMoneyPerYear.values());

        req.getSession().setAttribute("totalSold", totalSoldGames);
        req.getSession().setAttribute("totalMoney", totalMoneyEarned);

        RequestDispatcher rd = req.getRequestDispatcher("devStats.jsp");
        rd.forward(req, resp);
    }
}
