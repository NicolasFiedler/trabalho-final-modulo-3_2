package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.controller;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.userdto.UsersCreateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.userdto.UsersDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.exception.BusinessRuleException;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.service.UsersService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RestController
@RequestMapping("/users")
@Validated
public class UsersController {

    @Autowired
    private UsersService usersService;

    @ApiOperation(value = "Retorna a lista de todos os usuarios")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna todos os usuarios")
    })
    @GetMapping
    public List<UsersDTO> list () {
        return usersService.list();
    }

    @ApiOperation(value = "Retorna um usuario pelo ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um usuario"),
            @ApiResponse(code = 400, message = "Usuario nao encontrado")
    })
    @GetMapping("/{idUser}")
    public UsersDTO getById (@PathVariable("idUser") Integer id) throws BusinessRuleException {
        return usersService.getById(id);
    }

    @ApiOperation(value = "Insere e Retorna o Usuario inserido")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna Usuario inserido"),
            @ApiResponse(code = 400, message = "CPF ou CNPJ Invalido")
    })
    @PostMapping
    public UsersDTO create (@Valid @RequestBody UsersCreateDTO usersCreateDTO) throws BusinessRuleException {
        return usersService.create(usersCreateDTO);
    }

    @ApiOperation(value = "Atualiza e Retorna o usuario atualizado pelo ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna Usuario atualizado"),
            @ApiResponse(code = 400, message = "Usuario nao encontrado"),
            @ApiResponse(code = 400, message = "CPF ou CNPJ Invalido")
    })
    @PutMapping("/{idUser}")
    public UsersDTO update (@PathVariable("idUser") Integer id, @Valid UsersCreateDTO usersCreateDTO) throws BusinessRuleException {
        return usersService.update(id, usersCreateDTO);
    }

    @ApiOperation(value = "Remove e Retorna o usuario removido pelo ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma vakinha"),
            @ApiResponse(code = 400, message = "Usuario nao encontrado")
    })
    @DeleteMapping("/{idUser}")
    public UsersDTO delete (@PathVariable("idUser") Integer id) throws BusinessRuleException {
        return usersService.delete(id);
    }
}
