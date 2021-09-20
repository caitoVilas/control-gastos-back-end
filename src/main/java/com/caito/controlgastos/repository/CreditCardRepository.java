package com.caito.controlgastos.repository;

import com.caito.controlgastos.entity.Card;
import com.caito.controlgastos.entity.CreditCard;
import com.caito.controlgastos.entity.Institution;
import com.caito.controlgastos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    boolean existsByUserAndInstitutionAndCard(User user, Institution institution, Card card);
    List<CreditCard> findAllByUser(User user);
}
