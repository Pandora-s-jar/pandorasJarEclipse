package persistence;

import utility.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PurchaseDAO {
    static private PurchaseDAO instance = null;
    private PreparedStatement statement;

    private PurchaseDAO() {}

    static public PurchaseDAO getInstance(){
        if(instance == null)
            instance = new PurchaseDAO();
        return instance;
    }

    public TreeMap<Integer,Integer> getGamesYearFromIdUser(int id)
    {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "SELECT * FROM public.purchase WHERE purchase.user = ?::integer"; //va corretto
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,Integer.toString(id));
            ResultSet result = statement.executeQuery();
            if(result.isClosed())
                return null;
            TreeMap<Integer,Integer> gamesPlayed = new TreeMap<Integer,Integer>();
            ArrayList<Pair<Integer, Integer>> yearGames = new ArrayList<Pair<Integer, Integer>>();
            Set<Integer> years = new TreeSet<Integer>();
            Calendar calendar = Calendar.getInstance();
            while(result.next()) {
                calendar.setTime(result.getDate("date"));
                Pair<Integer, Integer> pair = new Pair<Integer,Integer>(calendar.get(Calendar.YEAR),result.getInt("game"));
                yearGames.add(pair);
                years.add(calendar.get(Calendar.YEAR));
            }
            for(Integer year : years)
            {
                int contGames = 0;
                for (Pair<Integer,Integer> pair: yearGames)
                {
                    if(pair.getFirst().equals(year))
                        contGames++;
                }
                gamesPlayed.put(year, contGames);
            }

            return gamesPlayed;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
        return null;
    }

}
