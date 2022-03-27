package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.request;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.Category;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO extends RequestCreateDTO {

    private Integer idRequest;
    private Integer idUser;
    private Double reachedValue;
    private Category category;
}
