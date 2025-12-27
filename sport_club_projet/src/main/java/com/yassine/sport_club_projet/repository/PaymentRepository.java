package com.yassine.sport_club_projet.repository;

import com.yassine.sport_club_projet.entites.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}

