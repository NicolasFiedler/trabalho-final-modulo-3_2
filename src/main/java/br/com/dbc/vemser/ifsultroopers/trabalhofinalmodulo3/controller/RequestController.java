package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.controller;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.request.RequestCreateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.request.RequestDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.request.RequestUpdateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.BankAccountEntity;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.Category;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.service.RequestService;
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
@RequestMapping("/request")
@Validated
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @ApiOperation(value = "Retorna a lista de vakinhas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna todas as vakinhas"),
    })
    @GetMapping
    public List<RequestDTO> list() {
        return requestService.list();
    }

    @ApiOperation(value = "Retorna uma vakinha pelo id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma vakinha"),
            @ApiResponse(code = 400, message = "Vakinha não encontrada")
    })
    @GetMapping("/{idRequest}")
    public ResponseEntity<RequestDTO> get(@PathVariable("idRequest") Integer id) throws Exception {
        RequestDTO request = requestService.getById(id);
        return ResponseEntity.ok(request);
    }

//    @ApiOperation(value = "Retorna a lista das vakinhas que já atingiram sua meta")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Retorna uma lista"),
//    })
//    @GetMapping("/closed")
//    public List<RequestDTO> getClosedList() {
//        return requestService.getClosedList();
//    }
//
    @ApiOperation(value = "Cria uma vakinha pelo id de um usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a vakinha criada."),
            @ApiResponse(code = 400, message = "Usuário inválido.")
    })
    @PostMapping("/{idUser}")
    @Validated
    public ResponseEntity<RequestDTO> create(@PathVariable("idUser") Integer id,
                                             @RequestBody @Valid RequestCreateDTO request, @RequestParam Category category) throws Exception {
        RequestDTO created = requestService.create(id, request, category);

        return ResponseEntity.ok(created);
    }

    @ApiOperation(value = "Atualiza a vakinha pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a vakinha atualizada."),
            @ApiResponse(code = 400, message = "Vakinha não encontrada.")
    })
    @PutMapping("/{idRequest}")
    @Validated
    public ResponseEntity<RequestDTO> update(@PathVariable("idRequest") Integer id,
                                          @RequestBody @Valid RequestUpdateDTO data, @RequestParam Category category) throws Exception {
        RequestDTO updated = requestService.update(id, data, category);
        return ResponseEntity.ok(updated);
    }

    @ApiOperation(value = "Deleta a vakinha pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a vakinha deletada"),
            @ApiResponse(code = 400, message = "Vakinha não encontrada")
    })
    @DeleteMapping("/{idRequest}")
    public ResponseEntity<RequestDTO> delete(@PathVariable("idRequest") Integer id) throws Exception {
        RequestDTO deleted = requestService.delete(id);
        return ResponseEntity.ok(deleted);
    }
}