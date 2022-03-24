package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.controller;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.DonateCreateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.DonateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.service.DonateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/donate") // localhost:8080/pessoa
@Validated
public class DonateController {

    @Autowired
    private DonateService donateService;
//    @Autowired
//    private EmailService emailService;

    @ApiOperation(value = "Cria e retorna a Donate criada")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a Donate criada"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping // localhost:8080/pessoa
    public ResponseEntity<DonateDTO> create(@Valid @RequestBody DonateCreateDTO donate) throws Exception {
        DonateDTO donateDTO = donateService.create(donate);
//        emailService.pessoaSendEmail(pessoaDTO, "Seu cadastro foi realizado com sucesso, seu identificador é "+pessoaDTO.getIdPessoa()+".", "Cadastro");
       return ResponseEntity.ok(donateDTO);
    }

    @ApiOperation(value = "Retorna a lista de donates")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de donates"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping // localhost:8080/pessoa
    public ResponseEntity<List<DonateDTO>> list() {
        return ResponseEntity.ok(donateService.list());
    }

    @ApiOperation(value = "Retorna a lista de Donates por Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de Donates por id"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/{idDonate}")
    public ResponseEntity<DonateDTO> getDonateById(@PathVariable("idDonate") Integer id) throws Exception {
        return ResponseEntity.ok(donateService.getDonateById(id));
    }


    @ApiOperation(value = "Retorna a donate Editada pelo Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a donate Editada pelo Id"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PutMapping("/{idDonate}") // localhost:8080/pessoa/1000
    public ResponseEntity<DonateDTO> update(@PathVariable("idDonate") Integer id,
                                            @Valid @RequestBody DonateDTO donateDTO) throws Exception {
//        emailService.pessoaSendEmail(pessoaAtualizar, "Seus dados foram atualizados no nosso sistema.", "Atualização de dados");
        return  ResponseEntity.ok(donateService.update(id, donateDTO));
    }

    @ApiOperation(value = "Retorna a donate Deletada pelo Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a donate Deletada pelo Id"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @DeleteMapping("/{idDonate}") // localhost:8080/pessoa/10
    public ResponseEntity<DonateDTO> delete(@PathVariable("idDonate") Integer id) throws Exception {
        DonateDTO donateDTO = donateService.delete(id);
//        emailService.pessoaSendEmail(pessoaDTO, "Você perdeu o acesso ao nosso sistema.", " Delet de conta");
        return ResponseEntity.ok(donateDTO);
    }
}
