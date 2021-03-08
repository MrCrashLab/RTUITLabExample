package ru.MrCrash.RtuItLabExample.DAO.Shops;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.MrCrash.RtuItLabExample.DAO.Purchases.PurchasesMapper;
import ru.MrCrash.RtuItLabExample.Models.Purchase;
import ru.MrCrash.RtuItLabExample.Models.Shop;

import java.util.List;

@Component
public class ShopsDAO {
    private final JdbcTemplate jdbcTemplate;

    public ShopsDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Shop createNewShop(Shop shop){
        jdbcTemplate.update("INSERT INTO shops VALUES(?,?,?,?)",
                shop.getIdShop(),
                shop.getName(),
                shop.getAddress(),
                shop.getNumberPhone());
        return shop;
    }

    public List<Shop> readAllShop(){
        return jdbcTemplate.query("SELECT * FROM shops", new ShopsMapper());
    }

    public Shop readShopFromId(int idShop){
        return readAllShop()
                .stream()
                .filter(shop ->shop.getIdShop()==idShop)
                .findAny()
                .orElse(null);
    }

    public Shop updateShopInfo(Shop shop){
        jdbcTemplate.update("UPDATE shops SET " +
                        "name=?, " +
                        "address=?, " +
                        "number_phone=? " +
                        "WHERE id_shop=?",
                shop.getName(),
                shop.getAddress(),
                shop.getNumberPhone());
        return shop;
    }

    public Shop deleteShop(int idShop){
        Shop shop = readShopFromId(idShop);
        jdbcTemplate.update("DELETE FROM " +
                        "shops WHERE " +
                        "id_shop=?",
                idShop);
        return shop;
    }
}
