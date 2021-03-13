package ru.MrCrash.RtuItLabExample.Shops.DAO;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.MrCrash.RtuItLabExample.Shops.Models.Shop;
import ru.MrCrash.RtuItLabExample.Shops.Models.ShopPurchase;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShopDAO {
    private final JdbcTemplate jdbcTemplate;

    public ShopDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Создание нового магазина
    public Shop createNewShop(Shop shop) {
        int uniqueShopId = shop.getIdShop();
        List<Integer> shopsId =
                readAllShop()
                        .stream()
                        .map(shop1 -> shop1.getIdShop())
                        .collect(Collectors.toList());
        if (shopsId.size() == 0) {
            uniqueShopId = 1;
        } else if (shopsId.indexOf(uniqueShopId) != 1 || uniqueShopId <= 0) {
            uniqueShopId = shopsId.stream().max(Integer::compare).get();
            uniqueShopId++;
        }
        jdbcTemplate.update("INSERT INTO shops VALUES(?,?,?,?)",
                uniqueShopId,
                shop.getName(),
                shop.getAddress(),
                shop.getNumberPhone());
        shop.setIdShop(uniqueShopId);
        return shop;
    }

    //Добавление нового товара
    public ShopPurchase createNewPurchase(int idParent, ShopPurchase shopPurchase) {
        int uniquePurchaseId = shopPurchase.getIdPurchase();
        List<Integer> purchasesId =
                readPurchaseFromShopId(idParent)
                        .stream()
                        .map(shopPurchase1 -> shopPurchase1.getIdPurchase())
                        .collect(Collectors.toList());
        if (uniquePurchaseId <= 0 && purchasesId.size() == 0) {
            uniquePurchaseId = 1;
        } else if (uniquePurchaseId <= 0 || purchasesId.indexOf(uniquePurchaseId) != 1) {
            uniquePurchaseId = purchasesId.stream().max(Integer::compare).get();
            uniquePurchaseId++;
        }
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

    //Чтение всех магазинов
    public List<Shop> readAllShop() {
        return jdbcTemplate.query("SELECT * FROM shops", new ShopMapper());
    }

    //Чтение всех товаров в магазине
    public List<ShopPurchase> readPurchaseFromShopId(int idShop) {
        return jdbcTemplate.query("SELECT * FROM shop_purchases WHERE id_shop=?", new ShopPurchaseMapper(), idShop);

    }

    //Чтение товара по id
    public ShopPurchase readPurchaseFromId(int idShop, int idPurchase) {
        List<ShopPurchase> shopPurchases = jdbcTemplate
                .query("SELECT * FROM shop_purchases " +
                                "WHERE id_shop=? " +
                                "AND id_purchase=?",
                        new ShopPurchaseMapper(),
                        idShop,
                        idPurchase);
        return shopPurchases
                .stream()
                .findAny()
                .orElse(null);
    }

    //Обновление информации о магазине
    public Shop updateShopInfo(Shop shop, int id) {
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

    //Обновление информации о товаре
    public ShopPurchase updatePurchaseInfo(int idParent, int idPurchase, ShopPurchase purchase) {
        jdbcTemplate.update("UPDATE shop_purchases SET " +
                        "name=?," +
                        "date=?," +
                        "category=?," +
                        "description=?," +
                        "cost=?," +
                        "amount=? " +
                        "where id_shop=? " +
                        "AND id_purchase=?",
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

    //Удаление магазина со всеми его товарами
    public Shop deleteShop(int idShop) {
        List<Shop> shops = jdbcTemplate
                .query("SELECT * FROM shops " +
                                "WHERE id_shop=?"
                        , new ShopMapper()
                        , idShop);
        jdbcTemplate.update("DELETE FROM " +
                        "shop_purchases WHERE " +
                        "id_shop=?",
                idShop);
        jdbcTemplate.update("DELETE FROM " +
                        "shops WHERE " +
                        "id_shop=?",
                idShop);
        return shops.stream().findAny().orElse(null);
    }

    //Удаление товара
    public ShopPurchase deletePurchase(int idShop, int idPurchase) {
        ShopPurchase shopPurchase = readPurchaseFromId(idShop, idPurchase);
        jdbcTemplate.update("DELETE FROM " +
                        "shop_purchases WHERE " +
                        "id_shop=? " +
                        "AND id_purchase=?",
                idShop,
                idPurchase);
        return shopPurchase;
    }
}
