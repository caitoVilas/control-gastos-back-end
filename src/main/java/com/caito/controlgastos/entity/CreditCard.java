package com.caito.controlgastos.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "credit_cards")
@SQLDelete(sql = "UPDATE credit_cards SET deleted=true WHERE id=?")
@Where(clause = "deleted=false")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "institution_id")
    private Institution institution;
    @ManyToOne
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private Card card;
    @CreationTimestamp
    private LocalDateTime creation;
    @UpdateTimestamp
    private LocalDateTime updated;
    private boolean deleted;
}
