package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.service;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.DonateCreateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.DonateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.Donate;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.Request;
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

        Donate donate = objectMapper.convertValue(donateCreate, Donate.class);
        requestRepository.incrementReachedValue(donate.getIdRequest(), donate.getDonate_value());
        Donate donateCreated = donateRepository.create(donate);
        DonateDTO donateDTO = objectMapper.convertValue(donateCreated, DonateDTO.class);

        return donateDTO;

    }

    public DonateDTO update(Integer id,
                            DonateDTO donateUpdate) throws Exception {
        Donate donate = objectMapper.convertValue(donateUpdate, Donate.class);
        Double valor = donateUpdate.getDonate_value()-getDonateById(id).getDonate_value();
        donateRepository.update(id, donate);
        requestRepository.incrementReachedValue(donate.getIdRequest(), valor);

        return donateUpdate;
    }

    public List<DonateDTO>list(){
        return donateRepository.list()
                .stream()
                .map(donate -> objectMapper.convertValue(donate, DonateDTO.class))
                .collect(Collectors.toList());
    }

    public DonateDTO getDonateById(Integer id) throws Exception {
        Donate donate= donateRepository.getDonataById(id);
        DonateDTO donateDTO = objectMapper.convertValue(donate, DonateDTO.class);
        return  donateDTO;
    }

    public DonateDTO delete(Integer id) throws Exception {
        Donate donate = donateRepository.delete(id);
        DonateDTO donateDTO = objectMapper.convertValue(donate, DonateDTO.class);
        Double valor= 0-donate.getDonate_value();
        requestRepository.incrementReachedValue(donate.getIdRequest(), valor);
        return donateDTO;
    }
}
