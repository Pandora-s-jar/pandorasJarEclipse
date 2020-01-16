package persistence;

import model.Game;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class HoursPlayedDAO {
    private PreparedStatement statement;

    public TreeMap<Integer, Integer> getHoursPlayedFromIdUser(int id)
    {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "SELECT * FROM public.hours_played WHERE hours_played.user = ?::integer";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,Integer.toString(id));
            ResultSet result = statement.executeQuery();
            if(result.isClosed())
                return null;
            TreeMap<Integer, Integer> hours = new TreeMap<Integer,Integer>();
            while(result.next()) {
                hours.put(result.getInt("year"), result.getInt("hours"));
            }

            return hours;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
        return null;
    }

}
