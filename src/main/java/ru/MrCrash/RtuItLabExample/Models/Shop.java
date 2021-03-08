package ru.MrCrash.RtuItLabExample.Models;

public class Shop {
    private int idShop;
    private String name;
    private String address;
    private String numberPhone;

    public Shop() {
    }

    public Shop(int idShop,
                String name,
                String address,
                String numberPhone) {
        this.idShop = idShop;
        this.name = name;
        this.address = address;
        this.numberPhone = numberPhone;
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
}
