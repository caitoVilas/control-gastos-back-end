package com.caito.controlgastos.repository;

import com.caito.controlgastos.entity.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {

    public boolean existsByName(String name);
}
