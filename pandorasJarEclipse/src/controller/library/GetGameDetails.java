package controller.library;

import com.google.gson.Gson;
import model.Game;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet(value = "/getGameDetails", name = "getGameDetails")
public class GetGameDetails extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        User user = (User) req.getSession().getAttribute("user");
        Game game = null;
        for(Game g : user.getLibrary()){
            if(g.getName().equals(name)){
                game = g;
                break;
            }
        }
        Gson gson = new Gson();
        String response = gson.toJson(game);
        PrintWriter printWriter = resp.getWriter();
        resp.setContentType("application/json");
        printWriter.print(response);
        printWriter.flush();
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(401);
    }

}
