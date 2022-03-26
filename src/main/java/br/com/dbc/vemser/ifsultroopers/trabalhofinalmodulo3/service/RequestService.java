package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.service;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.request.RequestCreateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.request.RequestDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.request.RequestUpdateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.Category;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.RequestEntity;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.exception.BusinessRuleException;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.repository.RequestRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
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

    public RequestDTO getById(Integer id) throws BusinessRuleException {
        RequestEntity requestEntity = requestRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Vaquinha não encontrada!"));
        return objectMapper.convertValue(requestEntity, RequestDTO.class);
    }

    public RequestDTO create(Integer id, RequestCreateDTO request) throws BusinessRuleException {
        RequestEntity requestEntity = objectMapper.convertValue(request, RequestEntity.class);
        RequestEntity created = requestRepository.save(requestEntity);
        return objectMapper.convertValue(created, RequestDTO.class);
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


//    Tava fazendo mas travei

//    public RequestDTO incrementReachedValue(Integer id, Double currentValue) throws BusinessRuleException {
//        RequestEntity requestEntity = requestRepository.findById(id)
//                .orElseThrow(() -> new BusinessRuleException("Vaquinha não encontrada!"));
//        Double newValue = requestEntity.getReachedValue()
//        requestEntity.setReachedValue(newValue);
//        return objectMapper.convertValue(currentValue, RequestDTO.class);
//    }


//    Mesma coisa que acima

//    public List<RequestDTO> deleteAll(Integer id) throws BusinessRuleException {
//        List<RequestEntity> list = requestRepository.deleteAll();
//        return list.stream()
//                .map(request -> objectMapper.convertValue(request, RequestDTO.class))
//                .toList();
//    }
//

//    Não mexi

//    public List<RequestDTO> getByCategory(Integer id) throws BusinessRuleException {
//        CategoryDTO categoryDTO = category.findById(id);
//
//        return requestRepository.getByCategory(id)
//                .stream()
//                .map(request -> objectMapper.convertValue(request, RequestDTO.class))
//                .toList();
//    }


    // Não mexi

//    public List<RequestDTO> getClosedList() {
//        return requestRepository.getClosedList()
//                .stream()
//                .map(request -> objectMapper.convertValue(request, RequestDTO.class))
//                .toList();
//    }
}
