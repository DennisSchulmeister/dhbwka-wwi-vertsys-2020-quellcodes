/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.spring.sessiondemo;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Einfacher REST-Webservice zum Zugriff auf den Warenkorb.
 */
@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
    
    @Autowired
    private ShoppingCart shoppingCart;
    
    /**
     * GET-Anfrage. Liefert den Inhalt des Warenkorbs
     * @return Alle Artikel des Warenkorbs.
     */
    @GetMapping
    public HashMap<Long, Integer> getCartContent() {
        return this.shoppingCart.getArticles();
    }
    
    /**
     * POST-Anfrage. Ändert die Anzahl eines Artikels im Warenkorb.
     * @param id Artikel ID
     * @param amount Anzahl
     */
    @PostMapping
    public void addArticle(@RequestParam long id, @RequestParam int amount) {
        this.shoppingCart.addArticle(id, amount);
    }
    
}
