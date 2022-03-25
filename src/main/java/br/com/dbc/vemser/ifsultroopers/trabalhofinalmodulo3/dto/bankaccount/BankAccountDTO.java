package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.bankaccount;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.bankaccount.BankAccountCreateDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BankAccountDTO extends BankAccountCreateDTO {
    @NotNull
    private Integer idBankAccount;
}
