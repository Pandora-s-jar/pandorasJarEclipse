package persistence;

import model.Data;
import model.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    static private UserDAO instance = null;
    private PreparedStatement statement;

    private UserDAO() {}

    static public UserDAO getInstance(){
        if(instance == null)
            instance = new UserDAO();
        return instance;
    }

    public User getUserFromIdUser(int id)
    {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "SELECT * FROM public.user WHERE idUser = ?::integer";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,Integer.toString(id));
            ResultSet result = statement.executeQuery();
           if(result.isClosed())
                return null;
            User user = new User();
            while(result.next()) {
                user.setId(id);
                user.setUsername(result.getString("username"));
                user.setEmail(result.getString("email"));
                user.setDescription(result.getString("description"));
                user.setPassword(result.getString("password"));
            }
            ArrayList<User> friends = new ArrayList<User>();
            query = "SELECT u.* FROM public.user as u, public.user_friend as uf WHERE uf.iduser1 = ?::integer and u.iduser = uf.iduser2";
            statement = connection.prepareStatement(query);
            statement.setString(1,Integer.toString(id));
            result = statement.executeQuery();
            if(result.isClosed())
                return null;
            while(result.next())
            {
                User u = new User();
                u.setId(result.getInt("iduser"));
                u.setUsername(result.getString("username"));
                u.setEmail(result.getString("email"));
                u.setDescription(result.getString("description"));
                u.setPassword(result.getString("password"));
                friends.add(u);
            }
            user.setFriends(friends);
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
        return null;
    }

}
