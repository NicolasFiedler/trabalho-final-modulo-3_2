package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.service;

<<<<<<< HEAD
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.bankaccount.BankAccountDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.BankAccount;
=======
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.BankAccountDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.BankAccountEntity;
>>>>>>> 064f85a7c101c9ccf0de40a73d5291497c2b9a2f
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.repository.BankAccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public BankAccountDTO create(BankAccountDTO bankAcccountCreate) throws Exception {

        BankAccountEntity bankAccountEntity = objectMapper.convertValue(bankAcccountCreate, BankAccountEntity.class);
        BankAccountEntity bankAccountEntityCreated = bankAccountRepository.create(bankAccountEntity);
        BankAccountDTO bankAccountDTO = objectMapper.convertValue(bankAccountEntityCreated, BankAccountDTO.class);



        return bankAccountDTO;
    }


    public BankAccountDTO update(Integer id,
                                 BankAccountDTO bankAccountUpdate) throws Exception {
        BankAccountEntity bankAccountEntity = objectMapper.convertValue(bankAccountUpdate, BankAccountEntity.class);
        bankAccountRepository.update(id, bankAccountEntity);
        return bankAccountUpdate;
    }

    public List<BankAccountDTO>list(){
        return bankAccountRepository.list()
                .stream()
                .map(bankAccountEntity -> objectMapper.convertValue(bankAccountEntity, BankAccountDTO.class))
                .collect(Collectors.toList());
    }

    public BankAccountDTO getBankAccountById(Integer id) throws Exception {
        BankAccountEntity bankAccountEntity = bankAccountRepository.getBankAccountById(id);
        BankAccountDTO bankAccountDTO = objectMapper.convertValue(bankAccountEntity, BankAccountDTO.class);
        return  bankAccountDTO;
    }

    public BankAccountDTO delete(Integer id) throws Exception {
        BankAccountEntity bankAccountEntity = bankAccountRepository.delete(id);
        BankAccountDTO bankAccountDTO = objectMapper.convertValue(bankAccountEntity, BankAccountDTO.class);
        return bankAccountDTO;
    }

}
