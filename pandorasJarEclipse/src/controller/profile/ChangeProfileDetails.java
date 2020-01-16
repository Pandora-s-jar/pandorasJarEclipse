package controller.profile;

import model.User;
import persistence.DAOFactory;
import persistence.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeProfileDetails  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = (int) req.getSession().getAttribute("userId");
        User u = DAOFactory.getInstance().makeUserDAO().getUserByIdUser(userId);
        int change = Integer.parseInt(req.getParameter("change"));
        if(change == 1)
        {
            String username = req.getParameter("inputUsername");
            String email = req.getParameter("inputEmail");
            String description = req.getParameter("inputDescription");
            String password = req.getParameter("inputPassword");

            if(username != null && !username.equals(u.getUsername()))
                u.setUsername(username);
            if(email != null && !email.equals(u.getEmail()))
                u.setEmail(email);
            if(password != null && !password.equals(u.getPassword()))
                u.setPassword(password);
            if(description != null && !description.equals(u.getDescription()))
                u.setDescription(description);
            DAOFactory.getInstance().makeUserDAO().changeUserDetails(u);
        }
        else if(change == 2)
        {
            String newFriend = req.getParameter("nameFriend");
            UserDAO dao = DAOFactory.getInstance().makeUserDAO();
            User friend = dao.getUserByUsernameUser(newFriend);
            if(friend != null)
            {
                if(u.addFriend(friend))
                {
                    dao.addUserFriend(friend.getId(), u.getId());
                }
            }
        }
        resp.sendRedirect("profile");
    }
}
