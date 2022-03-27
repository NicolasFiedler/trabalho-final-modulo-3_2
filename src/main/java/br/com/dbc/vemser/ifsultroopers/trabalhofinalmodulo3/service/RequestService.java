package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.service;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.request.RequestCreateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.request.RequestDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.request.RequestUpdateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.userdto.UsersCreateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.userdto.UsersDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.Category;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.RequestEntity;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.UsersEntity;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.exception.BusinessRuleException;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.repository.RequestRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class RequestService {
    
    private final RequestRepository requestRepository;
    
    private final UsersService usersService;

    private final ObjectMapper objectMapper;

    public List<RequestDTO> list() {
        return requestRepository.findAll()
                .stream()
                .map(requestEntity -> objectMapper.convertValue(requestEntity, RequestDTO.class))
                .collect(Collectors.toList());
    }

    public RequestDTO create(Integer id, RequestCreateDTO request) throws BusinessRuleException {
        RequestEntity requestEntity = objectMapper.convertValue(request, RequestEntity.class);
        requestEntity.setIdUser(id);
        RequestEntity created = requestRepository.save(requestEntity);
        return objectMapper.convertValue(created, RequestDTO.class);
    }

    public RequestDTO getById(Integer id) throws BusinessRuleException {
        RequestEntity requestEntity = requestRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Vakinha não encontrada!"));
        return objectMapper.convertValue(requestEntity, RequestDTO.class);
    }

    public RequestDTO update(Integer id, RequestUpdateDTO request) throws BusinessRuleException {
        RequestEntity requestEntity = requestRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Vakinha não encontrada!"));
        requestEntity.setTitle(request.getTitle());
        requestEntity.setDescription(request.getDescription());
        requestEntity.setGoal(request.getGoal());

        return objectMapper.convertValue(requestRepository.save(requestEntity), RequestDTO.class);
    }

    public RequestDTO delete(Integer id) throws BusinessRuleException {
        RequestEntity requestEntity = requestRepository.findById(id)
                .orElseThrow(()-> new BusinessRuleException("Vakinha não encontrada!"));
        requestRepository.deleteById(id);
        return objectMapper.convertValue(requestEntity, RequestDTO.class);
    }

    public void incrementReachedValue(Integer idRequest, Double donateValue) throws Exception {
        RequestEntity requestEntity = requestRepository.findById(idRequest)
                .orElseThrow(() -> new BusinessRuleException("Vakinha não encontrada!"));
        requestEntity.setReachedValue(requestEntity.getReachedValue() + donateValue);
    }

}

