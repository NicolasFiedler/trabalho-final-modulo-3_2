package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.category;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CategoryCreateDTO {

    @NotEmpty
    @Size(max = 50)
    private String name;

    @NotEmpty
    @Size(max = 250)
    private String description;
}
