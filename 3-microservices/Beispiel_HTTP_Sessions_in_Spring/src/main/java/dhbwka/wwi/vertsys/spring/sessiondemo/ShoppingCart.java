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
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

/**
 * Ein einfacher, in der aktuellen HTTP-Session abgelegter Warenkorb.
 */
@Component
@SessionScope
public class ShoppingCart {
    
    private HashMap<Long, Integer> articles = new HashMap<>();
    
    public void addArticle(long id, int amount) {
        if (amount == 0) {
            this.articles.remove(id);
        } else {
            this.articles.put(id, amount);
        }
    }
    
    public HashMap<Long, Integer> getArticles() {
        return (HashMap<Long, Integer>) this.articles.clone();
    }
        
}
