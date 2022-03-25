package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class DonateCreateDTO {
    @NotEmpty @NotNull
    private String donator_name;
    @Email
    private String donator_email;
    @NotNull @Min(1)
    private Double donate_value;
    @NotEmpty
    private String description;
    @NotNull
    private Integer idRequest;
}
