package ru.MrCrash.RtuItLabExample.DAO.PersonPurchases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.MrCrash.RtuItLabExample.Models.Purchase.PersonPurchase;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonPurchasesDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonPurchasesDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<PersonPurchase> getAllPurchases() {
        return jdbcTemplate.query("SELECT * FROM person_purchases", new PersonPurchasesMapper());
    }

    public List<PersonPurchase> getPurchasesFromId(int id) {
        return jdbcTemplate.query("SELECT * FROM person_purchases WHERE id_person=?", new PersonPurchasesMapper(), id);
    }

    public PersonPurchase createNewPurchase(PersonPurchase purchase) {
        int uniquePurchaseId = purchase.getIdPurchase();
        int uniquePersonId = purchase.getIdParent();
        List<Integer> purchaseIdList = getPurchasesFromId(purchase.getIdParent())
                .stream()
                .map(purchase1 -> purchase1.getIdPurchase())
                .collect(Collectors.toList());
        List<Integer> personIdList = getAllPurchases()
                .stream()
                .map(purchase1 -> purchase1.getIdParent())
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
        jdbcTemplate.update("INSERT INTO person_purchases VALUES(?,?,?,?,?,?,?)",
                uniquePersonId,
                uniquePurchaseId,
                purchase.getName(),
                purchase.getCost(),
                java.sql.Date.valueOf(purchase.getDate()),
                purchase.getCategory(),
                purchase.getIdReceipt());
        purchase.setIdPurchase(uniquePurchaseId);
        purchase.setIdParent(uniquePersonId);
        return purchase;
    }

    public PersonPurchase updatePurchase(PersonPurchase purchase) {
        jdbcTemplate.update("UPDATE person_purchases SET " +
                        "name=?, " +
                        "cost=?, " +
                        "date=?," +
                        "category=?," +
                        "id_receipt=? " +
                        "WHERE id_person=? AND " +
                        "id_purchase=?",
                purchase.getName(),
                purchase.getCost(),
                java.sql.Date.valueOf(purchase.getDate()),
                purchase.getIdParent(),
                purchase.getIdPurchase(),
                purchase.getCategory(),
                purchase.getIdReceipt());
        return purchase;
    }

    public PersonPurchase deletePurchase(int idPersonToDel, int idPurchaseToDel) {
        List<PersonPurchase> purchases = getPurchasesFromId(idPersonToDel);
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

    public List<PersonPurchase> deletePerson(int idPerson) {
        List<PersonPurchase> purchases = getPurchasesFromId(idPerson);
        jdbcTemplate.update("DELETE FROM " +
                        "person_purchases WHERE " +
                        "id_person=?",
                idPerson);
        return purchases;
    }
}
