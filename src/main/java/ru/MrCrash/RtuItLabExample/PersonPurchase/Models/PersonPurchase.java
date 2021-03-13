package ru.MrCrash.RtuItLabExample.PersonPurchase.Models;

import ru.MrCrash.RtuItLabExample.Purchase;

public class PersonPurchase extends Purchase {
    private int idReceipt = 0;
    private String paymentMethod;

    public PersonPurchase(int idPerson,
                          int idProduct,
                          String name,
                          double cost,
                          String date,
                          String category,
                          int idReceipt,
                          String paymentMethod,
                          int amount) {
        super(idPerson, idProduct, name, cost, date, category, amount);
        this.idReceipt = idReceipt;
        this.paymentMethod = paymentMethod;
    }

    public int getIdReceipt() {
        return idReceipt;
    }

    public void setIdReceipt(int idReceipt) {
        this.idReceipt = idReceipt;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}