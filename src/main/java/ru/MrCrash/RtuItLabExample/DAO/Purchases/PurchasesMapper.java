package ru.MrCrash.RtuItLabExample.DAO.Purchases;

import org.springframework.jdbc.core.RowMapper;
import ru.MrCrash.RtuItLabExample.Models.Purchase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PurchasesMapper implements RowMapper<Purchase> {
    @Override
    public Purchase mapRow(ResultSet resultSet, int i) throws SQLException {
        Purchase purchase = new Purchase(resultSet.getInt("id_person"),
                resultSet.getInt("id_purchase"),
                resultSet.getString("name"),
                resultSet.getDouble("cost"),
                String.valueOf(resultSet.getDate("date")));
        return purchase;
    }
}
