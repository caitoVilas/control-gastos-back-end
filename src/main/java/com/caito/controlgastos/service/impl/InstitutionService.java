package com.caito.controlgastos.service.impl;

import com.caito.controlgastos.constants.ConstantsExceptionMessages;
import com.caito.controlgastos.dto.InstitutionResponse;
import com.caito.controlgastos.dto.NewIstitution;
import com.caito.controlgastos.entity.Institution;
import com.caito.controlgastos.exceptions.customs.BadRequestException;
import com.caito.controlgastos.repository.InstitutionRepository;
import com.caito.controlgastos.service.IInstitutionService;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InstitutionService implements IInstitutionService {

    @Autowired
    private InstitutionRepository institutionRepository;


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
    public List<InstitutionResponse> getAllInstitution() {

        List<InstitutionResponse> list = institutionRepository.findAll()
                .stream()
                .map(this :: institutionToDto)
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public void deleteInstitution(Long id) throws NotFoundException {

        if (id == null){
            throw new BadRequestException(ConstantsExceptionMessages.MSG_INST_NOT_ID);
        }

        Institution institution = institutionRepository.findById(id).orElseThrow(() -> new NotFoundException(
                ConstantsExceptionMessages.MSG_INST_NOT_FOUND));
        institutionRepository.deleteById(id);
    }

    private InstitutionResponse institutionToDto(Institution institution){

        ModelMapper mapper = new ModelMapper();
        InstitutionResponse institutionResponse = mapper.map(institution, InstitutionResponse.class);
        return institutionResponse;
    }
}
