package model;

import java.util.HashMap;

public class Game {
    private String name;
    private String developer;
    private Data release;
    private Data purchase;
    private HashMap<String, Float> hoursPlayed; //year->hours
    private HashMap<Integer, Float> ranking;


    public Game(String name, String developer, Data release, Data purchase, HashMap<String, Float> hoursPlayed, HashMap<Integer, Float> ranking) {
        this.name = name;
        this.developer = developer;
        this.release = release;
        this.purchase = purchase;
        this.hoursPlayed = hoursPlayed;
        this.ranking = ranking;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public Data getRelease() {
        return release;
    }

    public void setRelease(Data release) {
        this.release = release;
    }

    public Data getPurchase() {
        return purchase;
    }

    public void setPurchase(Data purchase) {
        this.purchase = purchase;
    }

    public HashMap<String, Float> getHoursPlayed() {
        return hoursPlayed;
    }

    public void setHoursPlayed(HashMap<String, Float> hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
    }

    public HashMap<Integer, Float> getRanking() {
        return ranking;
    }

    public void setRanking(HashMap<Integer, Float> ranking) {
        this.ranking = ranking;
    }
}
