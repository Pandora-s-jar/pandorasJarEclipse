package persistence;

import model.Game;
import model.HoursPlayed;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class HoursPlayedDAO {
    static private HoursPlayedDAO instance = null;
    private PreparedStatement statement;

    private HoursPlayedDAO() {}

    static public HoursPlayedDAO getInstance(){
        if(instance == null)
            instance = new HoursPlayedDAO();
        return instance;
    }

    public HoursPlayed getHoursPlayedFromIdUser(int id)
    {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "SELECT * FROM public.hours_played"; //va corretto
        try {
            statement = connection.prepareStatement(query);
            //statement.setString(1,Integer.toString(id));
            ResultSet result = statement.executeQuery();
            if(result.isClosed())
                return null;
            HoursPlayed hoursplayed = new HoursPlayed();
            TreeMap<Integer, Integer> hours = new TreeMap<Integer,Integer>();
            while(result.next()) {
                hoursplayed.setIdGame(result.getInt("game"));
                hoursplayed.setIdUser(id);
                hours.put(result.getInt("year"), result.getInt("hours"));
            }
            hoursplayed.setHoursPlayed(hours);

            return hoursplayed;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
        return null;
    }

}
