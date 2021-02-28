package ru.MrCrash.RtuItLabExample.DAO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.MrCrash.RtuItLabExample.Moduls.Purchase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PurchaseDAO {
    @Value("${spring.datasource.url}")
    private final String URL = null;
    @Value("${spring.datasource.password}")
    private final String PASSWORD = null;
    @Value("${spring.datasource.password}")
    private final String USERNAME = null;
    List<Purchase> purchases = new ArrayList<>();

    {
        purchases.add(new Purchase(1,21,"Tea",1345,"12/08/1975"));
        purchases.add(new Purchase(1,651,"Coffe",23345,"12/08/1975"));
        purchases.add(new Purchase(2,761,"Cookies",15,"12/08/1975"));
        purchases.add(new Purchase(3,861,"Cola",13,"12/08/1975"));
    }

    public List<Purchase> getAllPurchases(){
        return purchases;
    }

    public List<Purchase> getPurchasesFromId(int id){
        return purchases.stream().filter(purchase -> purchase.getIdPerson() == id).collect(Collectors.toList());
    }

    public void createNewPurchase(Purchase purchase){
        purchases.add(purchase);
    }

    public void updatePurchase(int idPerson, int idPurchase, Purchase purchase){
        Purchase purchaseToBeUpdate = getPurchasesFromId(idPerson)
                .stream()
                .filter(purchase1 -> purchase1.getIdPurchase()==idPurchase)
                .findAny()
                .orElse(null);
        purchaseToBeUpdate.setIdPurchase(purchase.getIdPurchase());
        purchaseToBeUpdate.setName(purchase.getName());
        purchaseToBeUpdate.setCost(purchase.getCost());
        purchaseToBeUpdate.setPurchaseDate(purchase.getPurchaseDate());
    }

    public void deletePurchase(int idPerson,int idPurchase){
        purchases.remove(purchases.indexOf(getPurchasesFromId(idPerson)
                .stream()
                .filter(purchase1 -> purchase1.getIdPurchase()==idPurchase)
                .findAny()
                .orElse(null)));
    }

    public void deletePerson(int idPerson){
        getPurchasesFromId(idPerson).forEach(purchases::remove);
    }
}
