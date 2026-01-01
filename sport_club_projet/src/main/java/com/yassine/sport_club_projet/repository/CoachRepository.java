package com.yassine.sport_club_projet.repository;

import com.yassine.sport_club_projet.entites.Coach;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {
    void removeCoachById(Long id);

    @EntityGraph(attributePaths = {"teams", "teams.players"})
    @Query("SELECT c FROM Coach c  WHERE c.id = :id")
    Optional<Coach> findCoachByIdWithTeamsAndPlayers(@Param("id") Long id);

    @EntityGraph(attributePaths = {"teams", "teams.players"})
    @Query("SELECT c FROM Coach c")
    List<Coach> findAllWithTeamsAndPlayers();
}
