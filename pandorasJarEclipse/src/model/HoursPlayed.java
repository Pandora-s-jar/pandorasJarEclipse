package model;

import java.util.TreeMap;

public class HoursPlayed {
    private int idGame;
    private int idUser;
    private TreeMap<Integer, Integer> hoursPlayed; //year->hours

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

    public TreeMap<Integer, Integer> getHoursPlayed() {
        return hoursPlayed;
    }

    public void setHoursPlayed(TreeMap<Integer, Integer> hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
    }
}
