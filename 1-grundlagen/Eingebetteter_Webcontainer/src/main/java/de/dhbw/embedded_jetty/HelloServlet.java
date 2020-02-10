package de.dhbw.embedded_jetty;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Ein kleines Test-Servlet.
 */
public class HelloServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(HelloServlet.class.getName());
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LOGGER.info("HelloServlet wurde aufgerufen ...");
        
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("   <head>");
        out.println("       <title>Hallo, Servlet</title>");
        out.println("   </head>");
        out.println("   <html>");
        out.println("       <h1>Hallo, Servlet!</h1>");
        out.println("       session=" + request.getSession(true).getId());
        out.println("       <br/>");
        out.println("       <a href=\"/\">Zurück zur Startseite</a>");
        out.println("   </html>");
        out.println("</html>");
    }

}
