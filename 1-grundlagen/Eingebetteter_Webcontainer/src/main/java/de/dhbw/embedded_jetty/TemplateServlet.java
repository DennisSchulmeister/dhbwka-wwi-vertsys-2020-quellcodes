package de.dhbw.embedded_jetty;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Ein Servlet, das zeigt, wie man ohne JSP serversetigen HTML-Code generieren
 * kann. Dies ist eine typische Alternative zu AJAX, wenn man sich den Stress
 * nicht geben will, mit JavaScript die HTML-Anzeige zu modifizieren.<br><br>
 *
 * Gegenüber JSP hat die Template-Lösung einige Vorteile:<br><br>
 *
 * <ul>
 *   <li>JSP-Dateien müssen <b>zur Laufzeit</b> zu Servlets kompiliert werden</li>
 *   <li>Die Konfiguration von Embedded Jetty für Java Server Pages ist recht komplex, <br>
 *       Vgl. https://github.com/jetty-project/embedded-jetty-jsp
 *   </li>
 *   <li>Viele Frameworks außerhalb von Java arbeiten ebenfalls mit Templates</li>
 * </ul>
 *
 * Templates sind also für eine eingebettete Anwendung viel besser geeignet als
 * Java Server Pages, da sie viel weniger Ressourcen verbrauchen.<br><br>
 *
 * Und schaut man sich andere Frameworks außerhalb von Java an (z.B. Ruby On
 * Rails oder Python Django) wird man feststellen, dass diese genauso arbeiten,
 * wie wir es hier tun: Es gibt ein zentrales URL-Mapping, das je URL auf eine
 * ausführbare Funktion oder Klasse zeigt. In der Klasse oder Funktion kann man
 * dann auf die HTTP-Anfrage und Antwort zugreifen und ein Template aufrufen,
 * wenn man HTML-Code generieren will.<br><br>
 */
public class TemplateServlet extends HttpServlet {

    protected static final PebbleEngine TEMPLATE_ENGINE = new PebbleEngine.Builder().build();
    private static PebbleTemplate template;
    
    private static final Logger LOGGER = Logger.getLogger(TemplateServlet.class.getName());

    static {        
        try {
            // URL der Template-Datei
            // Für eine Dokumenation der Syntax, siehe:
            // http://www.mitchellbosecke.com/pebble/documentation
            template = TEMPLATE_ENGINE.getTemplate("templates/template.html");
        } catch (PebbleException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LOGGER.info("TemplateServlet wurde aufgerufen ...");
        
        request.setCharacterEncoding("utf-8");
        
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        
        try {
            // In dieser Map werden die Variablen gespeichert, die innerhalb
            // des Templates zur Verfügung stehen
            Map<String, Object> context = new HashMap<>();
            context.put("variable1", "Hallo");
            context.put("variable2", "Template");

            // Template ausführen und Ergebnis an den Browser schicken
            template.evaluate(response.getWriter(), context);
        } catch (PebbleException ex) {
            throw new ServletException(ex);
        }
    }

}
