package ru.MrCrash.RtuItLabExample.Controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.MrCrash.RtuItLabExample.DAO.ShopsDAO;

@RestController
@RequestMapping("/shops")
public class ShopsController {
    private Gson gson = new Gson();
    private final ShopsDAO shopsDAO;

    public ShopsController(ShopsDAO shopsDAO) {
        this.shopsDAO = shopsDAO;
    }

    @PostMapping()
    public void createNewShop(){

    }
    @PostMapping
    public void addNewPurchase(){

    }
    @PostMapping
    public void buyPurchaseByPerson(){

    }
    @GetMapping
    public String readAllShops(){
        return null;
    }
    @GetMapping
    public String readShopsFromId(){
        return null;
    }
    @GetMapping
    public String readShopFromPurchaseCategory(){
        return null;
    }
    @PatchMapping
    public void updateShopInfo(){

    }
    @PatchMapping
    public void updatePurchaseInfo(){

    }
    @DeleteMapping
    public void deleteShop(){

    }
    @DeleteMapping
    public void deletePurchase(){

    }


}
