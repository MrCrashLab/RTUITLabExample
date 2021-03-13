package ru.MrCrash.RtuItLabExample.Shops.DAO;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.MrCrash.RtuItLabExample.Shops.Models.Shop;
import ru.MrCrash.RtuItLabExample.Shops.Models.ShopPurchase;

import java.util.List;

@Component
public class ShopDAO {
    private final JdbcTemplate jdbcTemplate;
    public ShopDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //TODO:Изменить валидацию и сделать инкрементирование уникального ид
    public Shop createNewShop(Shop shop){
        jdbcTemplate.update("INSERT INTO shops VALUES(?,?,?,?)",
                shop.getIdShop(),
                shop.getName(),
                shop.getAddress(),
                shop.getNumberPhone());
        return shop;
    }

    public List<Shop> readAllShop(){
        return jdbcTemplate.query("SELECT * FROM shops", new ShopMapper());
    }

    public List<ShopPurchase> readPurchaseFromShopId(int idShop){
        return jdbcTemplate.query("SELECT * FROM shop_purchases WHERE id_shop=?", new ShopPurchaseMapper(), idShop);

    }

    public Shop updateShopInfo(Shop shop, int id){
        jdbcTemplate.update("UPDATE shops SET " +
                        "name=?, " +
                        "address=?, " +
                        "number_phone=? " +
                        "WHERE id_shop=?",
                shop.getName(),
                shop.getAddress(),
                shop.getNumberPhone(),
                id);
        shop.setIdShop(id);
        return shop;
    }

    public Shop deleteShop(int idShop){
        Shop shop = (Shop) jdbcTemplate.query("SELECT FROM shops WHERE id_shop=?",new ShopMapper(),idShop);
        jdbcTemplate.update("DELETE FROM " +
                        "shop_purchases WHERE " +
                        "id_shop=?",
                idShop);
        jdbcTemplate.update("DELETE FROM " +
                        "shops WHERE " +
                        "id_shop=?",
                idShop);
        return shop;
    }

    public ShopPurchase addNewPurchase(int idParent, ShopPurchase shopPurchase) {
        int uniquePurchaseId = 1;
        jdbcTemplate.update("INSERT INTO shop_purchases VALUES(?,?,?,?,?,?,?,?)",
                idParent,
                uniquePurchaseId,
                shopPurchase.getName(),
                java.sql.Date.valueOf(shopPurchase.getDate()),
                shopPurchase.getCategory(),
                shopPurchase.getDescription(),
                shopPurchase.getCost(),
                shopPurchase.getAmount());
        shopPurchase.setIdParent(idParent);
        shopPurchase.setIdPurchase(uniquePurchaseId);
        return shopPurchase;
    }

    public ShopPurchase updatePurchaseInfo(int idParent,int idPurchase, ShopPurchase purchase){
        jdbcTemplate.update("UPDATE shop_purchases SET " +
                "name=?," +
                "date=?," +
                "category=?," +
                "description=?," +
                "cost=?," +
                "amount=? " +
                "where id_shop=? " +
                "AND id_purchases=?",
                purchase.getName(),
                java.sql.Date.valueOf(purchase.getDate()),
                purchase.getCategory(),
                purchase.getDescription(),
                purchase.getCost(),
                purchase.getAmount(),
                idParent,
                idPurchase);
        purchase.setIdParent(idParent);
        purchase.setIdPurchase(idPurchase);
        return purchase;
    }

    public ShopPurchase deletePurchase(int idShop, int idPurchase) {

        return null;
    }
}
