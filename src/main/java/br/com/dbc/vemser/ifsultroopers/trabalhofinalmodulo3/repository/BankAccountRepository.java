package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.repository;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.BankAccountEntity;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.exception.BusinessRuleException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class BankAccountRepository {
    private static List<BankAccountEntity> listBankAccountEntity = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();

    public BankAccountRepository() {
        listBankAccountEntity.add(new BankAccountEntity(COUNTER.incrementAndGet() /*1*/, "0665216352", "10202"));
        listBankAccountEntity.add(new BankAccountEntity(COUNTER.incrementAndGet() /*1*/, "0665216352", "10202"));
        listBankAccountEntity.add(new BankAccountEntity(COUNTER.incrementAndGet() /*1*/, "0665216352", "10202"));
        listBankAccountEntity.add(new BankAccountEntity(COUNTER.incrementAndGet() /*1*/, "0665216352", "10202"));
        listBankAccountEntity.add(new BankAccountEntity(COUNTER.incrementAndGet() /*1*/, "0665216352", "10202"));

    }

    public BankAccountEntity create(BankAccountEntity bankAccountEntity) {
        bankAccountEntity.setId_bank_account(COUNTER.incrementAndGet());
        listBankAccountEntity.add(bankAccountEntity);
        return bankAccountEntity;
    }

    public List<BankAccountEntity> list() {
        return listBankAccountEntity;
    }

    public BankAccountEntity update(Integer id,
                                    BankAccountEntity bankAccountEntityUpdate) throws BusinessRuleException {
        BankAccountEntity bankAccountEntityRecovered = listBankAccountEntity.stream()
                .filter(bankAccountEntity -> bankAccountEntity.getId_bank_account().equals(id))
                .findFirst()
                .orElseThrow(() -> new BusinessRuleException("Conta Bancaria não econtrada"));
        bankAccountEntityRecovered.setAccount_number(bankAccountEntityUpdate.getAccount_number());
        bankAccountEntityRecovered.setAgency(bankAccountEntityUpdate.getAgency());
        return bankAccountEntityRecovered;
    }

    public BankAccountEntity getBankAccountById(Integer id) throws BusinessRuleException{
        BankAccountEntity bankAccountEntityRecovered = listBankAccountEntity.stream()
                .filter(pessoa -> pessoa.getId_bank_account().equals(id))
                .findFirst()
                .orElseThrow(() -> new BusinessRuleException("Conta Bancaria não econtrada"));
        return bankAccountEntityRecovered;
    }

    public BankAccountEntity delete(Integer id) throws BusinessRuleException {
        BankAccountEntity bankAccountEntityRecovered = listBankAccountEntity.stream()
                .filter(pessoa -> pessoa.getId_bank_account().equals(id))
                .findFirst()
                .orElseThrow(() -> new BusinessRuleException("Conta Bancaria não econtrada"));
        listBankAccountEntity.remove(bankAccountEntityRecovered);
        return bankAccountEntityRecovered;
    }
}
