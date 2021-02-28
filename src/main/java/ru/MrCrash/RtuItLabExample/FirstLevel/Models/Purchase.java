package ru.MrCrash.RtuItLabExample.FirstLevel.Models;

public class Purchase {
    private int idPerson;
    private int idPurchase;
    private String name;
    private double cost;
    private String date;

    public Purchase(int idPerson, int idProduct, String name, double cost, String purchaseDate) {
        this.idPerson = idPerson;
        this.idPurchase = idProduct;
        this.name = name;
        this.cost = cost;
        this.date = purchaseDate;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public int getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(int idPurchase) {
        this.idPurchase = idPurchase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}