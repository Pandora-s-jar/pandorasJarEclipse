package model;

import java.util.HashMap;

public class Game {
    private String name;
    private String developer;
    private Data release;
    private Data purchase;
    private HashMap<Integer, Float> ranking;

    public Game() {}

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

    public HashMap<Integer, Float> getRanking() {
        return ranking;
    }

    public void setRanking(HashMap<Integer, Float> ranking) {
        this.ranking = ranking;
    }
}
