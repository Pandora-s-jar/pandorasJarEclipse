package persistence;

import model.SoldGames;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

public class SoldGamesDAO
{
    static private SoldGamesDAO instance = null;
    private PreparedStatement statement;

    public SoldGamesDAO() {}

    public SoldGames getSoldGamesFromIdUser(int id)
    {
        Connection connection = DataSource.getInstance().getConnection();
        TreeMap<Integer,Integer> soldGPerYear = new TreeMap<Integer,Integer>();
        TreeMap<Integer,Double> earnedMoneyPerYear = new TreeMap<Integer,Double>();
        String queryYears = "SELECT EXTRACT(YEAR FROM date) FROM public.purchase;";
        try
        {
            statement = connection.prepareStatement(queryYears);
            ResultSet result = statement.executeQuery();
            while(result.next())
            {
                soldGPerYear.put(Integer.valueOf(result.getString(1)), 0);
                earnedMoneyPerYear.put(Integer.valueOf(result.getString(1)), 0.0);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        String querySoldGames = "SELECT EXTRACT(YEAR FROM date), game FROM public.purchase;";
        try
        {
            statement = connection.prepareStatement(querySoldGames);
            ResultSet result = statement.executeQuery();
            if(result.isClosed())
                return null;
            SoldGames soldGames = new SoldGames();
            int idGame = 0;
            double currentPrice = 0;
            while(result.next())
            {
                idGame = result.getInt("game");
                String currentYear = result.getString(1);
                String queryGame = "SELECT * FROM public.game WHERE idGame = " + idGame + " AND developer = " + id + ";";
                PreparedStatement statementGame = connection.prepareStatement(queryGame);
                ResultSet resultGame = statementGame.executeQuery();
                if(resultGame.next())
                {
                    soldGPerYear.put(Integer.valueOf(currentYear), soldGPerYear.get(Integer.valueOf(currentYear)) + 1);
                    currentPrice += resultGame.getDouble("price");
                    earnedMoneyPerYear.put(Integer.valueOf(currentYear), earnedMoneyPerYear.get(Integer.valueOf(currentYear)) + currentPrice);
                }
            }
            soldGames.setSoldGPerYear(soldGPerYear);
            soldGames.setEarnedMoneyPerYear(earnedMoneyPerYear);
            return soldGames;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally
        {
            DataSource.getInstance().closeConnection();
        }
        return null;
    }

}