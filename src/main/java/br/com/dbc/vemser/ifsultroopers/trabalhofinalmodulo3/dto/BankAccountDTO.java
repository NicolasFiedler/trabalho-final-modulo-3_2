package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BankAccountDTO extends BankAccountCreateDTO{
    @NotNull
    private Integer id_bank_account;
}
