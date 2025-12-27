package com.yassine.sport_club_projet.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.yassine.sport_club_projet.entites.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
