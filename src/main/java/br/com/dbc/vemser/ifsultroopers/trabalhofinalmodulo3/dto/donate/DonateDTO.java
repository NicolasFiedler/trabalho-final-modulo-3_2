package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.donate;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonateDTO extends DonateCreateDTO {

    private Integer idDonate;
    private Integer idRequest;
}
