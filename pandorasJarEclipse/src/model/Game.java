package model;

import java.util.Date;
import java.util.HashMap;

public class Game {
    private int id;
    private String name;
    private int idDeveloper;
    private String category;
    private String helpEmail;
    private double price;
    private String payment;
    private String description;
    private Date release;
    public Game() {}

    public int getIdDeveloper() {
        return idDeveloper;
    }

    public void setIdDeveloper(int idDeveloper) {
        this.idDeveloper = idDeveloper;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getHelpEmail() {
        return helpEmail;
    }

    public void setHelpEmail(String helpEmail) {
        this.helpEmail = helpEmail;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
    }
}
