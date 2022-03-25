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
                .map(usersEntity -> {
                    UsersDTO usersDTO = objectMapper.convertValue(usersEntity, UsersDTO.class);
                    return formatUserDTODocument(usersDTO);
                })
                .toList();
    }

    public UsersDTO getById (Integer id) throws BusinessRuleException {
        UsersEntity usersEntity = usersRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Usuario nao encontrado!"));
        UsersDTO usersDTO = objectMapper.convertValue(usersEntity, UsersDTO.class);

        return formatUserDTODocument(usersDTO);
    }

    public UsersDTO create (UsersCreateDTO usersCreateDTO) throws BusinessRuleException {
        UsersEntity u = validateAndSetDocument(usersCreateDTO);
        return objectMapper.convertValue(usersRepository.save(u), UsersDTO.class);
    }

    public UsersDTO update (Integer id, UsersCreateDTO usersCreateDTO) throws BusinessRuleException {

        getById(id);

        UsersEntity u = validateAndSetDocument(usersCreateDTO);
        u.setIdUser(id);

        return objectMapper.convertValue(usersRepository.save(u), UsersDTO.class);
    }

    public UsersDTO delete (Integer id) throws BusinessRuleException {
        UsersDTO u = getById(id);
        usersRepository.deleteById(id);
        return u;
    }

    public UsersDTO formatUserDTODocument (UsersDTO usersDTO) {
        if ((usersDTO.getType())){
            CNPJ cnpj = new CNPJ(usersDTO.getDocument());
            usersDTO.setDocument(cnpj.getCNPJ(true));
        } else {
            CPF cpf = new CPF(usersDTO.getDocument());
            usersDTO.setDocument(cpf.getCPF(true));
        }
        return usersDTO;
    }

    public UsersEntity validateAndSetDocument (UsersCreateDTO usersCreateDTO) throws BusinessRuleException {

        if (usersCreateDTO.getType()){
            CNPJ cnpj = new CNPJ(usersCreateDTO.getDocument());
            if (!cnpj.isCNPJ()){
                throw new BusinessRuleException("CNPJ Invalido!");
            }
            usersCreateDTO.setDocument(cnpj.getCNPJ(false));
        } else {
            CPF cpf = new CPF(usersCreateDTO.getDocument());
            if (!cpf.isCPF()){
                throw new BusinessRuleException("CPF Invalido!");
            }
            usersCreateDTO.setDocument(cpf.getCPF(false));
        }

        return objectMapper.convertValue(usersCreateDTO, UsersEntity.class);
    }

}
