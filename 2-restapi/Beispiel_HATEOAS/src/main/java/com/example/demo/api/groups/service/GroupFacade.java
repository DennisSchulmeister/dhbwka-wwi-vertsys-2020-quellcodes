package com.example.demo.api.groups.service;

import com.example.demo.api.groups.data.GroupRepository;
import com.example.demo.api.groups.web.GroupModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
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
 * Kapselt die Datentransformation der Gruppen und bietet auch Raum für
 * notwendige Business Logik, wie z.B. Autorisierungspruefungen.
 * 
 */
@Service
public class GroupFacade {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupMapper groupMapper;

    public GroupModel getGroupById(Long id) {
        return groupRepository.findById(id).map(groupMapper::toModel)
                .orElseThrow(() -> new RuntimeException("No group found with id " + id));
    }

    public CollectionModel<GroupModel> getGroups() {
        return groupMapper.toCollectionModel(groupRepository.findAll());
    }

}