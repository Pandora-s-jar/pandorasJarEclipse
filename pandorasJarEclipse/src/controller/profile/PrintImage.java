package controller.profile;

import model.User;
import persistence.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/PrintImage")
public class PrintImage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("id"));
        User u = DAOFactory.getInstance().makeUserDAO().getUserByIdUser(userId);
        byte[] imageBytes = u.getImage();

        resp.setContentType("image/jpeg");

        resp.setContentLength(imageBytes.length);

        resp.getOutputStream().write(imageBytes);
    }
}
