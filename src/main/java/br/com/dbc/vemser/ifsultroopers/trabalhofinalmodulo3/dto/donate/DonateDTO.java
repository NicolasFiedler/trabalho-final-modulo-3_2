package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.donate;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.donate.DonateCreateDTO;
import lombok.*;

import javax.validation.constraints.NotNull;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonateDTO extends DonateCreateDTO {
    @NotNull
    private Integer id_donate;
}
