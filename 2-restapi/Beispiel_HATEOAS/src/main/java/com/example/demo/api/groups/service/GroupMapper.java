package com.example.demo.api.groups.service;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.example.demo.api.groups.data.GroupEntity;
import com.example.demo.api.groups.web.GroupController;
import com.example.demo.api.groups.web.GroupModel;
import com.example.demo.api.users.web.UserController;
import com.example.demo.api.users.web.UserModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
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
 * Transformiert einen (toModel) oder mehrere Gruppen-Datenbankeinträge
 * (toCollectionModel) ins Austauschformat (Model, das Data Transfer Object).
 * Hier werden auch die HAL-Verknuepfungen (Relations mit Links) gesetzt.
 * Beachte: statischer Import: import static
 * org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*; liefert die
 * wichtigen Methoden.
 * 
 */
@Service
public class GroupMapper extends RepresentationModelAssemblerSupport<GroupEntity, GroupModel> {

    public GroupMapper() {
        super(GroupController.class, GroupModel.class);
    }

    @Override
    public GroupModel toModel(GroupEntity entity) {
        GroupModel groupModel = instantiateModel(entity);

        groupModel.add(linkTo(methodOn(GroupController.class).getGroupById(entity.getId())).withSelfRel());

        groupModel.setId(entity.getId());
        groupModel.setGroupName(entity.getGroupName());
        groupModel.setDescription(entity.getDescription());

        if (entity.getUsers().isEmpty()) {
            groupModel.setUsers(new ArrayList<>());
        } else {
            groupModel.setUsers(entity.getUsers().stream()
                    .map(user -> UserModel.builder().id(user.getId()).firstName(user.getFirstName())
                            .lastName(user.getLastName()).build()
                            .add(linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel()))
                    .collect(Collectors.toList()));

        }

        return groupModel;
    }

    @Override
    public CollectionModel<GroupModel> toCollectionModel(Iterable<? extends GroupEntity> entities) {
        CollectionModel<GroupModel> groupModels = super.toCollectionModel(entities);

        groupModels.add(linkTo(methodOn(GroupController.class).getGroups()).withSelfRel());

        return groupModels;
    }
}
