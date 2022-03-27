package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.donate;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonateCreateDTO {
    @NotEmpty @NotNull
    private String donatorName;
    @Email
    private String donatorEmail;
    @NotNull @Min(1)
    private Double donateValue;
    @NotEmpty
    private String description;
}
