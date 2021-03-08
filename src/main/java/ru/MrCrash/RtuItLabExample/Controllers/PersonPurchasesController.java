package ru.MrCrash.RtuItLabExample.Controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.MrCrash.RtuItLabExample.DAO.PersonPurchases.PersonPurchasesDAO;
import ru.MrCrash.RtuItLabExample.Models.Purchase.PersonPurchase;


@RestController
@RequestMapping("/purchases")
public class PersonPurchasesController {
    private final Gson gson = new Gson();
    private final PersonPurchasesDAO personPurchasesDAO;
    @Autowired
    public PersonPurchasesController(PersonPurchasesDAO personPurchasesDAO) {
        this.personPurchasesDAO = personPurchasesDAO;
    }


    //Создание новой покупки
    @PostMapping()
    public String createNewPurchase(@RequestBody String json) {
        return gson.toJson(personPurchasesDAO.createNewPurchase(gson.fromJson(json, PersonPurchase.class)));
    }
    //Прочтение всех покупок, в независимости от пользователя
    @GetMapping()
    public String readAllPurchases() {
        return gson.toJson(personPurchasesDAO.getAllPurchases());
    }
    //Прочтение покупок пользователя
    @GetMapping("/{idPerson}")
    public String readPurchasesFromId(@PathVariable int idPerson) {
        return gson.toJson(personPurchasesDAO.getPurchasesFromId(idPerson));
    }
    //Обновление данных о покупке
    @PatchMapping()
    public String updatePurchase(@RequestBody String json) {
        return gson.toJson(personPurchasesDAO.updatePurchase(gson.fromJson(json, PersonPurchase.class)));
    }
    //Удаление определенной покупки
    @DeleteMapping("/{idPerson}/{idPurchase}")
    public String deletePurchase(@PathVariable int idPerson,
                               @PathVariable int idPurchase) {
        return gson.toJson(personPurchasesDAO.deletePurchase(idPerson, idPurchase));
    }
    //Удаление всехь покупок пользователя
    @DeleteMapping("/{idPerson}")
    public String deletePerson(@PathVariable int idPerson) {
       return gson.toJson(personPurchasesDAO.deletePerson(idPerson));
    }
}