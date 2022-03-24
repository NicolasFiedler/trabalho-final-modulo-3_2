package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.controller;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.category.CategoryCreateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.category.CategoryDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.Category;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.service.CategoryService;
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
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @ApiOperation(value = "Retorna uma lista de categorias")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista"),
    })
    @GetMapping
    public List<CategoryDTO> get() {
        return service.findAll();
    }

    @ApiOperation(value = "Retorna uma categoria pelo id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista"),
            @ApiResponse(code = 400, message = "Não encontra a categoria")
    })
    @GetMapping("/{idCategory}")
    public ResponseEntity<CategoryDTO> get(@PathVariable("idCategory") Integer id) throws Exception {
        CategoryDTO category = service.findById(id);
        return ResponseEntity.ok(category);
    }

    @ApiOperation(value = "Cria uma categoria")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma categoria"),
            @ApiResponse(code = 400, message = "Categoria já existe.")
    })
    @PostMapping
    @Validated
    public ResponseEntity<CategoryDTO> add(@RequestBody @Valid CategoryCreateDTO category) throws Exception {
        CategoryDTO created = service.create(category);
        return ResponseEntity.ok(created);
    }

    @ApiOperation(value = "Atualiza uma categoria pelo id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a categoria atualizada."),
            @ApiResponse(code = 400, message = "Categoria não existe.")
    })
    @PutMapping("/{idCategory}")
    @Validated
    public ResponseEntity<CategoryDTO> put(@PathVariable("idCategory") Integer id,
                                           @RequestBody @Valid CategoryCreateDTO data) throws Exception {
        CategoryDTO updated = service.update(id, data);
        return ResponseEntity.ok(updated);
    }

    @ApiOperation(value = "Deleta uma categoria pelo id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a categoria deletada."),
            @ApiResponse(code = 400, message = "Categoria não existe.")
    })
    @DeleteMapping("/{idCategory}")
    public ResponseEntity<CategoryDTO> delete(@PathVariable("idCategory") Integer id) throws Exception {
        CategoryDTO deleted = service.delete(id);
        return ResponseEntity.ok(deleted);
    }
}
