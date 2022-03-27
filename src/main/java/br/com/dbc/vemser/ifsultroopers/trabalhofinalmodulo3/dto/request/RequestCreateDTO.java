package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreateDTO {

    @NotEmpty
    private String title;
    private String description;

    @NotNull
    private Double goal;

    @NotNull
    private Integer idBankAccount;
}