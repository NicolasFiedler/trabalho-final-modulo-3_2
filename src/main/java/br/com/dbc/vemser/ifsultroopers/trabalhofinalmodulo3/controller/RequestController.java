package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.controller;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.request.RequestCreateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.request.RequestDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.request.RequestUpdateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.service.RequestService;
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
@RequestMapping("/request")
public class RequestController {

    @Autowired
    private RequestService service;

    @ApiOperation(value = "Retorna uma lista de vakinhas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista"),
    })
    @GetMapping
    public List<RequestDTO> get() {
        return service.get();
    }

    @ApiOperation(value = "Retorna uma vakinha pelo id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma vakinha"),
            @ApiResponse(code = 400, message = "Não encontra a vakinha.")
    })
    @GetMapping("/{idRequest}")
    public ResponseEntity<RequestDTO> get(@PathVariable("idRequest") Integer id) throws Exception {
        RequestDTO request = service.get(id);
        return ResponseEntity.ok(request);
    }

    @ApiOperation(value = "Retorna a lista das vakinhas que já atingiram sua meta")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista"),
    })
    @GetMapping("/closed")
    public List<RequestDTO> getClosedList() {
        return service.getClosedList();
    }

    @ApiOperation(value = "Cria uma vakinha pelo id de um usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a vakinha criada."),
            @ApiResponse(code = 400, message = "Usuário inválido.")
    })
    @PostMapping("/{idUser}")
    @Validated
    public ResponseEntity<RequestDTO> post(@PathVariable("idUser") Integer id,
                                           @RequestBody @Valid RequestCreateDTO request) throws Exception {
        RequestDTO created = service.add(id, request);
        return ResponseEntity.ok(created);
    }

    @ApiOperation(value = "Atualiza a vakinha pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a vakinha atualizada."),
            @ApiResponse(code = 400, message = "Vakinha não encontrada.")
    })
    @PutMapping("/{idRequest}")
    @Validated
    public ResponseEntity<RequestDTO> put(@PathVariable("idRequest") Integer id,
                                          @RequestBody @Valid RequestUpdateDTO data) throws Exception {
        RequestDTO updated = service.put(id, data);
        return ResponseEntity.ok(updated);
    }

    @ApiOperation(value = "Deleta a vakinha pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a vakinha deletada."),
            @ApiResponse(code = 400, message = "Vakinha não encontrada.")
    })
    @DeleteMapping("/{idRequest}")
    public ResponseEntity<RequestDTO> delete(@PathVariable("idRequest") Integer id) throws Exception {
        RequestDTO deleted = service.delete(id);
        return ResponseEntity.ok(deleted);
    }

    @ApiOperation(value = "Retorna uma lista de vakinhas pelo id de uma categoria")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista."),
            @ApiResponse(code = 400, message = "Categoria não existe.")
    })
    @GetMapping("/category/{idCategory}")
    public List<RequestDTO> getByCategory(@PathVariable("idCategory") Integer id) throws Exception {
        return service.getByCategory(id);
    }
}
