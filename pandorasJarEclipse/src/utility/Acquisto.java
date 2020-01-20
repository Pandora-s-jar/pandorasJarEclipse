package utility;

import model.Game;

import java.util.Date;

public class Acquisto
{
    private int idGame;
    private int idUser;
    private double price;

    public Acquisto(int idGame, int idUser, double price) {
        this.idGame = idGame;
        this.idUser = idUser;
        this.price = price;
    }

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}

