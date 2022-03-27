package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.service;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.userdto.UsersCreateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.userdto.UsersDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.UsersEntity;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.documents.CNPJ;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.documents.CPF;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.exception.BusinessRuleException;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.repository.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final ObjectMapper objectMapper;

    public List<UsersDTO> list () {
        return usersRepository.findAll().stream()
                .map(this::formatUserDTODocument)
                .toList();
    }

    public UsersDTO getById (Integer id) throws BusinessRuleException {
        UsersEntity usersEntity = usersRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Usuario nao encontrado!"));

        return formatUserDTODocument(usersEntity);
    }

    public UsersDTO create (UsersCreateDTO usersCreateDTO) throws BusinessRuleException {
        UsersEntity u = validateAndSetDocument(usersCreateDTO);
        return formatUserDTODocument(usersRepository.save(u));
    }

    public UsersDTO update (Integer id, UsersCreateDTO usersCreateDTO) throws BusinessRuleException {
        getById(id);
        UsersEntity u = validateAndSetDocument(usersCreateDTO);
        u.setIdUser(id);
        return formatUserDTODocument(usersRepository.save(u));
    }

    public UsersDTO delete (Integer id) throws BusinessRuleException {
        UsersDTO u = getById(id);
        usersRepository.deleteById(id);
        return u;
    }

    public UsersDTO formatUserDTODocument (UsersEntity usersEntity) {
        if ((usersEntity.getType())){
            CNPJ cnpj = new CNPJ(usersEntity.getDocument());
            usersEntity.setDocument(cnpj.getCNPJ(true));
        } else {
            CPF cpf = new CPF(usersEntity.getDocument());
            usersEntity.setDocument(cpf.getCPF(true));
        }
        return objectMapper.convertValue(usersEntity, UsersDTO.class);
    }

    public UsersEntity validateAndSetDocument (UsersCreateDTO usersCreateDTO) throws BusinessRuleException {

        UsersEntity usersEntity = objectMapper.convertValue(usersCreateDTO, UsersEntity.class);

        CNPJ cnpj = new CNPJ(usersEntity.getDocument());
        CPF cpf = new CPF(usersEntity.getDocument());

        if (cnpj.isCNPJ()){
            //TODO - resolver update caso ele mesmo
            UsersEntity u = usersRepository.findByDocument(cnpj.getCNPJ(false));
            if (u != null ){
                throw new BusinessRuleException("CNPJ Invalido!");
            }
            usersEntity.setType(true);
            usersEntity.setDocument(cnpj.getCNPJ(false));
            return usersEntity;

        } else if (cpf.isCPF()){

            if (usersRepository.findByDocument(cpf.getCPF(false)) != null){
                throw new BusinessRuleException("CPF Invalido!");
            }
            usersEntity.setType(false);
            usersEntity.setDocument(cpf.getCPF(false));
            return usersEntity;

        } else {
            throw new BusinessRuleException("CPF ou CNPJ invalido!");
        }
    }



}
