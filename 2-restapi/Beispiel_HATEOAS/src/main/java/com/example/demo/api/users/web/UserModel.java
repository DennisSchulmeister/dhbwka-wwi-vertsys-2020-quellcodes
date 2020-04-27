package com.example.demo.api.users.web;

import java.util.List;

import com.example.demo.api.groups.web.GroupModel;
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
 * Data Transfer Object fuer Benutzer, welches die HAL-Relationen definiert.
 * 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonRootName(value = "user")
@Relation(collectionRelation = "users")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserModel extends RepresentationModel<UserModel> {
    private Long id;
    private String firstName;
    private String lastName;

    private List<GroupModel> groups;
}