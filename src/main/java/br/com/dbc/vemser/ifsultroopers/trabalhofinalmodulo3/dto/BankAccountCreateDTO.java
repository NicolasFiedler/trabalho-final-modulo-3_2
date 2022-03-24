package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class BankAccountCreateDTO {

    @NotNull
    @NotEmpty
    private String account_number, agency;
}
