package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.service;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.bankaccount.BankAccountDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.request.RequestCreateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.request.RequestDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.request.RequestUpdateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.userdto.UsersDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.BankAccountEntity;
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

    private final BankAccountService bankAccountService;

    public List<RequestDTO> list() {
        return requestRepository.findAll()
                .stream()
                .map(requestEntity -> objectMapper.convertValue(requestEntity, RequestDTO.class))
                .collect(Collectors.toList());
    }

    public RequestDTO create(Integer id, RequestCreateDTO request, Category category) throws BusinessRuleException {
        RequestEntity requestEntity = objectMapper.convertValue(request, RequestEntity.class);

        UsersDTO usersDTO = usersService.getById(id);
        UsersEntity usersEntity = objectMapper.convertValue(usersDTO, UsersEntity.class);
        requestEntity.setUsersEntity(usersEntity);

        requestEntity.setIdUser(id);
        requestEntity.setReachedValue(0.00);
        requestEntity.setStatusRequest(true);
        requestEntity.setCategory(category);

        BankAccountDTO bankAccountDTO = bankAccountService.getBankAccountById(requestEntity.getIdBankAccount());
        BankAccountEntity bankAccountEntity = objectMapper.convertValue(bankAccountDTO, BankAccountEntity.class);
        requestEntity.setBankAccountEntity(bankAccountEntity);

        RequestEntity created = requestRepository.save(requestEntity);
        return objectMapper.convertValue(created, RequestDTO.class);
    }

    public RequestDTO getById(Integer id) throws BusinessRuleException {
        RequestEntity requestEntity = requestRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Vakinha não encontrada!"));
        return objectMapper.convertValue(requestEntity, RequestDTO.class);
    }

    public RequestDTO update(Integer id, RequestUpdateDTO request, Category category) throws BusinessRuleException {
        RequestEntity requestEntity = requestRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Vakinha não encontrada!"));
        requestEntity.setTitle(request.getTitle());
        requestEntity.setDescription(request.getDescription());
        requestEntity.setGoal(request.getGoal());
        requestEntity.setCategory(category);

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

