package ru.MrCrash.RtuItLabExample.Models.Purchase;

public class ShopPurchase extends Purchase {
    private String description;

    public ShopPurchase(int idParent,
                        int idPurchase,
                        String name,
                        double cost,
                        String date,
                        String category,
                        String description) {
        super(idParent, idPurchase, name, cost, date, category);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
