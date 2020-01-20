package controller.library;

import com.google.gson.Gson;
import model.Game;
import persistence.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/getGameLinks")
public class GetGameLinks extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        Game g = DAOFactory.getInstance().makeGameDAO().getGameFromIdWithPreviews(Integer.parseInt(req.getParameter("gameID")));
        ArrayList<String> preview = g.getPreviewsVID();
        String json = gson.toJson(preview);
        PrintWriter printWriter = resp.getWriter();
        resp.setContentType("application/json");
        printWriter.print(json);
        printWriter.flush();
        printWriter.close();
        resp.setStatus(201);
    }
}
