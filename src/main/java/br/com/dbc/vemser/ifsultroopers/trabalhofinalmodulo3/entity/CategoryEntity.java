package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryEntity {

    private Integer idCategory;
    private String name;
    private String description;
}
