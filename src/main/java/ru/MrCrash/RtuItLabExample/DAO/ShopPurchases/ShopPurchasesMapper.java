package ru.MrCrash.RtuItLabExample.DAO.ShopPurchases;

import org.springframework.jdbc.core.RowMapper;
import ru.MrCrash.RtuItLabExample.Models.Purchase.ShopPurchase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShopPurchasesMapper implements RowMapper<ShopPurchase> {
    @Override
    public ShopPurchase mapRow(ResultSet resultSet, int i) throws SQLException {
        return new ShopPurchase(
                resultSet.getInt("id_shop"),
                resultSet.getInt("id_purchase"),
                resultSet.getString("name"),
                resultSet.getDouble("cost"),
                String.valueOf(resultSet.getDate("date")),
                resultSet.getString("category"),
                resultSet.getString("description"));
    }
}
