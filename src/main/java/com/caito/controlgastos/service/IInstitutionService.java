package com.caito.controlgastos.service;

import com.caito.controlgastos.dto.InstitutionResponse;
import com.caito.controlgastos.dto.NewIstitution;
import com.caito.controlgastos.entity.Institution;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IInstitutionService {

    public InstitutionResponse createInstitution(NewIstitution newIstitution);
    public InstitutionResponse getInstitution(Long id) throws NotFoundException;
    public List<Institution> getAllInstitution();
    public Page<Institution> getAllInstitutionPage(Pageable pageable);
    public void deleteInstitution(Long id) throws NotFoundException;
}
