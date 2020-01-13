package persistence;

import utility.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ScoreDAO {
    private PreparedStatement statement;

    public ArrayList<Pair<Integer, String>> getScoresFromIdUser(int id)
    {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "SELECT score.value, game.name FROM public.score, public.game WHERE score.game = game.idgame and score.user = ?::integer";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,Integer.toString(id));
            ResultSet result = statement.executeQuery();
            if(result.isClosed())
                return null;
            ArrayList<Pair<Integer, String>> gamesScore = new ArrayList<Pair<Integer, String>>();
            while(result.next()) {
                gamesScore.add(new Pair<Integer,String>(result.getInt("value"), result.getString("name")));
            }
            return gamesScore;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
        return null;
    }

}
