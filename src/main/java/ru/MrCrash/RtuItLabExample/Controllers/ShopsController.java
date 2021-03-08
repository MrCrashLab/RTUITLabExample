package ru.MrCrash.RtuItLabExample.Controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.MrCrash.RtuItLabExample.DAO.Shops.ShopsDAO;
import ru.MrCrash.RtuItLabExample.Models.Shop;

@RestController
@RequestMapping("/shops")
public class ShopsController {
    private final Gson gson = new Gson();
    private final ShopsDAO shopsDAO;

    @Autowired
    public ShopsController(ShopsDAO shopsDAO) {
        this.shopsDAO = shopsDAO;
    }

    //Создание нового магазина
    @PostMapping()
    public String createNewShop(@RequestBody String json){
        return gson.toJson(shopsDAO.createNewShop(gson.fromJson(json, Shop.class)));
    }
    //Просмотр всех магазинов
    @GetMapping
    public String readAllShops(){
        return gson.toJson(shopsDAO.readAllShop());
    }
    //Вывод магазина по id
    @GetMapping("/{idShop}")
    public String readShopsFromId(@PathVariable int idShop){
        return gson.toJson(shopsDAO.readShopFromId(idShop));
    }
    //Обновление информации о магазине
    @PatchMapping()
    public String updateShopInfo(@RequestBody String json){
        return gson.toJson(shopsDAO.updateShopInfo(gson.fromJson(json,Shop.class)));
    }
    //Удаление магазина
    @DeleteMapping("/{idShop}")
    public String deleteShop(@PathVariable int idShop){
        return gson.toJson(shopsDAO.deleteShop(idShop));
    }



}
