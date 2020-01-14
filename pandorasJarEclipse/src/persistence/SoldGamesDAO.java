package persistence;

import model.Game;
import model.SoldGames;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class SoldGamesDAO
{
    static private SoldGamesDAO instance = null;
    private PreparedStatement statement;

    public SoldGamesDAO() {}

    public SoldGames getSoldGamesFromIdUser(int id)
    {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "SELECT * FROM public.purchase;";
        try {
            statement = connection.prepareStatement(query);
            //statement.setString(1,Integer.toString(id));
            ResultSet result = statement.executeQuery();
            if(result.isClosed())
                return null;
            SoldGames soldGames = new SoldGames();
            TreeMap<Integer,Integer> soldGPerYear = new TreeMap<Integer,Integer>();
            TreeMap<Integer,Double> earnedMoneyPerYear = new TreeMap<Integer,Double>();
            int idGame = 0;
            double currentPrice = 0;
            while(result.next())
            {
                idGame = result.getInt("game");
                System.out.println(idGame);
                //String queryDate = "SELECT EXTRACT(YEAR FROM " + result.getString("date") + ");";
                //PreparedStatement statementDate = connection.prepareStatement(queryDate);
                //ResultSet resultDate = statementDate.executeQuery();
                int currentYear = 2018;//resultDate.getInt(1);
                System.out.println(currentYear);
                String queryGame = "SELECT * FROM public.game WHERE idGame = " + idGame + " AND developer = " + id + ";";
                PreparedStatement statementGame = connection.prepareStatement(queryGame);
                ResultSet resultGame = statementGame.executeQuery();
                if(resultGame.next())
                {
                    soldGPerYear.put(currentYear, 1);//soldGPerYear.get(currentYear) + 1);
                    currentPrice += resultGame.getDouble("price");
                    earnedMoneyPerYear.put(currentYear, currentPrice);//earnedMoneyPerYear.get(currentYear) + currentPrice);
                }
            }
            soldGames.setSoldGPerYear(soldGPerYear);
            soldGames.setEarnedMoneyPerYear(earnedMoneyPerYear);
            return soldGames;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
        return null;
    }

}