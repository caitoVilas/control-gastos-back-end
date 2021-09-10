package com.caito.controlgastos.service;

import com.caito.controlgastos.dto.InstitutionResponse;
import com.caito.controlgastos.dto.NewIstitution;
import javassist.NotFoundException;

import java.util.List;

public interface IInstitutionService {

    public InstitutionResponse createInstitution(NewIstitution newIstitution);
    public InstitutionResponse getInstitution(Long id) throws NotFoundException;
    public List<InstitutionResponse> getAllInstitution();
    public void deleteInstitution(Long id) throws NotFoundException;
}
