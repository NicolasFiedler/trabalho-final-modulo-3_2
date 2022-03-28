package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.controller;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.donate.DonateCreateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.donate.DonateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.service.DonateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RestController
@RequestMapping("/donate")
@Validated
@RequiredArgsConstructor
public class DonateController {

    private final DonateService donateService;

    @ApiOperation(value = "Cria e retorna a Donate criada")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a Donate criada"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping("/{idRequest}")
    public ResponseEntity<DonateDTO> create(@Valid @RequestBody DonateCreateDTO donate, @PathVariable("idRequest") Integer idRequest) throws Exception {
        DonateDTO donateDTO = donateService.create(donate,idRequest);
       return ResponseEntity.ok(donateDTO);
    }

    @ApiOperation(value = "Retorna a lista de donates")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de donates"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping
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
    @PutMapping("/{idDonate}")
    public ResponseEntity<DonateDTO> update(@PathVariable("idDonate") Integer id,
                                            @Valid @RequestBody DonateCreateDTO donateCreateDTO) throws Exception {
//        emailService.pessoaSendEmail(pessoaAtualizar, "Seus dados foram atualizados no nosso sistema.", "Atualização de dados");
        return  ResponseEntity.ok(donateService.update(id, donateCreateDTO));
    }

    @ApiOperation(value = "Retorna a donate Deletada pelo Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a donate Deletada pelo Id"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @DeleteMapping("/{idDonate}")
    public ResponseEntity<DonateDTO> delete(@PathVariable("idDonate") Integer id) throws Exception {
        DonateDTO donateDTO = donateService.delete(id);
//        emailService.pessoaSendEmail(pessoaDTO, "Você perdeu o acesso ao nosso sistema.", " Delet de conta");
        return ResponseEntity.ok(donateDTO);
    }

    @ApiOperation(value = "Retorna a lista de Donates por Id da Request")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de Donates por id da Request"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/findByIdRequest/{idRequest}")
    public ResponseEntity<List<DonateDTO>> getDonateByIdRequest(@PathVariable("idRequest") Integer id){
        return ResponseEntity.ok(donateService.findByIdRequest(id));
    }

    @ApiOperation(value = "Retorna a lista de Donates por nome do doador")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de Donates  por nome do doador"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/findByDonatorName/{donatorName}")
    public ResponseEntity<List<DonateDTO>> getDonateByIdRequest(@PathVariable("donatorName") String name){
        return ResponseEntity.ok(donateService.findByDonatorName(name));
    }
}
