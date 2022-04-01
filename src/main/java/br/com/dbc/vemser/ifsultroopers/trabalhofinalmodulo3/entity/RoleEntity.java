package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity(name = "ROLE")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_seq")
    @SequenceGenerator(name = "roles_seq", sequenceName = "roles_seq", allocationSize = 1)
    @Column(name = "id_role")
    private Integer idRole;

    @Column(name = "NAME")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<UsersEntity> users;
}
