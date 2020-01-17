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
import java.util.ArrayList;
import java.util.Collections;

@WebServlet(value="/PaymentSuccess")
public class PaymentRegister extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Game game = (Game) req.getSession().getAttribute("game");
        System.out.println(game.getPrice());
        resp.sendRedirect("/help");
    }
}
