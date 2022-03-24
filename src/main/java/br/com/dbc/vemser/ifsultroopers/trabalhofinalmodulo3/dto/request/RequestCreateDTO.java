package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestCreateDTO {

    @NotEmpty
    private String title;
    private String description;

    @NotNull
    private Double goal;

    @NotNull
    private Integer idCategory;
}
