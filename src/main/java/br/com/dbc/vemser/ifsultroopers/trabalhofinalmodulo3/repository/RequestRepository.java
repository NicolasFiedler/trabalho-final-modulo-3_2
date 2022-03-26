package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.repository;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RequestRepository extends JpaRepository<RequestEntity, Integer> {


}
