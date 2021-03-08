package ru.MrCrash.RtuItLabExample.Controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.MrCrash.RtuItLabExample.DAO.Purchases.PurchasesDAO;
import ru.MrCrash.RtuItLabExample.Models.Purchase;


@RestController
@RequestMapping("/purchases")
public class PurchasesController {
    private Gson gson = new Gson();
    private final PurchasesDAO purchasesDAO;
    @Autowired
    public PurchasesController(PurchasesDAO purchasesDAO) {
        this.purchasesDAO = purchasesDAO;
    }


    //Создание новой покупки
    @PostMapping()
    public String createNewPurchase(@RequestBody String json) {
        return gson.toJson(purchasesDAO.createNewPurchase(gson.fromJson(json,Purchase.class)));
    }
    //Прочтение всех покупок, в независимости от пользователя
    @GetMapping()
    public String readAllPurchases() {
        return gson.toJson(purchasesDAO.getAllPurchases());
    }
    //Прочтение покупок пользователя
    @GetMapping("/{idPerson}")
    public String readPurchasesFromId(@PathVariable int idPerson) {
        return gson.toJson(purchasesDAO.getPurchasesFromId(idPerson));
    }
    //Обновление данных о покупке
    @PatchMapping()
    public String updatePurchase(@RequestBody String json) {
        return gson.toJson(purchasesDAO.updatePurchase(gson.fromJson(json, Purchase.class)));
    }
    //Удаление определенной покупки
    @DeleteMapping("/{idPerson}/{idPurchase}")
    public String deletePurchase(@PathVariable int idPerson,
                               @PathVariable int idPurchase) {
        return gson.toJson(purchasesDAO.deletePurchase(idPerson, idPurchase));
    }
    //Удаление всехь покупок пользователя
    @DeleteMapping("/{idPerson}")
    public String deletePerson(@PathVariable int idPerson) {
       return gson.toJson(purchasesDAO.deletePerson(idPerson));
    }
}