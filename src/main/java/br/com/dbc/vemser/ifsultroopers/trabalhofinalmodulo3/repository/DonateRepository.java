package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.repository;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.DonateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonateRepository extends JpaRepository<DonateEntity, Integer> {

}
