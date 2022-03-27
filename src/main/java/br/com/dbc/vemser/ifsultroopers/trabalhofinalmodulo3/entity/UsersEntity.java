package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USERS")
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ")
    @SequenceGenerator(name = "USERS_SEQ", sequenceName = "USERS_SEQ", allocationSize = 1)
    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "type")
    private Boolean type;

    @Column(name = "document")
    private String document;

    @JsonIgnore
    @OneToMany(mappedBy = "usersEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<RequestEntity> requests;
}
