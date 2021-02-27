package ru.MrCrash.RtuItLabExample.Moduls;

import java.util.Date;

public class Purchase {
    private int idPerson;
    private int idProduct;
    private String name;
    private double cost;
    private Date purchaseDate;

    public Purchase(int idPerson, int idProduct, String name, double cost, Date purchaseDate) {
        this.idPerson = idPerson;
        this.idProduct = idProduct;
        this.name = name;
        this.cost = cost;
        this.purchaseDate = purchaseDate;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
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

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

}