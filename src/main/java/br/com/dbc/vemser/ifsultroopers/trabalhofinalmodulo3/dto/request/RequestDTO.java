package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.request;

import lombok.Data;

@Data
public class RequestDTO extends RequestCreateDTO {

    private Integer idRequest;
    private Integer idUser;
    private Double reachedValue;
}
