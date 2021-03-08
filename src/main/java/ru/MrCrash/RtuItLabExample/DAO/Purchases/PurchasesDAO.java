package ru.MrCrash.RtuItLabExample.DAO.Purchases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.MrCrash.RtuItLabExample.Models.Purchase;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PurchasesDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PurchasesDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Purchase> getAllPurchases() {
        return jdbcTemplate.query("SELECT * FROM person_purchases", new PurchasesMapper());
    }

    public List<Purchase> getPurchasesFromId(int id) {
        return jdbcTemplate.query("SELECT * FROM person_purchases WHERE id_person=?", new PurchasesMapper(), id);
    }

    public Purchase createNewPurchase(Purchase purchase) {
        int uniquePurchaseId = purchase.getIdPurchase();
        int uniquePersonId = purchase.getIdPerson();
        List<Integer> purchaseIdList = getPurchasesFromId(purchase.getIdPerson())
                .stream()
                .map(purchase1 -> purchase1.getIdPurchase())
                .collect(Collectors.toList());
        List<Integer> personIdList = getAllPurchases()
                .stream()
                .map(purchase1 -> purchase1.getIdPerson())
                .collect(Collectors.toList());

        if (uniquePurchaseId <= 0 && purchaseIdList.size() == 0) {
            uniquePurchaseId = 1;
        }
        else if (purchaseIdList.indexOf(uniquePurchaseId) != -1||uniquePurchaseId <= 0) {
            uniquePurchaseId = purchaseIdList.stream().max(Integer::compare).get();
            uniquePurchaseId++;
        }

        if(uniquePersonId <= 0 && personIdList.size()==0){
            uniquePersonId=1;
        }
        else if (uniquePersonId <= 0) {
            uniquePersonId = personIdList.stream().max(Integer::compare).get();
            uniquePersonId++;
        }
        jdbcTemplate.update("INSERT INTO person_purchases VALUES(?,?,?,?,?,?)",
                uniquePersonId,
                uniquePurchaseId,
                purchase.getName(),
                purchase.getCost(),
                java.sql.Date.valueOf(purchase.getDate()),
                purchase.getCategory());
        purchase.setIdPurchase(uniquePurchaseId);
        purchase.setIdPerson(uniquePersonId);
        return purchase;
    }

    public Purchase updatePurchase(Purchase purchase) {
        jdbcTemplate.update("UPDATE person_purchases SET " +
                        "name=?, " +
                        "cost=?, " +
                        "date=?," +
                        "category=? " +
                        "WHERE id_person=? AND " +
                        "id_purchase=?",
                purchase.getName(),
                purchase.getCost(),
                java.sql.Date.valueOf(purchase.getDate()),
                purchase.getIdPerson(),
                purchase.getIdPurchase(),
                purchase.getCategory());
        return purchase;
    }

    public Purchase deletePurchase(int idPersonToDel, int idPurchaseToDel) {
        List<Purchase> purchases = getPurchasesFromId(idPersonToDel);
        jdbcTemplate.update("DELETE FROM " +
                        "person_purchases WHERE " +
                        "id_person=? AND " +
                        "id_purchase=?",
                idPersonToDel,
                idPurchaseToDel);

        return purchases.stream()
                .filter(purchase -> purchase.getIdPurchase()==idPurchaseToDel)
                .findAny()
                .orElse(null);
    }

    public List<Purchase> deletePerson(int idPerson) {
        List<Purchase> purchases = getPurchasesFromId(idPerson);
        jdbcTemplate.update("DELETE FROM " +
                        "person_purchases WHERE " +
                        "id_person=?",
                idPerson);
        return purchases;
    }
}
