package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.service;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.donate.DonateCreateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.donate.DonateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.DonateEntity;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.exception.BusinessRuleException;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.repository.DonateRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Service
@RequiredArgsConstructor
public class DonateService {

    private final DonateRepository donateRepository;

    private final ObjectMapper objectMapper;

    private final RequestService requestService;

    public DonateDTO create(DonateCreateDTO donateCreate) throws Exception {

        DonateEntity donateEntity = objectMapper.convertValue(donateCreate, DonateEntity.class);
        requestService.incrementReachedValue(donateEntity.getIdRequest(), donateEntity.getDonateValue());
        DonateEntity donateEntityCreated = donateRepository.save(donateEntity);

        return  objectMapper.convertValue(donateEntityCreated, DonateDTO.class);
    }

    public DonateDTO update(Integer id,
                            DonateCreateDTO donateUpdate) throws Exception {
        DonateEntity donateEntity = donateRepository.findById(id)
                .orElseThrow(()->new BusinessRuleException("Donate não encontrada!"));
        donateEntity.setDonateValue(donateUpdate.getDonateValue());
        donateEntity.setDescription(donateUpdate.getDescription());
        donateEntity.setDonatorEmail(donateUpdate.getDonatorEmail());
        donateEntity.setDonatorName(donateUpdate.getDonatorName());
        requestService.incrementReachedValue(donateEntity.getIdRequest(), donateUpdate.getDonateValue());

        return  objectMapper.convertValue(donateRepository.save(donateEntity), DonateDTO.class);
    }

    public List<DonateDTO>list(){
        return donateRepository.findAll()
                .stream()
                .map(donateEntity -> objectMapper.convertValue(donateEntity, DonateDTO.class))
                .collect(Collectors.toList());
    }

    public DonateDTO getDonateById(Integer id) throws Exception {
      DonateEntity donateEntity= donateRepository.findById(id)
              .orElseThrow(()-> new BusinessRuleException("Donate não encontrada!"));
      return objectMapper.convertValue(donateEntity,DonateDTO.class);
    }

    public DonateDTO delete(Integer id) throws Exception {
        DonateEntity donateEntity = donateRepository.findById(id)
                .orElseThrow(()->new BusinessRuleException("Donate não encontrada!"));
        donateRepository.deleteById(id);
        DonateDTO donateDTO = objectMapper.convertValue(donateEntity, DonateDTO.class);
        Double valor= 0- donateEntity.getDonateValue();
        requestService.incrementReachedValue(donateEntity.getIdRequest(), valor);
        return donateDTO;
    }


}
