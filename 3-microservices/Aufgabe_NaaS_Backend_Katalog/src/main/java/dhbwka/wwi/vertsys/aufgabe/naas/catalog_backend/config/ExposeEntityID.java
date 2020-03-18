/*
 * Copyright © 2020 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.aufgabe.naas.catalog_backend.config;

import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

/**
 * ID-Felder der Entities im REST-Webservice verfügbar machen. Die Autoren von
 * Spring Data REST sind der Meinung, dass man die ID einer Entity nicht zu
 * wissen braucht, wenn HATEOAS aktiv ist. Manchmal braucht man sie aber eben
 * doch und da ist es ganz schön mühselig, sich die ID aus der _self-URL eines
 * Eintrags herauszuschneiden.
 * 
 * Vgl. https://stackoverflow.com/a/47765828
 */
@Configuration
public class ExposeEntityID extends RepositoryRestConfigurerAdapter {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(entityManager.getMetamodel().getEntities().stream().map(e -> e.getJavaType()).collect(Collectors.toList()).toArray(new Class[0]));
    }

}
