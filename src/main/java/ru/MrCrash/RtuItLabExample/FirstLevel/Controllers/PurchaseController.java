package ru.MrCrash.RtuItLabExample.FirstLevel.Controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.MrCrash.RtuItLabExample.FirstLevel.DAO.PurchaseDAO;
import ru.MrCrash.RtuItLabExample.FirstLevel.Models.Purchase;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    private Gson gson = new Gson();
    private final PurchaseDAO purchaseDAO;

    @Autowired
    public PurchaseController(PurchaseDAO purchaseDAO) {
        this.purchaseDAO = purchaseDAO;
    }

    @PostMapping()
    public void createNewPurchase(@ModelAttribute("purchase") Purchase purchase) {
        purchaseDAO.createNewPurchase(purchase);
    }

    @GetMapping()
    public String readAllPurchases() {
        return gson.toJson(purchaseDAO.getAllPurchases());
    }

    @GetMapping("/{idPerson}")
    public String readPurchasesFromId(@PathVariable int idPerson) {
        return gson.toJson(purchaseDAO.getPurchasesFromId(idPerson));
    }

    @PatchMapping("/{id}")
    public void updatePurchase(@PathVariable("id") int idPerson,
                               @RequestParam("idPurchaseToUpdate") int idPurchaseToUpdate,
                               @ModelAttribute("purchase") Purchase purchase) {
        purchaseDAO.updatePurchase(idPerson, idPurchaseToUpdate, purchase);
    }

    @DeleteMapping("/{idPerson}")
    public void deletePerson(@PathVariable int idPerson) {
        purchaseDAO.deletePerson(idPerson);
    }

    @DeleteMapping("/{idPerson}/{idPurchase}")
    public void deletePurchase(@PathVariable int idPerson,
                               @PathVariable int idPurchase) {
        purchaseDAO.deletePurchase(idPerson, idPurchase);
    }
}