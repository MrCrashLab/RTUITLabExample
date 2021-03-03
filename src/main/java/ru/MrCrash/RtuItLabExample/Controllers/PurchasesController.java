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

    @PostMapping()
    public void createNewPurchase(@ModelAttribute("purchase") Purchase purchase) {
        purchasesDAO.createNewPurchase(purchase);
    }

    @GetMapping()
    public String readAllPurchases() {
        return gson.toJson(purchasesDAO.getAllPurchases());
    }

    @GetMapping("/{idPerson}")
    public String readPurchasesFromId(@PathVariable int idPerson) {
        return gson.toJson(purchasesDAO.getPurchasesFromId(idPerson));
    }

    @PatchMapping()
    public void updatePurchase(@ModelAttribute("purchase") Purchase purchase) {
        purchasesDAO.updatePurchase(purchase);
    }

    @DeleteMapping("/{idPerson}")
    public void deletePerson(@PathVariable int idPerson) {
        purchasesDAO.deletePerson(idPerson);
    }

    @DeleteMapping("/{idPerson}/{idPurchase}")
    public void deletePurchase(@PathVariable int idPerson,
                               @PathVariable int idPurchase) {
        purchasesDAO.deletePurchase(idPerson, idPurchase);
    }
}