package com.example.demo.api.users.service;

import com.example.demo.api.groups.web.GroupModel;
import com.example.demo.api.users.data.UserEntity;
import com.example.demo.api.users.web.UserController;
import com.example.demo.api.users.web.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.api.groups.web.GroupController;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.stereotype.Service;

/**
 * 
 * Copyright © 2020 Martin Kutscher
 * 
 * E-Mail: martin.kutscher@exxeta.com
 * 
 * Dieser Quellcode ist lizenziert unter einer Creative Commons Namensnennung
 * 4.0 International Lizenz.
 * 
 * Transformiert einen (toModel) oder mehrere Benutzer-Datenbankeinträge
 * (toCollectionModel) ins Austauschformat (Model, das Data Transfer Object).
 * Hier werden auch die HAL-Verknuepfungen (Relations mit Links) gesetzt.
 * Beachte: statischer Import: import static
 * org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*; liefert die
 * wichtigen Methoden.
 */
@Service
public class UserMapper extends RepresentationModelAssemblerSupport<UserEntity, UserModel> {

    public UserMapper() {
        super(UserController.class, UserModel.class);
    }

    @Override
    public UserModel toModel(UserEntity entity) {
        UserModel userModel = instantiateModel(entity);
        userModel.add(linkTo(methodOn(UserController.class).getUserById(entity.getId())).withSelfRel());
        userModel.setId(entity.getId());
        userModel.setFirstName(entity.getFirstName());
        userModel.setLastName(entity.getLastName());

        if (entity.getGroups().isEmpty()) {
            userModel.setGroups(new ArrayList<>());
        } else {
            List<GroupModel> groups = entity.getGroups().stream()
                    .map(group -> GroupModel.builder().id(group.getId()).groupName(group.getGroupName()).build()
                            .add(linkTo(methodOn(GroupController.class).getGroupById(group.getId())).withSelfRel()))
                    .collect(Collectors.toList());
            userModel.setGroups(groups);
        }

        return userModel;
    }

    @Override
    public CollectionModel<UserModel> toCollectionModel(Iterable<? extends UserEntity> entities) {
        CollectionModel<UserModel> userModels = super.toCollectionModel(entities);
        userModels.add(linkTo(methodOn(UserController.class).getUsers()).withSelfRel());
        return userModels;
    }
}