package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.request;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class RequestUpdateDTO {

    @Size(max = 250)
    private String title;
    private String description;
    private Double goal;
}
