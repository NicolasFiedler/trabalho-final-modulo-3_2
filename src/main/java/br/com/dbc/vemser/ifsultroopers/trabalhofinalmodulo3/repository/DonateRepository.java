package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.repository;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.Donate;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.Request;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.exception.BusinessRuleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Repository
public class DonateRepository {

    private static List<Donate> listDonate = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();

    public DonateRepository() {
        listDonate.add(new Donate(COUNTER.incrementAndGet() /*1*/, 1, "Maicon Gerardi","ana.gocthel@dbccompany.com.br", 100.00, "Boa sorte!"));
        listDonate.add(new Donate(COUNTER.incrementAndGet() /*1*/, 1, "Ana Vitória","ana.gocthel@dbccompany.com.br", 200.00, "Boa sorte!"));
        listDonate.add(new Donate(COUNTER.incrementAndGet() /*1*/, 2, "Nicolas Fiedler","ana.gocthel@dbccompany.com.br", 400.00, "Boa sorte!"));
        listDonate.add(new Donate(COUNTER.incrementAndGet() /*1*/, 3, "Maria Eduarda","ana.gocthel@dbccompany.com.br", 50.00, "Boa sorte!"));
        listDonate.add(new Donate(COUNTER.incrementAndGet() /*1*/, 4, "Augusto Oliveira","ana.gocthel@dbccompany.com.br", 10.00, "Boa sorte!"));
    }

    public Donate create(Donate donate) throws Exception {
        donate.setId_donate(COUNTER.incrementAndGet());
        listDonate.add(donate);

        return donate;
    }

    public List<Donate> list() {
        return listDonate;
    }

    public Donate update(Integer id,
                         Donate donateUpdate) throws BusinessRuleException {
        Donate donateRecovered = listDonate.stream()
                .filter(donate -> donate.getId_donate().equals(id))
                .findFirst()
                .orElseThrow(() -> new BusinessRuleException("Donate não econtrada"));
        donateRecovered.setDonator_name(donateUpdate.getDonator_name());
        donateRecovered.setDonator_email(donateUpdate.getDonator_email());
        donateRecovered.setDonate_value(donateUpdate.getDonate_value());
        donateRecovered.setDescription(donateUpdate.getDescription());
        return donateRecovered;
    }

    public Donate getDonataById(Integer id) throws BusinessRuleException{
        Donate pessoaRecuperada = listDonate.stream()
                .filter(pessoa -> pessoa.getId_donate().equals(id))
                .findFirst()
                .orElseThrow(() -> new BusinessRuleException("Donate não econtrada"));
        return pessoaRecuperada;
    }

    public Donate delete(Integer id) throws BusinessRuleException {
        Donate pessoaRecuperada = listDonate.stream()
                .filter(pessoa -> pessoa.getId_donate().equals(id))
                .findFirst()
                .orElseThrow(() -> new BusinessRuleException("Donate não econtrada"));
        listDonate.remove(pessoaRecuperada);
        return pessoaRecuperada;
    }

}
