package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.bankaccount;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.bankaccount.BankAccountCreateDTO;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDTO extends BankAccountCreateDTO {
    @NotNull
    private Integer idBankAccount;
}
