package com.example.demo.api.users.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.example.demo.api.groups.data.GroupEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * Copyright © 2020 Martin Kutscher
 * 
 * E-Mail: martin.kutscher@exxeta.com
 * 
 * Dieser Quellcode ist lizenziert unter einer Creative Commons Namensnennung
 * 4.0 International Lizenz.
 * 
 * Modelliert eine Datenbankzeile fuer einen Benutzereintrag. Die
 * Lombok-Annotationen halten den Code kurz.
 * 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "groups")
@Entity
@Table(name = "demo_user")
public class UserEntity implements Serializable {

  private static final long serialVersionUID = -6871138331442058810L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String firstName;
  private String lastName;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "demo_user_group", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private List<GroupEntity> groups;

}