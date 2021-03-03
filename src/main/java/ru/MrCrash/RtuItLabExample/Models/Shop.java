package ru.MrCrash.RtuItLabExample.Models;

import java.util.List;

public class Shop {
    private int idShop;
    private String name;
    private String address;
    private String numberPhone;
    private List<Purchase> purchases;

    public Shop() {
    }

    public Shop(int idShop,
                String name,
                String address,
                String numberPhone,
                List<Purchase> purchases) {
        this.idShop = idShop;
        this.name = name;
        this.address = address;
        this.numberPhone = numberPhone;
        this.purchases = purchases;
    }

    public int getIdShop() {
        return idShop;
    }

    public void setIdShop(int idShop) {
        this.idShop = idShop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }
}
