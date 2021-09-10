package com.caito.controlgastos.repository;

import com.caito.controlgastos.entity.Card;
import com.caito.controlgastos.entity.CreditCard;
import com.caito.controlgastos.entity.Institution;
import com.caito.controlgastos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    public boolean existsByInstitution(Institution institution);
    public boolean existsByCard(Card card);
    public boolean existsByUser(User user);
    public boolean existsByUserAndInstitutionAndCard(User user, Institution institution, Card card);
}
