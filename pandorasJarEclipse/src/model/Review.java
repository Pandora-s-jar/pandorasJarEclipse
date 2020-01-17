package model;

public class Review {
    private String username;
    private int author;
    private int idGame;
    private String stars;
    private String comment;

    public Review() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(int numStars) {
        if(numStars > 5)
            return;
        String newStar = "";
        StringBuilder sb = new StringBuilder(newStar);
        for(int i = 0; i < numStars; i++)
            sb.append("⭐");
        this.stars = sb.toString();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
