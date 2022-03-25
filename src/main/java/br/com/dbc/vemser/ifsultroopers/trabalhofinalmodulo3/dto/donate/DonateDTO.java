package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.donate;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.donate.DonateCreateDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DonateDTO extends DonateCreateDTO {
    @NotNull
    private Integer id_donate;
}
