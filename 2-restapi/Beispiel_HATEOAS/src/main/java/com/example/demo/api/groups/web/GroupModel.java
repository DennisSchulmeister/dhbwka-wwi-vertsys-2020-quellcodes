package com.example.demo.api.groups.web;

import java.util.List;

import com.example.demo.api.users.web.UserModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * Copyright © 2020 Martin Kutscher
 * 
 * E-Mail: martin.kutscher@exxeta.com
 * 
 * Dieser Quellcode ist lizenziert unter einer Creative Commons Namensnennung
 * 4.0 International Lizenz.
 * 
 * Data Transfer Object fuer Gruppen, welches die HAL-Relationen definiert.
 * 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonRootName(value = "group")
@Relation(collectionRelation = "groups")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupModel extends RepresentationModel<GroupModel> {

        private Long id;
        private String groupName;
        private String description;
        private List<UserModel> users;

}