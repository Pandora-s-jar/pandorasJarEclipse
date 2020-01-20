package controller.gamesheet;

import com.google.gson.Gson;
import model.Game;
import model.Review;
import model.Score;
import model.User;
import persistence.DAOFactory;
import utility.Acquisto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

@WebServlet(value="/PaymentSuccess")
public class PaymentRegister extends HttpServlet
{
    Gson gson = new Gson();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getParameter("data");
        Acquisto acquisto = gson.fromJson(json, Acquisto.class);
        DAOFactory.getInstance().makePurchaseDAO().insertNewPurchase(acquisto);
        DAOFactory.getInstance().makeGameDAO().insertNewGameIntoLibrary(acquisto.getIdGame(), acquisto.getIdUser());
        RequestDispatcher rd = req.getRequestDispatcher("/library");
        rd.forward(req,resp);
    }
}
