package ru.MrCrash.RtuItLabExample.PersonPurchase.Controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.MrCrash.RtuItLabExample.PersonPurchase.DAO.PersonPurchasesDAO;
import ru.MrCrash.RtuItLabExample.PersonPurchase.Models.PersonPurchase;


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
    @PatchMapping("/{idPerson}/{idPurchase}")
    public String updatePurchase(@PathVariable int idPerson,
                                 @PathVariable int idPurchase,
                                 @RequestBody String json) {
        return gson.toJson(personPurchasesDAO.updatePurchase(idPerson, idPurchase,gson.fromJson(json, PersonPurchase.class)));
    }
    //Удаление определенной покупки
    @DeleteMapping("/{idPerson}/{idPurchase}")
    public String deletePurchase(@PathVariable int idPerson,
                               @PathVariable int idPurchase) {
        return gson.toJson(personPurchasesDAO.deletePurchase(idPerson, idPurchase));
    }
    //Удаление всех покупок пользователя
    @DeleteMapping("/{idPerson}")
    public String deletePerson(@PathVariable int idPerson) {
       return gson.toJson(personPurchasesDAO.deletePerson(idPerson));
    }
}