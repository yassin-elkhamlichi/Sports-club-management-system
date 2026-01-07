package com.yassine.sport_club_projet.repositories;

import com.yassine.sport_club_projet.entites.Match;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    @EntityGraph(attributePaths = {"team" , "facility" , "tickets"})
    @Query("SELECT m FROM Match m  ORDER BY m.dateTime ASC")
    List<Match> findAllWithTeamsSortByDateTime();
}