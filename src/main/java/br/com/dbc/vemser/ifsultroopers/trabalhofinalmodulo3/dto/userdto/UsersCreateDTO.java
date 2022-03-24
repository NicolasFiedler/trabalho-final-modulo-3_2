package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.userdto;

import jdk.jfr.BooleanFlag;
import lombok.Data;

import javax.validation.constraints.*;

@Data
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

    @BooleanFlag
    private Boolean type;

    @NotNull
    @NotEmpty
    private String document;
}
