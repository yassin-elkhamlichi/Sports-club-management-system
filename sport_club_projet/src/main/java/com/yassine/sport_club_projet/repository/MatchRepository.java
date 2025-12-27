package com.yassine.sport_club_projet.repository;

import com.yassine.sport_club_projet.entites.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
}

