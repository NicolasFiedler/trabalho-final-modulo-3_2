package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.userdto;

import jdk.jfr.BooleanFlag;
import lombok.*;

import javax.validation.constraints.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersCreateDTO {
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 250)
    private String name;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @NotEmpty
    @Size(min = 8)
    private String password;

    @NotNull
    @NotEmpty
    private String document;
}
