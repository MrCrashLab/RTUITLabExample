package ru.MrCrash.RtuItLabExample.DAO.PersonPurchases;

import org.springframework.jdbc.core.RowMapper;
import ru.MrCrash.RtuItLabExample.Models.Purchase.PersonPurchase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonPurchasesMapper implements RowMapper<PersonPurchase> {
    @Override
    public PersonPurchase mapRow(ResultSet resultSet, int i) throws SQLException {
        return new PersonPurchase(
                resultSet.getInt("id_person"),
                resultSet.getInt("id_purchase"),
                resultSet.getString("name"),
                resultSet.getDouble("cost"),
                String.valueOf(resultSet.getDate("date")),
                resultSet.getString("category"),
                resultSet.getInt("id_receipt"),
                resultSet.getString("payment_method"));
    }
}
