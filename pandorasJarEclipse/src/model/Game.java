package model;

import java.util.HashMap;

public class Game {
    private String name;
    private int developer;
    private double price;
    private Data release;
    private HashMap<String, Float> hoursPlayed; //year->hours
    private HashMap<Integer, Float> ranking;


    public Game(String name, int developer, Data release, double price, HashMap<String, Float> hoursPlayed, HashMap<Integer, Float> ranking) {
        this.name = name;
        this.developer = developer;
        this.release = release;
        this.price = price;
        this.hoursPlayed = hoursPlayed;
        this.ranking = ranking;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeveloper() {
        return developer;
    }

    public void setDeveloper(int developer) {
        this.developer = developer;
    }

    public Data getRelease() {
        return release;
    }

    public void setRelease(Data release) {
        this.release = release;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
