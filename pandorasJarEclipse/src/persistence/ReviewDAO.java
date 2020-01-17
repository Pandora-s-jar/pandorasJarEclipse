package persistence;


import model.Game;
import model.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReviewDAO {
    private PreparedStatement statement;

    public ArrayList<Review> getReviewsFromIdGame(int id)
    {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "SELECT * FROM public.review WHERE game = ?::integer";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,Integer.toString(id));
            ResultSet result = statement.executeQuery();
            if(result.isClosed())
                return null;
            ArrayList<Review> reviews = new ArrayList<Review>();
            while(result.next()) {
                Review review = new Review();
                review.setIdGame(id);
                review.setUsername(result.getString("username"));
                review.setAuthor(result.getInt("author"));
                review.setComment(result.getString("comment"));
                review.setStars(result.getInt("stars"));
                reviews.add(review);
            }

            return reviews;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DataSource.getInstance().closeConnection();
        }
        return null;
    }


}
