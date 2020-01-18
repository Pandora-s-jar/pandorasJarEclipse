package persistence;


import model.Game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class GameDAO {
    private PreparedStatement statement;

    public Game getGameByName(String name){
        Connection connection = DataSource.getInstance().getConnection();
        String query = "SELECT * FROM public.game WHERE name = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            if(result.isClosed())
                return null;
            Game game = new Game();
            while(result.next()) {
                game.setId(result.getInt("idgame"));
                createGame(result, game);
            }

            return game;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
        return null;
    }

    private void createGame(ResultSet result, Game game) throws SQLException {
        game.setName(result.getString("name"));
        game.setRelease(result.getDate("release"));
        game.setDescription(result.getString("description"));
        game.setCategory(result.getString("category"));
        game.setHelpEmail(result.getString("helpemail"));
        game.setIdDeveloper(result.getInt("developer"));
        game.setPayment(result.getString("paymentscoord"));
        game.setPrice(result.getDouble("price"));
    }

    public Game getGameById(int id)
    {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "SELECT * FROM public.game WHERE idgame = ?::integer";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,Integer.toString(id));
            ResultSet result = statement.executeQuery();
            if(result.isClosed())
                return null;
            Game game = new Game();
            while(result.next()) {
                game.setId(id);
                createGame(result, game);
            }

            return game;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
        return null;
    }

    public void insertGame(int id, String name, int idDeveloper, String category, String helpEmail, double price, String payment, String description){
        Connection connection = DataSource.getInstance().getConnection();
        String query = "INSERT INTO public.game(idgame, name, price, description, helpemail, paymentscoord, developer, category, release) values(default,?,?,?,?,?,?,?,?)";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,name);
            statement.setDouble(2,price);
            statement.setString(3,description);
            statement.setString(4,helpEmail);
            statement.setString(5,payment);
            statement.setInt(6,idDeveloper);
            statement.setString(7,category);
            statement.setDate(8, new java.sql.Date(new Date().getTime()));
            System.out.println("SDAJLSDHALJDHAJDSASDKADH");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DataSource.getInstance().closeConnection();
        }
    }

}
