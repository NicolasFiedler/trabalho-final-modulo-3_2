package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.service;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.BankAccountDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.DonateCreateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.DonateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.BankAccount;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.Donate;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.repository.BankAccountRepository;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.repository.DonateRepository;
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

        BankAccount bankAccount = objectMapper.convertValue(bankAcccountCreate, BankAccount.class);
        BankAccount bankAccountCreated = bankAccountRepository.create(bankAccount);
        BankAccountDTO bankAccountDTO = objectMapper.convertValue(bankAccountCreated, BankAccountDTO.class);



        return bankAccountDTO;
    }


    public BankAccountDTO update(Integer id,
                                 BankAccountDTO bankAccountUpdate) throws Exception {
        BankAccount bankAccount = objectMapper.convertValue(bankAccountUpdate, BankAccount.class);
        bankAccountRepository.update(id, bankAccount);
        return bankAccountUpdate;
    }

    public List<BankAccountDTO>list(){
        return bankAccountRepository.list()
                .stream()
                .map(bankAccount -> objectMapper.convertValue(bankAccount, BankAccountDTO.class))
                .collect(Collectors.toList());
    }

    public BankAccountDTO getBankAccountById(Integer id) throws Exception {
        BankAccount bankAccount= bankAccountRepository.getBankAccountById(id);
        BankAccountDTO bankAccountDTO = objectMapper.convertValue(bankAccount, BankAccountDTO.class);
        return  bankAccountDTO;
    }

    public BankAccountDTO delete(Integer id) throws Exception {
        BankAccount bankAccount = bankAccountRepository.delete(id);
        BankAccountDTO bankAccountDTO = objectMapper.convertValue(bankAccount, BankAccountDTO.class);
        return bankAccountDTO;
    }

}
