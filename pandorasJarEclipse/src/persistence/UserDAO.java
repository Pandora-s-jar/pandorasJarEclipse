package persistence;

import model.Data;
import model.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    private PreparedStatement statement;

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
                user.setImage(result.getBytes("image"));
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
                u.setImage(result.getBytes("image"));
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

    public User getUserFromUsernameUser(String username)
    {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "SELECT * FROM public.user WHERE username = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,username);
            ResultSet result = statement.executeQuery();
            if(result.isClosed())
                return null;
            User user = new User();
            while(result.next()) {
                user.setId(result.getInt("iduser"));
                user.setUsername(result.getString("username"));
                user.setEmail(result.getString("email"));
                user.setDescription(result.getString("description"));
                user.setPassword(result.getString("password"));
                user.setImage(result.getBytes("image"));
            }
            ArrayList<User> friends = new ArrayList<User>();
            query = "SELECT u.* FROM public.user as u, public.user_friend as uf WHERE uf.iduser1 = ?::integer and u.iduser = uf.iduser2";
            statement = connection.prepareStatement(query);
            statement.setString(1,Integer.toString(user.getId()));
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
                u.setImage(result.getBytes("image"));
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

    public void changeUserDetails(User u) {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "UPDATE public.user SET username = ?, email = ?, description = ?, password = ?  WHERE iduser = ?::integer";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,u.getUsername());
            statement.setString(2,u.getEmail());
            statement.setString(3,u.getDescription());
            statement.setString(4,u.getPassword());
            statement.setString(5,Integer.toString(u.getId()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
    }

    public void addUserFriend(int friend, int main) {
        Connection connection = DataSource.getInstance().getConnection();
        int nextId = getFriendNextId(connection);
        String query = "INSERT INTO user_friend(iduser1,iduser2,id) values(?::integer,?::integer,?::integer)";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,Integer.toString(main));
            statement.setString(2,Integer.toString(friend));
            statement.setString(3,Integer.toString(nextId));
            statement.executeUpdate();

            nextId = getFriendNextId(connection);
            statement = connection.prepareStatement(query);
            statement.setString(1,Integer.toString(friend));
            statement.setString(2,Integer.toString(main));
            statement.setString(3,Integer.toString(nextId));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
    }

    private int getFriendNextId(Connection conn)
    {
        String query = "SELECT nextval('user_friend_sequence') AS id";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(query);
            ResultSet set = stmt.executeQuery();
            set.next();
            return set.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void changeProfileImageUser(int idUser, InputStream fileContent) {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "UPDATE public.user SET image = ? WHERE iduser = ?::integer";
        try {
            statement = connection.prepareStatement(query);
            statement.setBytes(1, fileContent.readAllBytes());
            statement.setString(2,Integer.toString(idUser));
            statement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
    }
}
