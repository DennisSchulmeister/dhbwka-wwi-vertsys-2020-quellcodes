/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.guestbook;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet für die Gästebuchseite
 */
@WebServlet(urlPatterns = {"/index.html"})
public class GuestbookServlet extends HttpServlet {
    
    @EJB
    GuestbookBean guestbookBean;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // Alle vorhandenen Einträge aus der Datenbank lesen und im Request
        // Context ablegen, damit sie in der JSP zur Verfügung stehen
        List<GuestbookEntry> entries = this.guestbookBean.findAllEntries();
        request.setAttribute("entries", entries);
        
        // Anfrage an die JSP weiterleiten
        request.getRequestDispatcher("guestbook.jsp").forward(request, response);
        
        // In der Session liegende Fehlermeldung verwerfen, damit wir beim
        // nächsten Aufruf wieder mit einem leeren Eingabefeld anfangen
        HttpSession session = request.getSession();
        session.removeAttribute("fehler");
        session.removeAttribute("name");
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // Prüfen, ob der Anwender seinen Namen eingegeben hat
        HttpSession session = request.getSession();
        
        String fehler = "";
        String name = request.getParameter("name");
        
        if (name == null || name.trim().isEmpty()) {
            fehler = "Bitte gib erst deinen Namen ein.";
            session.setAttribute("fehler", fehler);
            session.setAttribute("name", name);
        }
        
        // Neuen Eintrag speichern
        if (fehler.isEmpty()) {
            this.guestbookBean.createNewEntry(name);
        }
        
        // Browser auffordern, die Seite neuzuladen
        response.sendRedirect(request.getContextPath());
    }
    
}
