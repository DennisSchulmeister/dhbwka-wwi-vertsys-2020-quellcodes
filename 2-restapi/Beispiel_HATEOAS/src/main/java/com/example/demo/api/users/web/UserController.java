package com.example.demo.api.users.web;

import com.example.demo.api.users.service.UserFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Ermöglicht Lesen eines Benutzers über die Id oder alle vorhandenen Benutzer.
 * 
*/
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userFacade.getUserById(id), HttpStatus.OK);   
    }

    @GetMapping
    public ResponseEntity<CollectionModel<UserModel>> getUsers() {
        return new ResponseEntity<>(userFacade.getUsers(), HttpStatus.OK);   
    }
}
