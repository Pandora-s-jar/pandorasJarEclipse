package controller;

import model.User;
import persistence.DBManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

public class ChangeProfileDetails  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = (int) req.getSession().getAttribute("userId");
        User u = DBManager.getInstance().getUser(userId);
        String username = req.getParameter("inputUsername");
        String email = req.getParameter("inputEmail");
        String description = req.getParameter("inputDescription");
        String password = req.getParameter("inputPassword");
        String newFriend = req.getParameter("nameFriend");

        //TODO: upload di una nuova immagine profilo

        if(username != null && !username.equals(u.getUsername()))
            u.setUsername(username);
        if(email != null && !email.equals(u.getEmail()))
            u.setEmail(email);
        if(password != null && !password.equals(u.getPassword()))
            u.setPassword(password);
        if(description != null && !description.equals(u.getDescription()))
            u.setDescription(description);

        if(newFriend != null)
        {
           User friend = DBManager.getInstance().getUser(newFriend);
           if(friend != null)
           {
               u.addFriend(friend);
           }
        }

        resp.sendRedirect("profile");

    }
}
