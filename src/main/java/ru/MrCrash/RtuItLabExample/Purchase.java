package ru.MrCrash.RtuItLabExample;

public abstract class Purchase {
    private int idParent = 0;
    private int idPurchase  = 0;
    private String name;
    private double cost;
    private String date;
    private String category;
    private int amount;

    public Purchase(int idParent, int idPurchase, String name, double cost, String date, String category, int amount) {
        this.idParent = idParent;
        this.idPurchase = idPurchase;
        this.name = name;
        this.cost = cost;
        this.date = date;
        this.category = category;
        this.amount = amount;
    }

    public int getIdParent() {
        return idParent;
    }

    public void setIdParent(int idParent) {
        this.idParent = idParent;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
