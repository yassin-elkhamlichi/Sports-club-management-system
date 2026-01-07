package com.yassine.sport_club_projet.repositories;

import com.yassine.sport_club_projet.entites.Attendance;
import com.yassine.sport_club_projet.entites.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Optional<List<Attendance>> findByMatchId(Long matchId);
}
