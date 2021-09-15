package com.caito.controlgastos.repository;

import com.caito.controlgastos.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    public boolean existsByUserAndInstitutionAndCard(Long id_user, Long id_institution, Long id_card);
}
