package controller.library;

import com.google.gson.Gson;
import model.User;
import persistence.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/getUserForComment", name = "getUserForComment")
public class GetUserForComment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(401);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        this.log(Integer.toString(id));
        User user = DAOFactory.getInstance().makeUserDAO().getUserByIdUser(id);
        Gson gson = new Gson();
        String response = gson.toJson(user);
        PrintWriter printWriter = resp.getWriter();
        resp.setContentType("application/json");
        printWriter.print(response);
        printWriter.flush();
        printWriter.close();
        resp.setStatus(201);
    }

}
