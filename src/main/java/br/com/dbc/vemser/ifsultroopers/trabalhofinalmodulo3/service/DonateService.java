package br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.service;

import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.donate.DonateCreateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.dto.donate.DonateDTO;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.DonateEntity;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.entity.RequestEntity;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.exception.BusinessRuleException;
import br.com.dbc.vemser.ifsultroopers.trabalhofinalmodulo3.repository.DonateRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DonateService {

    private final DonateRepository donateRepository;

    private final ObjectMapper objectMapper;

    private final RequestService requestService;

    public DonateDTO create(DonateCreateDTO donateCreate, Integer idRequest) throws Exception {

        DonateEntity donateEntity = objectMapper.convertValue(donateCreate, DonateEntity.class);
        RequestEntity requestEntity = objectMapper.convertValue(requestService.getById(idRequest), RequestEntity.class);
        donateEntity.setIdRequest(idRequest);
        donateEntity.setRequestEntity(requestEntity);
        requestService.incrementReachedValue(idRequest, donateEntity.getDonateValue());

        DonateDTO donateDTO = objectMapper.convertValue(donateRepository.save(donateEntity), DonateDTO.class);

        requestService.checkClosed(idRequest);
        return  donateDTO;
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

        requestService.checkClosed(donateEntity.getIdRequest());
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

    public List<DonateDTO> findByIdRequest(Integer idRequest){
        return donateRepository.findByIdRequest(idRequest)
                .stream()
                .map(donateEntity -> objectMapper.convertValue(donateEntity, DonateDTO.class))
                .collect(Collectors.toList());
    }

    public List<DonateDTO> findByDonatorName(String name){
        return donateRepository.findByDonatorNameContainingIgnoreCase(name)
                .stream()
                .map(donateEntity -> objectMapper.convertValue(donateEntity, DonateDTO.class))
                .collect(Collectors.toList());
    }

}
