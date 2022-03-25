package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.repository;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.DonateEntity;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.exception.BusinessRuleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Repository
public class DonateRepository {

    private static List<DonateEntity> listDonateEntity = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();

    public DonateRepository() {
        listDonateEntity.add(new DonateEntity(COUNTER.incrementAndGet() /*1*/, 1, "Maicon Gerardi","ana.gocthel@dbccompany.com.br", 100.00, "Boa sorte!"));
        listDonateEntity.add(new DonateEntity(COUNTER.incrementAndGet() /*1*/, 1, "Ana Vitória","ana.gocthel@dbccompany.com.br", 200.00, "Boa sorte!"));
        listDonateEntity.add(new DonateEntity(COUNTER.incrementAndGet() /*1*/, 2, "Nicolas Fiedler","ana.gocthel@dbccompany.com.br", 400.00, "Boa sorte!"));
        listDonateEntity.add(new DonateEntity(COUNTER.incrementAndGet() /*1*/, 3, "Maria Eduarda","ana.gocthel@dbccompany.com.br", 50.00, "Boa sorte!"));
        listDonateEntity.add(new DonateEntity(COUNTER.incrementAndGet() /*1*/, 4, "Augusto Oliveira","ana.gocthel@dbccompany.com.br", 10.00, "Boa sorte!"));
    }

    public DonateEntity create(DonateEntity donateEntity) throws Exception {
        donateEntity.setId_donate(COUNTER.incrementAndGet());
        listDonateEntity.add(donateEntity);

        return donateEntity;
    }

    public List<DonateEntity> list() {
        return listDonateEntity;
    }

    public DonateEntity update(Integer id,
                               DonateEntity donateEntityUpdate) throws BusinessRuleException {
        DonateEntity donateEntityRecovered = listDonateEntity.stream()
                .filter(donateEntity -> donateEntity.getId_donate().equals(id))
                .findFirst()
                .orElseThrow(() -> new BusinessRuleException("Donate não econtrada"));
        donateEntityRecovered.setDonator_name(donateEntityUpdate.getDonator_name());
        donateEntityRecovered.setDonator_email(donateEntityUpdate.getDonator_email());
        donateEntityRecovered.setDonate_value(donateEntityUpdate.getDonate_value());
        donateEntityRecovered.setDescription(donateEntityUpdate.getDescription());
        return donateEntityRecovered;
    }

    public DonateEntity getDonataById(Integer id) throws BusinessRuleException{
        DonateEntity pessoaRecuperada = listDonateEntity.stream()
                .filter(pessoa -> pessoa.getId_donate().equals(id))
                .findFirst()
                .orElseThrow(() -> new BusinessRuleException("Donate não econtrada"));
        return pessoaRecuperada;
    }

    public DonateEntity delete(Integer id) throws BusinessRuleException {
        DonateEntity pessoaRecuperada = listDonateEntity.stream()
                .filter(pessoa -> pessoa.getId_donate().equals(id))
                .findFirst()
                .orElseThrow(() -> new BusinessRuleException("Donate não econtrada"));
        listDonateEntity.remove(pessoaRecuperada);
        return pessoaRecuperada;
    }

}
