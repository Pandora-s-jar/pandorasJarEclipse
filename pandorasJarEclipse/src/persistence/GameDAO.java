package persistence;


import model.Game;
import model.User;
import utility.Acquisto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

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
            game.setReviews(DAOFactory.getInstance().makeReviewDAO().getReviewsFromIdGame(game.getId()));
            return game;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
        return null;
    }

    public Game getGameFromIdWithPreviews(int id)
    {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "SELECT * FROM public.game WHERE game.idgame = ?::integer;";
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
            query = "SELECT * FROM public.previewimg WHERE game = ?::integer;";
            statement = connection.prepareStatement(query);
            statement.setString(1,Integer.toString(id));
            result = statement.executeQuery();
            while(result.next()) {
                game.addPreviewIMG(result.getString("link"));
            }
            query = "SELECT * FROM public.previewvid WHERE game = ?::integer;";
            statement = connection.prepareStatement(query);
            statement.setString(1,Integer.toString(id));
            result = statement.executeQuery();
            while(result.next()) {
                game.addPreviewVID(result.getString("link"));
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


    /** if true selects only the front preview image **/
    public ArrayList<Game> getAllGamesFromCategory(String category) {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "SELECT game.idgame, previewimg.link FROM public.game, public.previewimg WHERE game.idgame = previewimg.game and previewimg.front = true and game.category = ?;";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, category);
            ResultSet result = statement.executeQuery();
            if(result.isClosed())
                return null;
            ArrayList<Game> gamesCategory = new ArrayList<Game>();
            while(result.next()) {
                Game game = new Game();
                game.setId(result.getInt("idgame"));
                game.setFrontImage(result.getString("link"));
                gamesCategory.add(game);
            }

            return gamesCategory;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
        return null;
    }

    public ArrayList<Game> getGamesFromNameLike(String gameName) {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "SELECT game.idgame, previewimg.link FROM public.game, public.previewimg WHERE game.idgame = previewimg.game and previewimg.front = true and game.name iLIKE ?;";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, "%"+gameName+"%");
            ResultSet result = statement.executeQuery();
            if(result.isClosed())
                return null;
            ArrayList<Game> games = new ArrayList<Game>();
            while(result.next()) {
                Game game = new Game();
                game.setId(result.getInt("idgame"));
                game.setFrontImage(result.getString("link"));
                games.add(game);
            }

            return games;

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
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DataSource.getInstance().closeConnection();
        }
    }

    public void insertPreview(int id, String imageName, boolean front){
        Connection connection = DataSource.getInstance().getConnection();
        String query = "INSERT INTO public.previewimg(idpreview, link, game, front) VALUES(default,?,?,?)";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,imageName);
            statement.setInt(2, id);
            statement.setBoolean(3, front);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DataSource.getInstance().closeConnection();
        }
    }

    public void insertVideoLink(int id, String link){
        Connection connection = DataSource.getInstance().getConnection();
        String query = "INSERT INTO public.previewvid(idpreview, link, game) VALUES(default,?,?)";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, link);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DataSource.getInstance().closeConnection();
        }
    }

    public void insertNewGameIntoLibrary(int game, int user) {
        Connection connection = DataSource.getInstance().getConnection();
        int nextId = getLibraryNextId(connection);
        String query = "INSERT INTO public.libreria values(?,?,?)";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1,user);
            statement.setInt(2, game);
            statement.setInt(3, nextId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
    }

    private int getLibraryNextId(Connection conn)
    {
        String query = "SELECT nextval('libreria_sequence') AS id";
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

    public boolean isGamePurchased(int gameId, int userId) {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "SELECT idgame FROM public.libreria WHERE iduser = ? and idgame = ?;";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setInt(2, gameId);
            ResultSet result = statement.executeQuery();
            return !result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
        return false;
    }
}
