package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.repository;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.BankAccount;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.Donate;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.exception.BusinessRuleException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class BankAccountRepository {
    private static List<BankAccount> listBankAccount = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();

    public BankAccountRepository() {
        listBankAccount.add(new BankAccount(COUNTER.incrementAndGet() /*1*/, "0665216352", "10202"));
        listBankAccount.add(new BankAccount(COUNTER.incrementAndGet() /*1*/, "0665216352", "10202"));
        listBankAccount.add(new BankAccount(COUNTER.incrementAndGet() /*1*/, "0665216352", "10202"));
        listBankAccount.add(new BankAccount(COUNTER.incrementAndGet() /*1*/, "0665216352", "10202"));
        listBankAccount.add(new BankAccount(COUNTER.incrementAndGet() /*1*/, "0665216352", "10202"));

    }

    public BankAccount create(BankAccount bankAccount) {
        bankAccount.setId_bank_account(COUNTER.incrementAndGet());
        listBankAccount.add(bankAccount);
        return bankAccount;
    }

    public List<BankAccount> list() {
        return listBankAccount;
    }

    public BankAccount update(Integer id,
                         BankAccount bankAccountUpdate) throws BusinessRuleException {
        BankAccount bankAccountRecovered = listBankAccount.stream()
                .filter(bankAccount -> bankAccount.getId_bank_account().equals(id))
                .findFirst()
                .orElseThrow(() -> new BusinessRuleException("Conta Bancaria não econtrada"));
        bankAccountRecovered.setAccount_number(bankAccountUpdate.getAccount_number());
        bankAccountRecovered.setAgency(bankAccountUpdate.getAgency());
        return bankAccountRecovered;
    }

    public BankAccount getBankAccountById(Integer id) throws BusinessRuleException{
        BankAccount bankAccountRecovered = listBankAccount.stream()
                .filter(pessoa -> pessoa.getId_bank_account().equals(id))
                .findFirst()
                .orElseThrow(() -> new BusinessRuleException("Conta Bancaria não econtrada"));
        return bankAccountRecovered;
    }

    public BankAccount delete(Integer id) throws BusinessRuleException {
        BankAccount bankAccountRecovered = listBankAccount.stream()
                .filter(pessoa -> pessoa.getId_bank_account().equals(id))
                .findFirst()
                .orElseThrow(() -> new BusinessRuleException("Conta Bancaria não econtrada"));
        listBankAccount.remove(bankAccountRecovered);
        return bankAccountRecovered;
    }
}
