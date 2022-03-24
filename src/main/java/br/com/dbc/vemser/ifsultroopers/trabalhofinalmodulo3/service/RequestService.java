package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.service;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.category.CategoryDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.request.RequestCreateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.request.RequestDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.request.RequestUpdateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.userdto.UsersDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.Request;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.repository.RequestRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepository repo;

    @Autowired
    private UsersService usersService;

    @Autowired
    private CategoryService categoryService;


    @Autowired
    private ObjectMapper mapper;

    public List<RequestDTO> get() {
        return repo.getAll()
                .stream()
                .map(request -> mapper.convertValue(request, RequestDTO.class))
                .toList();
    }

    public RequestDTO get(Integer id) throws Exception {
        Request entity = repo.getById(id);
        return mapper.convertValue(entity, RequestDTO.class);
    }

    public RequestDTO add(Integer id, RequestCreateDTO request) throws Exception {
        UsersDTO user = usersService.getById(id);

        Request entity = mapper.convertValue(request, Request.class);
        Request created = repo.create(id, entity);
        return mapper.convertValue(created, RequestDTO.class);
    }

    public RequestDTO put(Integer id, RequestUpdateDTO request) throws Exception {
        Request updated = repo.update(id, request);
        return mapper.convertValue(updated, RequestDTO.class);
    }

    public RequestDTO delete(Integer id) throws Exception {
        Request request = repo.delete(id);
        return mapper.convertValue(request, RequestDTO.class);
    }

    public RequestDTO incrementReachedValue(Integer id, Double value) throws Exception {
        Request request = repo.incrementReachedValue(id, value);
        return mapper.convertValue(request, RequestDTO.class);
    }

    public List<RequestDTO> deleteAll(Integer id) throws Exception {
        List<Request> list = repo.deleteAll(id);
        return list.stream()
                .map(request -> mapper.convertValue(request, RequestDTO.class))
                .toList();
    }

    public List<RequestDTO> getByCategory(Integer id) throws Exception {
        CategoryDTO categoryDTO = categoryService.findById(id);

        return repo.getByCategory(id)
                .stream()
                .map(request -> mapper.convertValue(request, RequestDTO.class))
                .toList();
    }

    public List<RequestDTO> getClosedList() {
        return repo.getClosedList()
                .stream()
                .map(request -> mapper.convertValue(request, RequestDTO.class))
                .toList();
    }
}
