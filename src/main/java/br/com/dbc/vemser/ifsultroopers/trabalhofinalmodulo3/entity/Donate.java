package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Donate {
    private Integer id_donate;
    private Integer idRequest;
    private String donator_name;
    private String donator_email;
    private Double donate_value;
    private String description;
}