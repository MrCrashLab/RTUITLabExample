package ru.MrCrash.RtuItLabExample.DAO.Shops;

import org.springframework.jdbc.core.RowMapper;

import ru.MrCrash.RtuItLabExample.Models.Shop;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShopsMapper implements RowMapper<Shop> {

    @Override
    public Shop mapRow(ResultSet resultSet, int i) throws SQLException {
        Shop shop = new Shop(
                resultSet.getInt("id_shop"),
                resultSet.getString("name"),
                resultSet.getString("name"),
                resultSet.getString("name"));
        return shop;
    }
}
