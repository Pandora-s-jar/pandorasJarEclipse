package controller.library;

import com.google.gson.Gson;
import model.Score;
import persistence.DAOFactory;
import utility.Pair;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(value = "/getRank", name = "getRank")
public class GetRank extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Score> ranks = DAOFactory.getInstance().makeScoreDAO().getScoresFromIdGame(Integer.parseInt(req.getParameter("id")));
        Gson gson = new Gson();
        String json = gson.toJson(ranks);
        PrintWriter printWriter = resp.getWriter();
        resp.setContentType("application/json");
        printWriter.print(json);
        printWriter.flush();
        printWriter.close();
        resp.setStatus(201);
    }
}
