package controller.gamesheet;

import model.Game;
import model.Review;
import model.Score;
import persistence.DAOFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

@WebServlet(value="/GameDataSheet")
public class GameDataSheet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int gameId = Integer.parseInt(req.getParameter("gameId"));
        DAOFactory factory = DAOFactory.getInstance();
        Game game = factory.makeGameDAO().getGameFromIdWithPreviews(gameId);
        String usernameDeveloper = factory.makeUserDAO().getUserByIdUser(game.getIdDeveloper()).getUsername();
        ArrayList<Review> reviews = factory.makeReviewDAO().getReviewsFromIdGame(gameId);
        ArrayList<Score> scores = factory.makeScoreDAO().getScoresFromIdGame(gameId);
        if(req.getSession().getAttribute("userId") != null) {
            boolean canBuy = factory.makeGameDAO().isGamePurchased(gameId,(int) req.getSession().getAttribute("userId"));
            req.setAttribute("canBuy", canBuy);
        }
        sortScores(scores);
        if(scores.size() >= 10)
            scores = (ArrayList<Score>) scores.subList(0,9);

        ArrayList<Integer> totalSize = new ArrayList<Integer>();
        for(int i = 0; i < game.getPreviewsVID().size()+game.getPreviewsIMG().size();i++)
            totalSize.add(i);
        req.setAttribute("game", game);
        req.setAttribute("totalSize", totalSize);
        req.setAttribute("developer", usernameDeveloper);
        req.setAttribute("reviews", reviews);
        req.setAttribute("ranking", scores);
        RequestDispatcher rd = req.getRequestDispatcher("gameDataSheet.jsp");
        rd.forward(req, resp);
    }

    private void sortScores(ArrayList<Score> scores)
    {
        Collections.sort(scores, (a, b) -> a.getValue() > b.getValue() ? -1 : a.getUsername().compareTo(b.getUsername()));
    }
}
