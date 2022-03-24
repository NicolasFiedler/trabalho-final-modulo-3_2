package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.service;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.category.CategoryCreateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.category.CategoryDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.request.RequestDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.Category;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.exception.BusinessRuleException;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.repository.CategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    @Autowired
    private ObjectMapper mapper;

    public List<CategoryDTO> findAll() {
        return repo.findAll()
                .stream()
                .map(category -> mapper.convertValue(category, CategoryDTO.class))
                .toList();
    }

    public CategoryDTO findById(Integer id) throws Exception {
        return mapper.convertValue(repo.findById(id), CategoryDTO.class);
    }

    public CategoryDTO create(CategoryCreateDTO category) throws Exception {
        if (this.nameAlreadyExists(category.getName())) {
            throw new BusinessRuleException("Categoria já existe.");
        }

        Category entity = mapper.convertValue(category, Category.class);
        Category created = repo.create(entity);

        return mapper.convertValue(created, CategoryDTO.class);
    }

    public CategoryDTO update(Integer id, CategoryCreateDTO data) throws Exception {
        CategoryDTO entityDTO = mapper.convertValue(data, CategoryDTO.class);
        Category updated = repo.update(id, mapper.convertValue(entityDTO, Category.class));
        return mapper.convertValue(updated, CategoryDTO.class);
    }

    public CategoryDTO delete(Integer id) throws Exception {
        Category deleted = repo.delete(id);
        return mapper.convertValue(deleted, CategoryDTO.class);
    }

    private boolean nameAlreadyExists(String name) {
        return this.findAll()
                .stream()
                .anyMatch(c -> c.getName().equalsIgnoreCase(name));
    }
}
