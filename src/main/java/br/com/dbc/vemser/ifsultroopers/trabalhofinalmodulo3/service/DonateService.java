package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.service;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.donate.DonateCreateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.donate.DonateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.DonateEntity;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.repository.DonateRepository;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.repository.RequestRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonateService {
    @Autowired
    private DonateRepository donateRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private RequestRepository requestRepository = new RequestRepository(true);

    public DonateDTO create(DonateCreateDTO donateCreate) throws Exception {

        DonateEntity donateEntity = objectMapper.convertValue(donateCreate, DonateEntity.class);
        requestRepository.incrementReachedValue(donateEntity.getIdRequest(), donateEntity.getDonate_value());
        DonateEntity donateEntityCreated = donateRepository.create(donateEntity);
        DonateDTO donateDTO = objectMapper.convertValue(donateEntityCreated, DonateDTO.class);

        return donateDTO;

    }

    public DonateDTO update(Integer id,
                            DonateDTO donateUpdate) throws Exception {
        DonateEntity donateEntity = objectMapper.convertValue(donateUpdate, DonateEntity.class);
        Double valor = donateUpdate.getDonate_value()-getDonateById(id).getDonate_value();
        donateRepository.update(id, donateEntity);
        requestRepository.incrementReachedValue(donateEntity.getIdRequest(), valor);

        return donateUpdate;
    }

    public List<DonateDTO>list(){
        return donateRepository.list()
                .stream()
                .map(donateEntity -> objectMapper.convertValue(donateEntity, DonateDTO.class))
                .collect(Collectors.toList());
    }

    public DonateDTO getDonateById(Integer id) throws Exception {
        DonateEntity donateEntity = donateRepository.getDonataById(id);
        DonateDTO donateDTO = objectMapper.convertValue(donateEntity, DonateDTO.class);
        return  donateDTO;
    }

    public DonateDTO delete(Integer id) throws Exception {
        DonateEntity donateEntity = donateRepository.delete(id);
        DonateDTO donateDTO = objectMapper.convertValue(donateEntity, DonateDTO.class);
        Double valor= 0- donateEntity.getDonate_value();
        requestRepository.incrementReachedValue(donateEntity.getIdRequest(), valor);
        return donateDTO;
    }
}
