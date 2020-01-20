package controller;

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
import java.util.TreeMap;

@WebServlet(value="")
public class Homepage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Game> bestSellers = DAOFactory.getInstance().makePurchaseDAO().getBestThreeSoldGames();
        req.setAttribute("firstGameBestSellers", bestSellers.get(0));
        req.setAttribute("secondGameBestSellers", bestSellers.get(1));
        req.setAttribute("thirdGameBestSellers", bestSellers.get(2));
        setGamesCategory("shooter", req);
        setGamesCategory("arcade", req);
        setGamesCategory("azione", req);
        setGamesCategory("avventura", req);
        RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
        rd.forward(req, resp);
    }

    private void setGamesCategory(String category, HttpServletRequest req)
    {
        ArrayList<Game> games = DAOFactory.getInstance().makeGameDAO().getAllGamesFromCategory(category);
        if(games == null)
            return;
        ArrayList<Integer> lengthGamesDiv6 = new ArrayList<Integer>();
        for (int i = 0; i < games.size() / 6; i++)
            lengthGamesDiv6.add(i);
        StringBuilder sb1 = new StringBuilder("lengthGamesDiv6");
        sb1.append(category);
        StringBuilder sb2 = new StringBuilder(category);
        sb2.append("Games");
        req.setAttribute(sb1.toString(), lengthGamesDiv6);
        req.setAttribute(sb2.toString(), games);
    }
}
