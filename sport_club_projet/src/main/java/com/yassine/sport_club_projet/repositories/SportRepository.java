package com.yassine.sport_club_projet.repositories;

import com.yassine.sport_club_projet.entites.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportRepository extends JpaRepository<Sport, Long> {
}

