package ru.MrCrash.RtuItLabExample.Shops.Controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.MrCrash.RtuItLabExample.Shops.DAO.ShopDAO;
import ru.MrCrash.RtuItLabExample.Shops.Models.Shop;
import ru.MrCrash.RtuItLabExample.Shops.Models.ShopPurchase;

@RestController
@RequestMapping("/shops")
public class ShopsController {
    private final Gson gson = new Gson();
    private final ShopDAO shopDAO;

    @Autowired
    public ShopsController(ShopDAO shopDAO) {
        this.shopDAO = shopDAO;
    }

    //Создание нового магазина
    @PostMapping()
    public String createNewShop(@RequestBody String shop) {
        return gson.toJson(shopDAO.createNewShop(gson.fromJson(shop, Shop.class)));
    }

    //Добаление товара в магазин
    @PostMapping("/{id}")
    public String addNewShopPurchase(@PathVariable int id, @RequestBody String purchase) {
        return gson.toJson(shopDAO.createNewPurchase(id, gson.fromJson(purchase, ShopPurchase.class)));
    }

    //Просмотр всех магазинов
    @GetMapping()
    public String readAllShops() {
        return gson.toJson(shopDAO.readAllShop());
    }

    //Просмотр товаров по id магазина
    @GetMapping("/{idShop}")
    public String readPurchasesFromShopId(@PathVariable int idShop) {
        return gson.toJson(shopDAO.readPurchaseFromShopId(idShop));
    }

    @GetMapping("/{idShop}/{idPurchase}")
    public String readPurchaseFromId(@PathVariable int idShop,
                                     @PathVariable int idPurchase) {
        return gson.toJson(shopDAO.readPurchaseFromId(idShop, idPurchase));
    }

    //Обновление информации о магазине
    @PatchMapping("/{id}")
    public String updateShopInfo(@PathVariable int id, @RequestBody String shopInfo) {
        return gson.toJson(shopDAO.updateShopInfo(gson.fromJson(shopInfo, Shop.class), id));
    }

    //Обновление информации о товарах в магазине
    @PatchMapping("/{idParent}/{idPurchase}")
    public String updatePurchaseInfo(@PathVariable int idParent,
                                     @PathVariable int idPurchase,
                                     String purchaseInfo) {
        return gson.toJson(shopDAO.updatePurchaseInfo(idParent,
                idPurchase,
                gson.fromJson(purchaseInfo, ShopPurchase.class)));
    }

    //Удаление магазина
    @DeleteMapping("/{idShop}")
    public String deleteShop(@PathVariable int idShop) {
        return gson.toJson(shopDAO.deleteShop(idShop));
    }

    //Удаление товара
    @DeleteMapping("/{idShop}/{idPurchase}")
    public String deletePurchase(@PathVariable int idShop,
                                 @PathVariable int idPurchase) {
        return gson.toJson(shopDAO.deletePurchase(idShop, idPurchase));
    }

}
