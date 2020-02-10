package de.dhbw.embedded_jetty;

import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Minimalbeispiel für einen eingebetteten Jetty-Webcontainer in einer Java SE-
 * Anwendung. Dieses Beispiel nutzt Maven, um eine JAR-Datei der Anwendung zu
 * bauen und dabei alle Abhängigkeiten aufzulösen.<br><br>
 * 
 * Dieses Beispiel zeigt, wie mit Java ein Webserver programmiert werden kann,
 * der auf einem eingebetteten Gerät mit begrenzten Ressourcen laufen kann.
 * Es kommen <b>keine</b> Java Enterprise Edition und <b>kein</b> Applikationsserver
 * zum Einsatz, da diese für eingebettete Geräte viel zu verschwenderisch sind.
 * <br><br>
 * 
 * Ungefähr in der Mitte der main()-Methode müssen die Servlets eingebunden und
 * einer URL zugeordnet werden. Da wir kein Java EE nutzen, gibt es keinen
 * Deployment Descriptor (web.xml) und auch die aus der Vorlesung bekannten
 * Annotationen (@Servlet usw.) funktionieren nicht.<br><br>
 */
public class Main {
    
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    
    public static void main(String[] args) throws Exception {
        // Server soll auf Port 8080 lauschen und Servlets ausführen
        Server server = new Server();
        int port = 8080;
        
        ServerConnector httpConnector = new ServerConnector(server);
        httpConnector.setPort(port);
        server.addConnector(httpConnector);

        // Servlet-Handling aktivieren. Alternativ bietet Jetty auch eine eigene
        // API, um HTTP-Handler zu schreiben. Servlets kennen wir aber schon. :-)
        // Vgl. http://stackoverflow.com/questions/20207477/serving-static-files-from-alternate-path-in-embedded-jetty
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setResourceBase(".");
        server.setHandler(context);
        
        // URL-Mappings für Servlets
        context.addServlet(HelloServlet.class, "/hello");
        context.addServlet(TemplateServlet.class, "/template");
        
        // Default-Servlet zur Bereitstellung statischer Dateien
        // Siehe "Other Sources --> src/main/resources --> static" in Netbeans!
        ServletHolder staticFileServletHolder = new ServletHolder("default", DefaultServlet.class);
        
        String staticPath = Main.class.getResource("/static").getPath();
        staticPath = URLDecoder.decode(staticPath, "UTF-8");
        LOGGER.log(Level.INFO, "Pfad für statische Dateien: {0}", staticPath);
        
        staticFileServletHolder.setInitParameter("resourceBase", staticPath);
        staticFileServletHolder.setInitParameter("dirAllowed", "true");
        context.addServlet(staticFileServletHolder, "/");
                
        // HTTP-Server nun starten
        LOGGER.log(Level.INFO, "Starte Webserver auf Port {0}", Integer.toString(port));
        LOGGER.log(Level.INFO, "Rufen Sie http://localhost:{0}/ im Browser auf", Integer.toString(port));
        
        server.start();
        server.join();
    }
}
