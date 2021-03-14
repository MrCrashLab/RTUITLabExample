package ru.MrCrash.RtuItLabExample.Interlayer;

import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/UI")
public class Interlayer {
    private final HttpClient httpClient = HttpClient.newHttpClient();
    String personPurchasePath = "http://localhost:8081/purchases";
    String shopsPath = "http://localhost:8080/shops";


    @PostMapping("/shops/{idPerson}/{idShop}")
    public String buyPurchase(@PathVariable String idPerson,
                              @PathVariable String idShop,
                              @RequestBody int[] purchases) {

    }

    @GetMapping("/shops/{idShop}")
    public String getAllShopPurchases(@PathVariable int idShop) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(shopsPath + "/" + idShop))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body().toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/person/{idPerson}")
    public String getAllPurchases(@PathVariable int idPerson) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(personPurchasePath + "/" + idPerson))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body().toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/person/{idPerson}/{idReceipt}")
    public String getReceipt(@PathVariable int idPerson,
                             @PathVariable String idReceipt) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(personPurchasePath + "/receipt/" + idPerson + "/" + idReceipt))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body().toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/shops")
    public String getAllShop() {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(shopsPath))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body().toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
