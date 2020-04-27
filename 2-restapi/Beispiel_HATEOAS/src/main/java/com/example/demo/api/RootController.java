package com.example.demo.api;

import com.example.demo.api.groups.web.GroupController;
import com.example.demo.api.users.web.UserController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * Einstiegspunkt für die HAL-Navigation. Hier werden die Entities, die
 * "discovered" werden können gelistet.
 * 
 */
@RestController
@RequestMapping("/api")
public class RootController {

    @GetMapping
    public RepresentationModel root() {
        RepresentationModel rootResource = new RepresentationModel();

        rootResource.add(linkTo(methodOn(UserController.class).getUsers()).withRel("users"),
                linkTo(methodOn(GroupController.class).getGroups()).withRel("groups"),
                linkTo(methodOn(RootController.class).root()).withSelfRel(),
                linkTo(methodOn(UserController.class).getUsers()).withSelfRel(),
                linkTo(methodOn(GroupController.class).getGroups()).withSelfRel());
        return rootResource;
    }
}