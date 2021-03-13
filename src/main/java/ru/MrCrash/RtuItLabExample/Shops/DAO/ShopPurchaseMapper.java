package ru.MrCrash.RtuItLabExample.Shops.DAO;

import org.springframework.jdbc.core.RowMapper;
import ru.MrCrash.RtuItLabExample.Shops.Models.Shop;
import ru.MrCrash.RtuItLabExample.Shops.Models.ShopPurchase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShopPurchaseMapper implements RowMapper<ShopPurchase> {

    @Override
    public ShopPurchase mapRow(ResultSet resultSet, int i) throws SQLException {
        return new ShopPurchase(
                resultSet.getInt("id_shop"),
                resultSet.getInt("id_purchases"),
                resultSet.getString("name"),
                resultSet.getDouble("cost"),
                String.valueOf(resultSet.getDate("date")),
                resultSet.getString("category"),
                resultSet.getString("description"),
                resultSet.getInt("amount"));
    }
}
