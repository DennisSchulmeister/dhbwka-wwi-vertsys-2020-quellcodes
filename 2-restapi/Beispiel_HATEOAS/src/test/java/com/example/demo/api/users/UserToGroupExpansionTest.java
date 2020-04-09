package com.example.demo.api.users;

import java.net.URI;
import java.net.URISyntaxException;

import com.example.demo.api.groups.web.GroupModel;
import com.example.demo.api.users.web.UserModel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;

/**
 * 
 * Copyright © 2020 Martin Kutscher
 * 
 * E-Mail: martin.kutscher@exxeta.com
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 * 
 * Test mit dem HATEOAS-CLient Traverson, der das Spring-Resttemplate kapselt.
 * Die Navigation erfolgt über die Relationen und auch ueber Ausdruecke im JSPN Path format.
 * In diesem Test wird zunaechst dem Link zu Benutzern gefolgt und dann die 1. Gruppe des 1. Benutzers abgefragt 
 * und deren Feld fuer Beschreibung validiert. Zusaetzlich werden Vor- und Nachname des Benutzers validiert.
 * Die Daten kommen aus der data.sql Datei im /resources Ordner.
 * 
*/import org.springframework.hateoas.client.Traverson;

public class UserToGroupExpansionTest {

    @Test
    public void testExpantGroupOfUser() throws URISyntaxException {

        Traverson client = new Traverson(new URI("http://localhost:8080/api/"), MediaTypes.HAL_JSON);

        // Testseite fuer JSON Path http://www.jsonquerytool.com/
        String pathToUser = "$._embedded.users[0].groups[0]._links.self.href";

        GroupModel expandedGroup = client.follow("users", pathToUser)
                .toObject(new ParameterizedTypeReference<EntityModel<GroupModel>>() {
                }).getContent();

        Assertions.assertEquals("Hauptanwender", expandedGroup.getDescription());
        Assertions.assertEquals(1, expandedGroup.getUsers().size());
        UserModel firstUser = expandedGroup.getUsers().iterator().next();
        Assertions.assertEquals("Nudel", firstUser.getFirstName());
        Assertions.assertEquals("Suppe", firstUser.getLastName());

    }

}
