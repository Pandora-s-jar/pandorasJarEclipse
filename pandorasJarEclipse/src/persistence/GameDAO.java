package persistence;


import model.Game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDAO {
    private PreparedStatement statement;

    public Game getGameFromId(int id)
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
                game.setName(result.getString("name"));
                game.setRelease(result.getDate("release"));
                game.setDescription(result.getString("description"));
                game.setCategory(result.getString("category"));
                game.setHelpEmail(result.getString("helpemail"));
                game.setIdDeveloper(result.getInt("developer"));
                game.setPayment(result.getString("paymentscoord"));
                game.setPrice(result.getDouble("price"));
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


}
