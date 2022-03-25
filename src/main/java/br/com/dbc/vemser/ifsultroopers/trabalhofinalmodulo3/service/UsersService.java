package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.service;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.userdto.UsersCreateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.userdto.UsersDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.UsersEntity;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.documents.CNPJ;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.documents.CPF;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.exception.BusinessRuleException;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.repository.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public List<UsersDTO> list () {
        return usersRepository.findAll().stream()
                .map(usersEntity -> objectMapper.convertValue(usersEntity, UsersDTO.class))
                .map(usersDTO -> {
                    if ((usersDTO.getType())){
                        CNPJ cnpj = new CNPJ(usersDTO.getDocument());
                        usersDTO.setDocument(cnpj.getCNPJ(true));
                    } else {
                        CPF cpf = new CPF(usersDTO.getDocument());
                        usersDTO.setDocument(cpf.getCPF(true));
                    }
                    return usersDTO;
                })
                .toList();
    }

    public UsersDTO getById (Integer id) throws BusinessRuleException {
        UsersDTO usersDTO = objectMapper.convertValue(usersRepository.getById(id), UsersDTO.class);
        if ((usersDTO.getType())){
            CNPJ cnpj = new CNPJ(usersDTO.getDocument());
            usersDTO.setDocument(cnpj.getCNPJ(true));
        } else {
            CPF cpf = new CPF(usersDTO.getDocument());
            usersDTO.setDocument(cpf.getCPF(true));
        }
        return usersDTO;
    }

    public UsersDTO create (UsersCreateDTO usersCreateDTO) throws BusinessRuleException {
//        if (usersCreateDTO.getType()){
//            CNPJ cnpj = new CNPJ(usersCreateDTO.getDocument());
//            if (!cnpj.isCNPJ() || !documentAlreadyExists(cnpj.getCNPJ(false))){
//                throw new BusinessRuleException("CNPJ Invalido!");
//            }
//            usersCreateDTO.setDocument(cnpj.getCNPJ(false));
//        } else {
//            CPF cpf = new CPF(usersCreateDTO.getDocument());
//            if (!cpf.isCPF() || !documentAlreadyExists(cpf.getCPF(false))){
//                throw new BusinessRuleException("CPF Invalido!");
//            }
//            usersCreateDTO.setDocument(cpf.getCPF(false));
//        }

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

        return objectMapper.convertValue(usersRepository.save(objectMapper.convertValue(usersCreateDTO, UsersEntity.class)), UsersDTO.class);
    }

    public UsersDTO update (Integer id, UsersCreateDTO usersCreateDTO) throws BusinessRuleException {
//            if (usersCreateDTO.getType()){
//                CNPJ cnpj = new CNPJ(usersCreateDTO.getDocument());
//                if (!cnpj.isCNPJ() || testForUpdate(id, cnpj.getCNPJ(false))){
//                    throw new BusinessRuleException("CNPJ Invalido!");
//                }
//                usersCreateDTO.setDocument(cnpj.getCNPJ(false));
//            } else {
//                CPF cpf = new CPF(usersCreateDTO.getDocument());
//                if (!cpf.isCPF() || testForUpdate(id, cpf.getCPF(false))){
//                    throw new BusinessRuleException("CPF Invalido!");
//                }
//                usersCreateDTO.setDocument(cpf.getCPF(false));
//            }

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

        UsersDTO u = objectMapper.convertValue(usersCreateDTO, UsersDTO.class);
        u.setIdUser(id);

        return objectMapper.convertValue(usersRepository.save(objectMapper.convertValue(u, UsersEntity.class)), UsersDTO.class);
    }

    public UsersDTO delete (Integer id) throws BusinessRuleException {
        UsersDTO u = objectMapper.convertValue(usersRepository.findById(id), UsersDTO.class);
        usersRepository.deleteById(id);
        return u;
    }

    private boolean documentAlreadyExists(String document) {
        return this.list().stream()
                .anyMatch(u -> u.getDocument().equalsIgnoreCase(document));
    }

    private boolean testForUpdate (Integer id, String document) throws BusinessRuleException {
//        if (!documentAlreadyExists(document) && getById(id).getDocument().equals(document)){
//            return false;
//        }
//        return true;
        UsersDTO exists = this.getById(id);
        for (UsersDTO updated : this.list()) {
            if (updated.equals(exists)) {
                continue;
            } else if (updated.getDocument().equalsIgnoreCase(document)) {
                return true;
            }
        }
        return false;
    }

}
