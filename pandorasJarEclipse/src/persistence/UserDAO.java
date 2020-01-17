package persistence;

import model.Game;
import model.User;
import org.apache.commons.io.IOUtils;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    private PreparedStatement statement;

    private ArrayList<Game> getGames(Connection connection, User user) throws SQLException{
        ArrayList<Game> games = new ArrayList<>();
        String query = "SELECT g.* FROM public.libreria as l, public.game as g WHERE l.idgame = g.idgame AND l.iduser = ?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, user.getId());
        ResultSet rs = statement.executeQuery();
        while (rs.next()){
            Game g = new Game(rs.getInt("idgame"), rs.getString("name"), rs.getInt("developer"),
                    rs.getString("category"), rs.getString("helpemail"), rs.getDouble("price"),
                    rs.getString("paymentscoord"), rs.getString("description"), rs.getDate("release"), DAOFactory.getInstance().makeReviewDAO().getReviewsFromIdGame(rs.getInt("idgame")));
            games.add(g);
        }
        return games;
    }

    private ArrayList<User> getFriends(Connection connection, User user) throws SQLException{
        ArrayList<User> friends = new ArrayList<User>();
        String query = "SELECT u.* FROM public.user as u, public.user_friend as uf WHERE uf.iduser1 = ?::integer and u.iduser = uf.iduser2";
        statement = connection.prepareStatement(query);
        statement.setString(1,Integer.toString(user.getId()));
        ResultSet rs = statement.executeQuery();
        if(rs.isClosed())
            return null;
        while(rs.next())
        {
            User u = new User();
            u.setId(rs.getInt("iduser"));
            u.setUsername(rs.getString("username"));
            u.setEmail(rs.getString("email"));
            u.setDescription(rs.getString("description"));
            u.setPassword(rs.getString("password"));
            u.setImage(rs.getBytes("image"));
            friends.add(u);
        }
        return friends;
    }

    private User createUserWithFriends(Connection connection, ResultSet rs) throws SQLException{
        User user = new User();
        while(rs.next()) {
            user.setId(rs.getInt("iduser"));
            System.out.println(user.getId());
            if (user.getId() == 0) {
                return null;
            }
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setDescription(rs.getString("description"));
            user.setPassword(rs.getString("password"));
            user.setImage(rs.getBytes("image"));
        }
        user.setFriends(this.getFriends(connection,user));
        user.setLibrary(this.getGames(connection, user));
        return user;
    }

    public void insertUsert(User user){
        Connection connection = DataSource.getInstance().getConnection();
        String query = "INSERT INTO public.user (iduser,username,email,password,description) values(default,?,?,?,?)";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DataSource.getInstance().closeConnection();
        }
    }

    public User getUserByEmail(String email){
        Connection connection = DataSource.getInstance().getConnection();
        String query = "SELECT * FROM public.user WHERE email = ?";
        System.out.println(email);
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.isClosed())
                return null;
            User user = this.createUserWithFriends(connection, resultSet);
            return user;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DataSource.getInstance().closeConnection();
        }
        return new User();
    }

    public User getUserByIdUser(int id)
    {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "SELECT * FROM public.user WHERE idUser = ?::integer";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,Integer.toString(id));
            ResultSet result = statement.executeQuery();
           if(result.isClosed())
                return null;
            return this.createUserWithFriends(connection,result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
        return null;
    }

    public User getUserByUsernameUser(String username)
    {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "SELECT * FROM public.user WHERE username = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,username);
            ResultSet result = statement.executeQuery();
            if(result.isClosed())
                return null;
            return this.createUserWithFriends(connection,result);
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
            statement.setBytes(1, IOUtils.toByteArray(fileContent));
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
