package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.service;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.request.RequestCreateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.request.RequestDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.request.RequestUpdateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.Category;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.RequestEntity;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.exception.BusinessRuleException;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.repository.RequestRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class RequestService {
    
    private RequestRepository requestRepository;
    
    private UsersService usersService;
    
    private Category category;

    private ObjectMapper objectMapper;

    public List<RequestDTO> get() {
        return requestRepository.findAll()
                .stream()
                .map(requestEntity -> objectMapper.convertValue(requestEntity, RequestDTO.class))
                .toList();
    }

    public RequestDTO create(Integer id, RequestCreateDTO request) throws BusinessRuleException {
        RequestEntity requestEntity = objectMapper.convertValue(request, RequestEntity.class);
        requestEntity.setIdUser(id);
        RequestEntity created = requestRepository.save(requestEntity);
        return objectMapper.convertValue(created, RequestDTO.class);
    }

    public RequestDTO getById(Integer id) throws BusinessRuleException {
        RequestEntity requestEntity = requestRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Vaquinha não encontrada!"));
        return objectMapper.convertValue(requestEntity, RequestDTO.class);
    }

    public RequestDTO update(Integer id, RequestUpdateDTO request) throws BusinessRuleException {
        RequestEntity requestEntity = requestRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Vaquinha não encontrada!"));
        requestEntity.setTitle(request.getTitle());
        requestEntity.setDescription(request.getDescription());
        //TALVEZ VALIDAR P N MUDAR META SE FOR MENOR QUE O VALOR ATUAL ETC
        requestEntity.setGoal(request.getGoal());
        return objectMapper.convertValue(request, RequestDTO.class);
    }

    public RequestDTO delete(Integer id) throws BusinessRuleException {
        RequestEntity requestEntity = requestRepository.findById(id)
                .orElseThrow(()-> new BusinessRuleException("Vaquinha não encontrada!"));
        requestRepository.deleteById(id);
        return objectMapper.convertValue(requestEntity, RequestDTO.class);
    }

    public void incrementReachedValue(Integer idRequest, Double donateValue) throws Exception {
        RequestEntity requestEntity = requestRepository.findById(idRequest)
                .orElseThrow(() -> new BusinessRuleException("Vaquinha não encontrada!"));
        requestEntity.setReachedValue(requestEntity.getReachedValue() + donateValue);
    }

}

