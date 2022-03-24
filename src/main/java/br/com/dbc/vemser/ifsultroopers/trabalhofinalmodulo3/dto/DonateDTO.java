package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class DonateDTO extends DonateCreateDTO{
    @NotNull
    private Integer id_donate;

}
