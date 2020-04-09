package com.example.demo.api.groups.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.example.demo.api.users.data.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelliert eine Datenbankzeile fuer einen Grupppeneintrag. Die Lombok-Annotationen halten den Code kurz.
 * 
*/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="demo_group")
public class GroupEntity implements Serializable{

    private static final long serialVersionUID = 3628088657445508772L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String groupName;
    private String description;
     
    @ManyToMany(mappedBy = "groups",fetch = FetchType.EAGER)
    private List<UserEntity> users;

}