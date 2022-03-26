package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.request;

import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestUpdateDTO {

    @Size(max = 250)
    private String title;
    private String description;
    private Double goal;
}
