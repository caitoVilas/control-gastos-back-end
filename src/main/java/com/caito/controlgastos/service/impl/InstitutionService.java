package com.caito.controlgastos.service.impl;

import com.caito.controlgastos.constants.ConstantsExceptionMessages;
import com.caito.controlgastos.dto.InstitutionResponse;
import com.caito.controlgastos.dto.NewIstitution;
import com.caito.controlgastos.entity.Institution;
import com.caito.controlgastos.exceptions.customs.BadRequestException;
import com.caito.controlgastos.repository.CreditCardRepository;
import com.caito.controlgastos.repository.InstitutionRepository;
import com.caito.controlgastos.service.IInstitutionService;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InstitutionService implements IInstitutionService {

    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;


    @Override
    public InstitutionResponse createInstitution(NewIstitution newIstitution) {

        if (institutionRepository.existsByName(newIstitution.getName())){
            throw new BadRequestException(ConstantsExceptionMessages.MSG_INST_EXISTS_BY_NAME);
        }
        if (newIstitution.getName() == null | newIstitution.getName() == ""){
            throw new BadRequestException(ConstantsExceptionMessages.MSG_INST_NAME_EMPTY);
        }

        ModelMapper mapper = new ModelMapper();
        Institution institution = mapper.map(newIstitution, Institution.class);
        institutionRepository.save(institution);

        return  mapper.map(institution, InstitutionResponse.class);
    }

    @Override
    public InstitutionResponse getInstitution(Long id) throws NotFoundException {

        if (id == null){
            throw new BadRequestException(ConstantsExceptionMessages.MSG_INST_NOT_ID);
        }

        Institution institution =  institutionRepository.findById(id).orElseThrow(() -> new NotFoundException(
                ConstantsExceptionMessages.MSG_INST_NOT_FOUND));
        ModelMapper mapper = new ModelMapper();
        return mapper.map(institution, InstitutionResponse.class);
    }

    @Override
    public List<Institution> getAllInstitution() {
        return institutionRepository.findAll();
    }

    @Override
    public Page<Institution> getAllInstitutionPage(Pageable pageable) {
        return institutionRepository.findAll(pageable);
    }

    @Override
    public void deleteInstitution(Long id) throws NotFoundException {
        if (id == null){
            throw new BadRequestException(ConstantsExceptionMessages.MSG_INST_NOT_ID);
        }

        Institution institution = institutionRepository.findById(id).orElseThrow(() -> new NotFoundException(
                ConstantsExceptionMessages.MSG_INST_NOT_FOUND));
        if (creditCardRepository.existsByInstitution(institution)){
            throw new BadRequestException(ConstantsExceptionMessages.MSG_INST_IN_USE);
        }
        institutionRepository.deleteById(id);
    }
}
