package com.yassine.sport_club_projet.repository;

import com.yassine.sport_club_projet.entites.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}

