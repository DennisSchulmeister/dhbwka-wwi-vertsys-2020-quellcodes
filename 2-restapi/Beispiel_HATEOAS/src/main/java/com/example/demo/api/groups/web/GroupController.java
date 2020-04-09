package com.example.demo.api.groups.web;

import com.example.demo.api.groups.service.GroupFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Ermöglicht Lesen einer Gruppe über die Id oder alle vorhandenen Gruppen.
 * 
*/
@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupFacade groupFacade;

    @GetMapping("/{id}")
    public ResponseEntity<GroupModel> getGroupById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(groupFacade.getGroupById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<GroupModel>> getGroups() {
        return new ResponseEntity<>(groupFacade.getGroups(), HttpStatus.OK);
    }
}