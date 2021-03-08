package ru.MrCrash.RtuItLabExample.Models.Purchase;

public class PersonPurchase extends Purchase {
    private int idReceipt;
    private String paymentMethod;

    public PersonPurchase(int idPerson,
                          int idProduct,
                          String name,
                          double cost,
                          String date,
                          String category,
                          int idReceipt,
                          String paymentMethod) {
        super(idPerson, idProduct, name, cost, date, category);
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