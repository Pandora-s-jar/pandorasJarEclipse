package model;

import java.util.ArrayList;
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
    private String frontImage;
    private ArrayList<String> previewsIMG;
    private ArrayList<String> previewsVID;
    private ArrayList<Review> reviews;

    public Game() {
        previewsIMG = new ArrayList<String>();
        previewsVID = new ArrayList<String>();
        reviews = new ArrayList<Review>();
    }

    public Game(int id, String name, int idDeveloper, String category, String helpEmail, double price, String payment, String description, Date release, String frontImage, ArrayList<String> previewsIMG, ArrayList<String> previewsVID, ArrayList<Review> reviews) {
        this.id = id;
        this.name = name;
        this.idDeveloper = idDeveloper;
        this.category = category;
        this.helpEmail = helpEmail;
        this.price = price;
        this.payment = payment;
        this.description = description;
        this.release = release;
        this.frontImage = frontImage;
        this.previewsIMG = previewsIMG;
        this.previewsVID = previewsVID;
        this.reviews = reviews;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public String getFrontImage() {
        return frontImage;
    }

    public void setFrontImage(String frontImage) {
        this.frontImage = frontImage;
    }

    public void addPreviewIMG(String s)
    {
        this.previewsIMG.add(s);
    }

    public void addPreviewVID(String s)
    {
        this.previewsVID.add(s);
    }

    public ArrayList<String> getPreviewsIMG() {
        return previewsIMG;
    }

    public void setPreviewsIMG(ArrayList<String> previewsIMG) {
        this.previewsIMG = previewsIMG;
    }

    public ArrayList<String> getPreviewsVID() {
        return previewsVID;
    }

    public void setPreviewsVID(ArrayList<String> previewsVID) {
        this.previewsVID = previewsVID;
    }

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
