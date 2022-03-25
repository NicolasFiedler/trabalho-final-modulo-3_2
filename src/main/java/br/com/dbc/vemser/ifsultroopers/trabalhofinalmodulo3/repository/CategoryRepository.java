package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.repository;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.CategoryEntity;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.exception.BusinessRuleException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class CategoryRepository {

    public List<CategoryEntity> list = new ArrayList<>();
    public static AtomicInteger COUNTER = new AtomicInteger();

    public CategoryRepository() {
        list.add(new CategoryEntity(COUNTER.incrementAndGet(), "Pobreza", "Falta de recursos financeiros."));
        list.add(new CategoryEntity(COUNTER.incrementAndGet(), "Sonhos", "Sonhos que as pessoas desejam alcançar."));
        list.add(new CategoryEntity(COUNTER.incrementAndGet(), "Lutas Sociais", "Lutas em prol de causas sociais."));
    }

    public List<CategoryEntity> findAll() {
        return list;
    }

    public CategoryEntity findById(Integer id) throws Exception {
        return list.stream()
                .filter(categoryEntity -> categoryEntity.getIdCategory().equals(id))
                .findFirst()
                .orElseThrow(() -> new BusinessRuleException("Categoria não encontrada."));
    }

    public CategoryEntity create(CategoryEntity categoryEntity) {
        categoryEntity.setIdCategory(COUNTER.incrementAndGet());
        list.add(categoryEntity);
        return categoryEntity;
    }

    public CategoryEntity update(Integer id, CategoryEntity categoryEntity) throws Exception {
        CategoryEntity exists = this.findById(id);
        exists.setName(categoryEntity.getName());
        exists.setDescription(categoryEntity.getDescription());
        return exists;
    }

    public CategoryEntity delete(Integer id) throws Exception {
        CategoryEntity categoryEntity = this.findById(id);
        list.remove(categoryEntity);
        return categoryEntity;
    }
}
