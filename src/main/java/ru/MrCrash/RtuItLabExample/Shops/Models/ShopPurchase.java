package ru.MrCrash.RtuItLabExample.Shops.Models;

import ru.MrCrash.RtuItLabExample.Purchase;

public class ShopPurchase extends Purchase {
    private String description;

    public ShopPurchase(int idParent,
                        int idPurchase,
                        String name,
                        double cost,
                        String date,
                        String category,
                        String description,
                        int amount) {
        super(idParent, idPurchase, name, cost, date, category, amount);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
