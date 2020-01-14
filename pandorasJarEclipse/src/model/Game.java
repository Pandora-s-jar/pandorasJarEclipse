package model;

import java.util.HashMap;

public class Game {
    private String name;
    private String developer;
    private Data release;
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
}
