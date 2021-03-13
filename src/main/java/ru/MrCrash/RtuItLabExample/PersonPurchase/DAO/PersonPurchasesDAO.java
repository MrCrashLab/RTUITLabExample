package ru.MrCrash.RtuItLabExample.PersonPurchase.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.MrCrash.RtuItLabExample.PersonPurchase.Models.PersonPurchase;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonPurchasesDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonPurchasesDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    //Получение всех покупок
    public List<PersonPurchase> getAllPurchases() {
        return jdbcTemplate.query("SELECT * FROM person_purchases", new PersonPurchasesMapper());
    }
    //Получение покупок по id человека
    public List<PersonPurchase> getPurchasesFromPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM person_purchases WHERE id_person=?", new PersonPurchasesMapper(), id);
    }
    //Получение определенной покупки по id
    public PersonPurchase getPurchaseFromId(int idPerson, int idPurchase) {
        List<PersonPurchase> personPurchases = jdbcTemplate.query(
                "SELECT * FROM person_purchases " +
                "WHERE id_person=? " +
                "AND id_purchase=?",
                new PersonPurchasesMapper(),
                idPerson,
                idPurchase);
        return personPurchases
                .stream()
                .findAny()
                .orElse(null);
    }
    //Создание новой покупки
    //TODO:Изменить валидацию и сделать инкрементирование уникального ид
    public PersonPurchase createNewPurchase(PersonPurchase purchase) {
        int uniquePurchaseId = purchase.getIdPurchase();
        int uniquePersonId = purchase.getIdParent();
        List<Integer> purchaseIdList = getAllPurchases()
                .stream()
                .map(purchase1 -> purchase1.getIdPurchase())
                .collect(Collectors.toList());
        List<Integer> personIdList = getAllPurchases()
                .stream()
                .map(purchase1 -> purchase1.getIdParent())
                .collect(Collectors.toList());
        if (uniquePurchaseId <= 0 && purchaseIdList.size()==0) {
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
        jdbcTemplate.update("INSERT INTO person_purchases VALUES(?,?,?,?,?,?,?,?,?)",
                uniquePersonId,
                uniquePurchaseId,
                purchase.getIdReceipt(),
                purchase.getName(),
                purchase.getCost(),
                java.sql.Date.valueOf(purchase.getDate()),
                purchase.getCategory(),
                purchase.getPaymentMethod(),
                purchase.getAmount());
        purchase.setIdPurchase(uniquePurchaseId);
        purchase.setIdParent(uniquePersonId);
        return purchase;
    }
    //Обновление информации о покупке
    public PersonPurchase updatePurchase(int idPerson, int idPurchase, PersonPurchase purchase) {
        jdbcTemplate.update("UPDATE person_purchases SET " +
                        "name=?, " +
                        "cost=?, " +
                        "date=?," +
                        "category=?," +
                        "id_receipt=?," +
                        "payment_method=?," +
                        "amount=? WHERE" +
                        " id_person=? AND " +
                        "id_purchase=?",
                purchase.getName(),
                purchase.getCost(),
                java.sql.Date.valueOf(purchase.getDate()),
                purchase.getCategory(),
                purchase.getIdReceipt(),
                purchase.getPaymentMethod(),
                purchase.getAmount(),
                idPerson,
                idPurchase);
        purchase.setIdParent(idPerson);
        purchase.setIdPurchase(idPurchase);
        return purchase;
    }
    //Удаление покупки
    public PersonPurchase deletePurchase(int idPersonToDel, int idPurchaseToDel) {
        List<PersonPurchase> purchases = getPurchasesFromPersonId(idPersonToDel);
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
    //Удаление человека со всеми покупками
    public List<PersonPurchase> deletePerson(int idPerson) {
        List<PersonPurchase> purchases = getPurchasesFromPersonId(idPerson);
        jdbcTemplate.update("DELETE FROM " +
                        "person_purchases WHERE " +
                        "id_person=?",
                idPerson);
        return purchases;
    }
}
