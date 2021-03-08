package ru.MrCrash.RtuItLabExample.Models.Purchase;

public class PersonPurchase extends Purchase {
    private int idReceipt;

    public PersonPurchase(int idPerson,
                          int idProduct,
                          String name,
                          double cost,
                          String date,
                          String category,
                          int idReceipt) {
        super(idPerson, idProduct, name, cost, date, category);
        this.idReceipt = idReceipt;
    }

    public int getIdReceipt() {
        return idReceipt;
    }

    public void setIdReceipt(int idReceipt) {
        this.idReceipt = idReceipt;
    }
}