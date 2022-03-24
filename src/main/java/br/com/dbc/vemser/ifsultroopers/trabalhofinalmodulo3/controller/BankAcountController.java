package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.controller;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.BankAccountDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.service.BankAccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/BankAccount") // localhost:8080/pessoa
@Validated
public class BankAcountController {
    @Autowired
    private BankAccountService bankAccountService;
//    @Autowired
//    private EmailService emailService;

    @ApiOperation(value = "Cria e retorna a Bank Account criada")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a Bank Account criada"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping // localhost:8080/pessoa
    public ResponseEntity<BankAccountDTO> create(@Valid @RequestBody BankAccountDTO bankAccount) throws Exception {
        BankAccountDTO bankAccountDTO = bankAccountService.create(bankAccount);
//        emailService.pessoaSendEmail(pessoaDTO, "Seu cadastro foi realizado com sucesso, seu identificador é "+pessoaDTO.getIdPessoa()+".", "Cadastro");
        return ResponseEntity.ok(bankAccountDTO);
    }

    @ApiOperation(value = "Retorna a lista de Bank Accounts")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de Bank Account"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping // localhost:8080/pessoa
    public ResponseEntity<List<BankAccountDTO>> list() {
        return ResponseEntity.ok(bankAccountService.list());
    }

    @ApiOperation(value = "Retorna a lista de Bank Accounts por Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de Bank Accounts por id"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/{idBankAccount}")
    public ResponseEntity<BankAccountDTO> getDonateById(@PathVariable("idBankAccount") Integer id) throws Exception {
        return ResponseEntity.ok(bankAccountService.getBankAccountById(id));
    }


    @ApiOperation(value = "Retorna a Bank Account editada pelo Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a donate Editada pelo Id"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PutMapping("/{idBankAccount}") // localhost:8080/pessoa/1000
    public ResponseEntity<BankAccountDTO> update(@PathVariable("idBankAccount") Integer id,
                                            @Valid @RequestBody BankAccountDTO bankAccountDTO) throws Exception {
//        emailService.pessoaSendEmail(pessoaAtualizar, "Seus dados foram atualizados no nosso sistema.", "Atualização de dados");
        return  ResponseEntity.ok(bankAccountService.update(id, bankAccountDTO));
    }

    @ApiOperation(value = "Retorna a Bank Account deletada pelo Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a Bank Account deletada pelo Id"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @DeleteMapping("/{idBankAccount}") // localhost:8080/pessoa/10
    public ResponseEntity<BankAccountDTO> delete(@PathVariable("idBankAccount") Integer id) throws Exception {
        BankAccountDTO bankAccountDTO = bankAccountService.delete(id);
//        emailService.pessoaSendEmail(pessoaDTO, "Você perdeu o acesso ao nosso sistema.", " Delet de conta");
        return ResponseEntity.ok(bankAccountDTO);
    }
}
