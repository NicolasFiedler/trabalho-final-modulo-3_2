package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Request {

    private Integer idRequest;
    private String title;
    private String description;
    private Double goal;
    private Double reachedValue;
    private Integer idCategory;
    private Integer idUser;
}
