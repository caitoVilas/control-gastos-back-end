package com.caito.controlgastos.controller;

import com.caito.controlgastos.dto.InstitutionResponse;
import com.caito.controlgastos.dto.NewIstitution;
import com.caito.controlgastos.service.impl.InstitutionService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/api/v1/institution")
    @CrossOrigin
    public class InstitutionController {

        @Autowired
        private InstitutionService service;

        @PostMapping
        public ResponseEntity<InstitutionResponse> createInstitution(@RequestBody NewIstitution newIstitution){

            return new ResponseEntity<InstitutionResponse>(service.createInstitution(newIstitution),
                    HttpStatus.CREATED );
        }

        @GetMapping("/{id}")
        public ResponseEntity<InstitutionResponse> getInstitute(@PathVariable("id") Long id) throws NotFoundException {

            return new ResponseEntity<InstitutionResponse>(service.getInstitution(id),
                    HttpStatus.OK);
        }

        @GetMapping
        public ResponseEntity<List<InstitutionResponse>> getAllInstitution(){

            return new ResponseEntity<List<InstitutionResponse>>(service.getAllInstitution(), HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteIntitution(@PathVariable("id") Long id) throws NotFoundException {

            service.deleteInstitution(id);
            return new ResponseEntity( HttpStatus.OK);
        }
}
